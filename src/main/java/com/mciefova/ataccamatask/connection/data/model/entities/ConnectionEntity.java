package com.mciefova.ataccamatask.connection.data.model.entities;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "connection", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "connection_seq")
    @SequenceGenerator(name = "connection_id_generator", sequenceName = "connection_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @NaturalId(mutable = true)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "port", nullable = false)
    private String port;

    @Column(name = "database_name", nullable = false)
    private String databaseName;

    @Column(name = "database_type", nullable = false)
    private String databaseType;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    public ConnectionEntity() {
    }

    public ConnectionEntity(int id, String name, String host, String port, String databaseName) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
    }

    public ConnectionEntity(String name, String host, String port, String databaseName, String databaseType, String
            userName, String password) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.databaseType = databaseType;
        this.userName = userName;
        this.password = password;
    }

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
        ConnectionEntity that = (ConnectionEntity) o;
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
