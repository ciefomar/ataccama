package com.mciefova.ataccamatask.connection.api.connection.creator;

import com.mciefova.ataccamatask.connection.api.connection.factory.ConnectionWrapper;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionWrapperCreator {
    Connection createConnection(ConnectionEntity connectionData) throws SQLException, ClassNotFoundException;
    ConnectionWrapper createConnectioWrapper (ConnectionEntity connectionData);
}
