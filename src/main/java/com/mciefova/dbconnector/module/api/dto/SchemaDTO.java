package com.mciefova.dbconnector.module.api.dto;

import java.io.Serializable;

/**
 * Database schema DTO.
 */
public class SchemaDTO implements Serializable {

    private String schemaName;
    private String catalogName;

    public SchemaDTO(String schemaName, String catalogName) {
        this.schemaName = schemaName;
        this.catalogName = catalogName;
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
}
