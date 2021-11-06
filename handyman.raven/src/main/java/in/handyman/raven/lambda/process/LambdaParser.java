package in.handyman.raven.lambda.process;

import in.handyman.raven.compiler.RavenLexer;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.exception.HandymanException;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Map;

@Log4j2
public class LambdaParser {

    protected static RavenParser.ProcessContext doParse(final String processFile, final Map<String, String> context) {
//        final String tokenMappedProcessFile = bindToken(processFile, context); to skip context mapping while compiling
        final CharStream input = CharStreams.fromString(processFile);
        // create lexer
        final RavenLexer lexer = new RavenLexer(input);
        // create token stream
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create parser
        final RavenParser parser = new RavenParser(tokens);
        parser.setBuildParseTree(true);
        // build parse tree
        return parser.process();
    }

    private static String bindToken(final String load, final Map<String, String> tokenMap) {
        return tokenMap.entrySet()
                .stream()
                .reduce(load,
                        (s, token) -> s.replace(String.format("${%s}", token.getKey()), token.getValue()),
                        (x, y) -> {
                            throw new HandymanException("Binding the Token failed");
                        });
    }

}
