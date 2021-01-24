package com.mciefova.dbconnector.module.data.model.dto;

import com.mciefova.dbconnector.data.Response;

public class ConnectionEntityResponse extends ConnectionEntityRequest implements Response{

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
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
        final ConnectionEntityResponse other = (ConnectionEntityResponse) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id: ").append(this.id).append(", ")
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
