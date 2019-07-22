package com.mciefova.ataccamatask.connection.api.connection.url.creator;

import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostgresqlDatabaseUrlCreatorTest {

    @Autowired
    private PostgresqlDatabaseUrlCreator urlCreator;

    @DisplayName("Url creation test")
    @ParameterizedTest(name = "host = {0}, port = {1}, dbName = {2}, user = {3}, password = {4}")
    @MethodSource("urlParams")
    void buildDatabaseUrl (String host, String port, String dbName, String user, String password, String result) {
        ConnectionEntity connection= new ConnectionEntity("", host, port, dbName, "", user, password);

        assertEquals(urlCreator.buildDatabaseUrl(connection), result);
    }

    private static Stream<Arguments> urlParams() {

        return Stream.of(
                Arguments.of("localhost", "5432", "customer", "boss", "+++secret", "jdbc:postgresql://localhost:5432/customer?user=boss&password=secret"),
                Arguments.of("localhost", "5432", "order", "little_boss", "+++secret", "jdbc:postgresql://localhost:5432/order?user=little_boss&password=secret"),
                Arguments.of("localhost", "5432", "store", "boss", "+++super.secret", "jdbc:postgresql://localhost:5432/store?user=boss&password=super.secret"),
                Arguments.of("localhost", "5432", "product", "boss", "+++123456", "jdbc:postgresql://localhost:5432/product?user=boss&password=123456")
        );
    }

}
