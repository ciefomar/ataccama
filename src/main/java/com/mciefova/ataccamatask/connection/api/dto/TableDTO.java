package com.mciefova.ataccamatask.connection.api.dto;

import java.io.Serializable;

public class TableDTO implements Serializable {

    private String tableName;
    private String tableType;
    private String catalogName;
    private String schemaName;

    public TableDTO(String tableName, String tableType, String catalogName, String schemaName) {
        this.tableName = tableName;
        this.tableType = tableType;
        this.catalogName = catalogName;
        this.schemaName = schemaName;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
