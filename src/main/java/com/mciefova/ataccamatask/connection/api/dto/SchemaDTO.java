package com.mciefova.ataccamatask.connection.api.dto;

import java.io.Serializable;

public class SchemaDTO implements Serializable {

    private String schemaName;
    private String catalogName;
    private String schemaOwner;

    public SchemaDTO(String schemaName, String catalogName, String schemaOwner) {
        this.schemaName = schemaName;
        this.catalogName = catalogName;
        this.schemaOwner = schemaOwner;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaOwner() {
        return schemaOwner;
    }

    public void setSchemaOwner(String schemaOwner) {
        this.schemaOwner = schemaOwner;
    }
}
