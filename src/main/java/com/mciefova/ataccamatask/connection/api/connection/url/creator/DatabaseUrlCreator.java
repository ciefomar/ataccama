package com.mciefova.ataccamatask.connection.api.connection.url.creator;

import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;

public interface DatabaseUrlCreator {
    String buildDatabaseUrl(ConnectionEntity connectionData);
}
