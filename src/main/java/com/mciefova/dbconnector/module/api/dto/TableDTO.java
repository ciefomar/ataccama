package com.mciefova.dbconnector.module.api.dto;

import java.io.Serializable;

/**
 * Table DTO.
 */
public class TableDTO implements Serializable {

    private String tableName;
    private String tableType;
    private String catalogName;
    private String schemaName;
    private String remarks;

    public TableDTO(String tableName, String tableType, String catalogName,
                    String schemaName, String remarks) {
        this.tableName = tableName;
        this.tableType = tableType;
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.remarks = remarks;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
