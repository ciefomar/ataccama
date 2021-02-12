package com.mciefova.dbconnector.module.data.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mciefova.dbconnector.DatabaseType;
import com.mciefova.dbconnector.data.Request;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionEntityRequest implements Request {

    @NotBlank(message = "Connection name is mandatory.")
    protected String name;

    @NotBlank(message = "Connection host is mandatory.")
    protected String host;

    @NotBlank(message = "Connection port is mandatory.")
    protected String port;

    @NotBlank(message = "Database name is mandatory.")
    protected String databaseName;

    @NotNull(message = "Database type is mandatory.")
    protected DatabaseType databaseType;

    @NotBlank(message = "User name is mandatory.")
    protected String userName;

    @NotBlank(message = "Password is mandatory.")
    protected String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConnectionEntityRequest other = (ConnectionEntityRequest) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.host, other.host)) {
            return false;
        }
        if (!Objects.equals(this.port, other.port)) {
            return false;
        }
        if (!Objects.equals(this.databaseName, other.databaseName)) {
            return false;
        }
        if (!Objects.equals(this.databaseType, other.databaseType)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getHost(), getPort(),
                            getDatabaseName(), getDatabaseType(), getUserName(),
                            getPassword());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("name: ").append(this.name).append(", ")
                .append("host: ").append(this.host).append(", ")
                .append("port: ").append(this.port).append(", ")
                .append("db_name: ").append(this.databaseName).append(", ")
                .append("db_type: ").append(this.databaseType).append(", ")
                .append("user: ").append(this.userName).append(", ")
                .append("password: ").append(this.password)
                .toString();
    }
}
