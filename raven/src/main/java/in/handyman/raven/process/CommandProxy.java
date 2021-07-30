package in.handyman.raven.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.compiler.RavenLexer;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.Lambda;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

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

    public static void setTarget(final Lambda target, final ParseTree source) {
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
                            final String text = getString(o);
                            final Method method = getMethod(target, fieldName, getter.getReturnType());
                            Arrays.stream(method.getParameterTypes()).findFirst().ifPresent(aClass -> {
                                final Object convertValue = mapper.convertValue(text, aClass);
                                try {
                                    method.invoke(target, convertValue);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new HandymanException("Context mapping failed for Token", e);
                                }
                            });
                        } else if (field.getType() == RavenParser.ExpressionContext.class) {
                            final RavenParser.ExpressionContext o = (RavenParser.ExpressionContext) fieldValue;
                            final Boolean condition = condition(o);
                            setValue(target, fieldName, getter, condition);
                        } else if (field.getType() == RavenParser.JsonContext.class) {
                            final RavenParser.JsonContext o = (RavenParser.JsonContext) fieldValue;
                            final JsonNode node = mapper.readTree(o.getText());
                            setValue(target, fieldName, getter, node);
                        } else if (field.getType() == List.class) {
                            final List<Object> tokens = (List<Object>) fieldValue;
                            final Type actualTypeArgument = ((ParameterizedType) getter.getGenericReturnType()).getActualTypeArguments()[0];
//                        actualTypeArgument.getTypeName()
                            final List<Object> list = tokens.stream()
                                    .filter(Token.class::isInstance)
                                    .map(Token.class::cast)
                                    .map(CommandProxy::getString)
                                    .map(s -> {
                                        try {
                                            return mapper.convertValue(s, Class.forName(actualTypeArgument.getTypeName()));
                                        } catch (ClassNotFoundException e) {
                                            log.warn(e);
                                        }
                                        return null;
                                    })
                                    .collect(Collectors.toList());
                            final Class<?> returnType = getter.getReturnType();
                            final Method method = getMethod(target, fieldName, returnType);
                            Arrays.stream(method.getParameterTypes()).findFirst().ifPresent(aClass -> {
                                try {
                                    method.invoke(target, list);
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new HandymanException("Context mapping failed for List", e);
                                }
                            });
                        } else {
                            throw new HandymanException("Unknown Context provided");
                        }
                    }
                } catch (IllegalAccessException | NoSuchMethodException e) {
                    throw new HandymanException("Context mapping failed", e);
                } catch (JsonMappingException e) {
                    throw new HandymanException("Json Context mapping failed", e);
                } catch (JsonProcessingException e) {
                    throw new HandymanException("Unknown Context provided");
                }
            }
        }
    }

    private static void setValue(final Lambda target, final String fieldName, final Method getter, final Object node) throws NoSuchMethodException {
        final Method method = getMethod(target, fieldName, getter.getReturnType());
        Arrays.stream(method.getParameterTypes()).findFirst().ifPresent(aClass -> {
            try {
                method.invoke(target, node);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new HandymanException("Context mapping failed for ExpressionContext", e);
            }
        });
    }

    private static Method getMethod(final Lambda target, final String fieldName, final Class<?> returnType) throws NoSuchMethodException {
        return target.getClass().getMethod("set" + fieldName, returnType);
    }

    private static String getString(final Token o) {
        final String text;
        if (o.getType() == RavenLexer.STRING) {
            text = o.getText().substring(1, o.getText().length() - 1);
        } else {
            text = o.getText();
        }
        return text.trim();
    }

    private static boolean condition(final RavenParser.ExpressionContext context) {
        return Optional.ofNullable(context)
                .map(expressionContext -> expressionContext.operator)
                .filter(token -> {
                    final String operator = context.operator.getText();
                    if (Objects.equals(operator, "<")) {
                        final double lhs = Double.parseDouble(context.lhs.getText());
                        final double rhs = Double.parseDouble(context.rhs.getText());
                        return lhs < rhs;
                    } else if (Objects.equals(operator, ">")) {
                        final double lhs = Double.parseDouble(context.lhs.getText());
                        final double rhs = Double.parseDouble(context.rhs.getText());
                        return lhs > rhs;
                    } else if (Objects.equals(operator, "==")) {
                        final String lhs = context.lhs.getText();
                        final String rhs = context.rhs.getText();
                        return Objects.equals(lhs, rhs);
                    } else if (Objects.equals(operator, "contains")) {
                        final String lhs = context.lhs.getText();
                        final String rhs = context.rhs.getText();
                        return lhs.contains(rhs);
                    } else {
                        throw new HandymanException("Unknown Operator");
                    }
                }).isPresent();
    }
}
