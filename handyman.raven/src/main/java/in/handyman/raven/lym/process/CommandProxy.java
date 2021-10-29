package in.handyman.raven.lym.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.action.IActionContext;
import in.handyman.raven.compiler.RavenLexer;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lib.model.RestPart;
import in.handyman.raven.lib.model.StartProcess;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
@Log4j2
public class CommandProxy {

    private static final ObjectMapper mapper = new ObjectMapper();

    private CommandProxy() {
    }

    public static void setTarget(final IActionContext target, final ParseTree source, final Map<String, String> context) {
        final Map<String, Method> methodNames = Arrays.stream(target.getClass().getMethods())
                .filter(method -> method.getName().startsWith("get"))
                .collect(Collectors.toMap(method -> method.getName().substring(3), method -> method, (p, q) -> p));
//CAse sensitive
        for (final Field field : source.getClass().getDeclaredFields()) {
            final String name = field.getName();
            final String fieldName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            if (methodNames.containsKey(fieldName)) {
                final Method getter = methodNames.get(fieldName);
                try {
                    final Object fieldValue = field.get(source);
                    if (Objects.nonNull(fieldValue)) {
                        if (field.getType() == Token.class) {
                            final Token o = (Token) fieldValue;
                            final String text = getString(o, context);
                            final Method method = getMethod(target, fieldName, getter.getReturnType());
                            Arrays.stream(method.getParameterTypes()).findFirst().ifPresent(aClass -> {
                                final Object convertValue = mapper.convertValue(text, aClass);
                                try {
                                    method.invoke(target, convertValue);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new HandymanException("RavenParserContext mapping failed for Token", e);
                                }
                            });
                        } else if (field.getType() == RavenParser.ExpressionContext.class) {
                            final RavenParser.ExpressionContext o = (RavenParser.ExpressionContext) fieldValue;
                            final Boolean condition = condition(o, context);
                            setValue(target, fieldName, getter, condition);
                        } else if (field.getType() == RavenParser.JsonContext.class) {
                            final RavenParser.JsonContext o = (RavenParser.JsonContext) fieldValue;
                            final String text = o.getText();
                            final JsonNode node = mapper.readTree(getString(context, text));
                            setValue(target, fieldName, getter, node);
                        } else if (field.getType() == List.class) {
                            final List<Object> tokens = (List<Object>) fieldValue;
                            final Type actualTypeArgument = ((ParameterizedType) getter.getGenericReturnType()).getActualTypeArguments()[0];
                            final List<Object> list = tokens.stream()
                                    .map(o -> {
                                        if (o instanceof Token) {
                                            var token = (Token) o;
                                            var s = getString(token, context);
                                            try {
                                                return mapper.convertValue(s, Class.forName(actualTypeArgument.getTypeName()));
                                            } catch (ClassNotFoundException e) {
                                                throw new HandymanException("RavenParserContext mapping failed for List", e);
                                            }
                                        } else if (o instanceof RavenParser.RestPartContext) {
                                            var token = (RavenParser.RestPartContext) o;
                                            return RestPart.builder()
                                                    .partName(getString(token.partName, context))
                                                    .partData(getString(token.partData, context))
                                                    .type(getString(token.type, context))
                                                    .build();
                                        } else if (o instanceof RavenParser.StartProcessContext) {
                                            var token = (RavenParser.StartProcessContext) o;
                                            return StartProcess.builder()
                                                    .name(getString(token.name, context))
                                                    .target(getString(token.target, context))
                                                    .build();
                                        }
                                        throw new HandymanException("RavenParserContext mapping failed for List");
                                    }).collect(Collectors.toList());
                            final Class<?> returnType = getter.getReturnType();
                            final Method method = getMethod(target, fieldName, returnType);
                            Arrays.stream(method.getParameterTypes()).findFirst().ifPresent(aClass -> {
                                try {
                                    method.invoke(target, list);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new HandymanException("RavenParserContext mapping failed for List", e);
                                }
                            });
                        } else {
                            throw new HandymanException("Unknown RavenParserContext provided");
                        }
                    }
                } catch (IllegalAccessException | NoSuchMethodException e) {
                    throw new HandymanException("RavenParserContext mapping failed", e);
                } catch (JsonMappingException e) {
                    throw new HandymanException("Json RavenParserContext mapping failed", e);
                } catch (JsonProcessingException e) {
                    throw new HandymanException("Unknown RavenParserContext provided");
                }
            }
        }
    }

    private static String getString(final Token o, final Map<String, String> context) {
        if (Objects.nonNull(o)) {
            final String text;
            if (o.getType() == RavenLexer.STRING) {
                text = o.getText().substring(1, o.getText().length() - 1);
            } else {
                text = o.getText();
            }
            return getString(context, text);
        } else {
            return null;
        }
    }

    private static Method getMethod(final IActionContext target, final String fieldName, final Class<?> returnType) throws NoSuchMethodException {
        return target.getClass().getMethod("set" + fieldName, returnType);
    }

    private static boolean condition(final RavenParser.ExpressionContext context, final Map<String, String> configMap) {
        return Optional.ofNullable(context)
                .map(expressionContext -> expressionContext.operator)
                .filter(token -> {
                    final String operator = getString(context.operator, configMap);
                    final String lhsText = getString(context.lhs, configMap);
                    final String rhsText = getString(context.rhs, configMap);
                    assert lhsText != null;
                    assert rhsText != null;
                    if (Objects.equals(operator, "<")) {
                        final double lhs = Double.parseDouble(lhsText);
                        final double rhs = Double.parseDouble(rhsText);
                        return lhs < rhs;
                    } else if (Objects.equals(operator, ">")) {
                        final double lhs = Double.parseDouble(lhsText);
                        final double rhs = Double.parseDouble(rhsText);
                        return lhs > rhs;
                    } else if (Objects.equals(operator, "==")) {
                        return Objects.equals(lhsText, rhsText);
                    } else if (Objects.equals(operator, "contains")) {
                        return lhsText.contains(rhsText);
                    } else {
                        throw new HandymanException("Unknown Operator");
                    }
                }).isPresent();
    }

    private static void setValue(final IActionContext target, final String fieldName, final Method getter, final Object node) throws NoSuchMethodException {
        final Method method = getMethod(target, fieldName, getter.getReturnType());
        Arrays.stream(method.getParameterTypes()).findFirst().ifPresent(aClass -> {
            try {
                method.invoke(target, node);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new HandymanException("RavenParserContext mapping failed for ExpressionContext", e);
            }
        });
    }

    private static String getString(final Map<String, String> context, final String text) {
        var paramEngine = new StrSubstitutor(context);
        return paramEngine.replace(text.trim());
    }
}
