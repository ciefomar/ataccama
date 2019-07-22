package com.mciefova.ataccamatask.connection.api.dto;

public class DateTableColumnDTO extends TableColumnDTO {

    private String precision;

    public DateTableColumnDTO(String tableName, String columnName, String position, String defaultValue, String
            nullable, String dataType, String precision) {
        super(tableName, columnName, position, defaultValue, nullable, dataType);
        this.precision = precision;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}
