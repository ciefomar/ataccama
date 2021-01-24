package com.mciefova.dbconnector.module.api.dto;

import java.io.Serializable;

/**
 * Table column DTO.
 */
public class TableColumnDTO implements Serializable{

    protected String tableName;
    protected String columnName;
    protected String position;
    protected String defaultValue;
    protected String nullable;
    protected String dataType;
    protected String remarks;

    public TableColumnDTO() {
    }

    public TableColumnDTO(String tableName, String columnName, String position, String defaultValue, String nullable,
                          String dataType, String remarks) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.position = position;
        this.defaultValue = defaultValue;
        this.nullable = nullable;
        this.dataType = dataType;
        this.remarks = remarks;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
