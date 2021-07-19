package in.handyman.raven.lambda;

import in.handyman.raven.compiler.RavenParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class LambdaGenerationTest {

    @Test
    void generate() {
        final Set<String> actions = Set.of(RavenParser.ruleNames);
        final List<Class<?>> classes = Arrays.asList(RavenParser.class.getDeclaredClasses());
        final LambdaGeneration lambdaGeneration = new LambdaGeneration();
        lambdaGeneration.generate(actions, classes, "in.handyman.raven.lib.model", "in.handyman.raven.lib");
    }
}