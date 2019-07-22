package com.mciefova.ataccamatask.connection.api.connection;

import com.mciefova.ataccamatask.connection.api.connection.enums.DatabaseType;

import java.sql.Connection;

public class ConnectionWrapper {

    private DatabaseType databaseType;
    private Connection connection;

    public ConnectionWrapper(DatabaseType databaseType, Connection connection) {
        this.databaseType = databaseType;
        this.connection = connection;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
