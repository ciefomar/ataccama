package com.mciefova.ataccamatask.connection.data.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionEntityDTO implements Serializable {

    private int id;
    private String name;
    private String host;
    private String port;
    private String databaseName;
    private String databaseType;
    private String userName;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionEntityDTO that = (ConnectionEntityDTO) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getHost(), that.getHost()) &&
                Objects.equals(getPort(), that.getPort()) &&
                Objects.equals(getDatabaseName(), that.getDatabaseName()) &&
                Objects.equals(getDatabaseType(), that.getDatabaseType()) &&
                Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getHost(), getPort(), getDatabaseName(), getDatabaseType(), getUserName(), getPassword());
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
