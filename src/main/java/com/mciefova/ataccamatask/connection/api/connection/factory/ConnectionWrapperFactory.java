package com.mciefova.ataccamatask.connection.api.connection.factory;

import com.mciefova.ataccamatask.connection.api.connection.creator.PostrgesqlConnectionWrapperCreator;
import com.mciefova.ataccamatask.connection.api.connection.enums.DatabaseType;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionWrapperFactory {

    @Autowired
    private PostrgesqlConnectionWrapperCreator postrgesqlConnectionCreator;

    public ConnectionWrapper createConnectionWrapper (ConnectionEntity connection,
                                                      DatabaseType databaseType) {

        switch (databaseType) {
            case POSTGRESQL:
                return postrgesqlConnectionCreator.createConnectioWrapper(connection);
            case MYSQL:
                return null;
            case ORACLE:
                return null;
            default:
                return null;
        }
    }
}
