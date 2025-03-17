package utils;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class DataProviderClass {
    public static Stream<Arguments> loginData() {
        return Stream.of(
                Arguments.of("", "", "Epic sadface: Username is required"),
                Arguments.of("standard_user", "", "Epic sadface: Password is required"),
                Arguments.of("standard_user", "secret_sauce", "Swag Labs")
        );
    }
}
