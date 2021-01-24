package com.mciefova.dbconnector;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Database type enumeration class.
 */
public enum DatabaseType {
    @JsonProperty("oracle")
    ORACLE,
    @JsonProperty("mysql")
    MYSQL,
    @JsonProperty("postgresql")
    POSTGRESQL
}
