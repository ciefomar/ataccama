package com.mciefova.dbconnector.module.api.connector.connection;

import com.mciefova.dbconnector.DatabaseType;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostgresqlConnectionProviderTest {

    @Autowired
    private PostgresqlConnectionProvider provider;

    @DisplayName("Url creation test")
    @ParameterizedTest(name = "host = {0}, port = {1}, dbName = {2}")
    @MethodSource("urlParams")
    void buildDatabaseUrl(String host, String port, String dbName, String result) {
        ConnectionEntity connection = new ConnectionEntity("", host, port, dbName, DatabaseType.POSTGRESQL, "", "");

        assertEquals(provider.buildDatabaseUrl(connection), result);
    }

    private static Stream<Arguments> urlParams() {

        return Stream.of(
                Arguments.of("localhost", "5432", "customer", "jdbc:postgresql://localhost:5432/customer"),
                Arguments.of("localhost", "5432", "order", "jdbc:postgresql://localhost:5432/order"),
                Arguments.of("localhost", "5432", "store", "jdbc:postgresql://localhost:5432/store"),
                Arguments.of("localhost", "5432", "product", "jdbc:postgresql://localhost:5432/product")
        );
    }

}
