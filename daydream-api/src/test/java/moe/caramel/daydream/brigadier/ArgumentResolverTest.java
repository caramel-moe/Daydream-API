package moe.caramel.daydream.brigadier;

import static org.junit.jupiter.api.Assertions.assertTrue;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class ArgumentResolverTest {

    @Test
    public void methodTest() {
        final Set<String> arguments = Arrays.stream(ArgumentTypes.class.getDeclaredMethods())
            .map(Method::getName)
            .collect(Collectors.toSet());
        final Set<String> resolvers = Arrays.stream(Resolvers.class.getDeclaredMethods())
            .map(Method::getName)
            .collect(Collectors.toSet());

        for (final String method : arguments) {
            assertTrue(resolvers.contains(method), "Cannot find resolver for argument: " + method);
        }
    }
}
