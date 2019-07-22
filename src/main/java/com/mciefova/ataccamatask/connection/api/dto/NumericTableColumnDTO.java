package com.mciefova.ataccamatask.connection.api.dto;

public class NumericTableColumnDTO extends TableColumnDTO {

    private String precision;
    private String scale;

    public NumericTableColumnDTO(String tableName, String columnName, String position, String defaultValue, String
            nullable, String dataType, String precision, String scale) {
        super(tableName, columnName, position, defaultValue, nullable, dataType);
        this.precision = precision;
        this.scale = scale;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
