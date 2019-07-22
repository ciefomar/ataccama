package com.mciefova.ataccamatask.connection.api.queries;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PostgresqlQueriesProviderTest {

    @Autowired
    private PostgresqlQueriesProvider queriesProvider;

    @DisplayName("Null schema / table should throw exception")
    @ParameterizedTest(name = "schemaName = {0}, tableName = {1}")
    @MethodSource("schemaTableValues")
    public void createSelectQuery(String schemaName, String tableName) {
        assertThrows(IllegalArgumentException.class, () -> queriesProvider.createSelectQuery(schemaName, tableName, "", "", ""));
    }

    private static Stream<Arguments> schemaTableValues() {

        return Stream.of(
                Arguments.of("","table"),
                Arguments.of("schema",""),
                Arguments.of("",""),
                Arguments.of(null,"table"),
                Arguments.of("schema",null),
                Arguments.of("",null),
                Arguments.of(null,""),
                Arguments.of(null,null));
    }
}
