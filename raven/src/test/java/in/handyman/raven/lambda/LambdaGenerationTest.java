package in.handyman.raven.lambda;

import in.handyman.raven.compiler.RavenParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class LambdaGenerationTest {

    @Test
    void generate() {
        final List<Class<?>> classes = Arrays.asList(RavenParser.class.getDeclaredClasses());
        final LambdaGeneration lambdaGeneration = new LambdaGeneration();
        lambdaGeneration.generate(classes, "in.handyman.raven.lib.model", "in.handyman.raven.lib");
    }
}
