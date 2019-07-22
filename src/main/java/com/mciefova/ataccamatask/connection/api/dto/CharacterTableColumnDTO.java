package com.mciefova.ataccamatask.connection.api.dto;

public class CharacterTableColumnDTO extends TableColumnDTO {

    private String maximumLength;

    public CharacterTableColumnDTO(String tableName, String columnName, String position, String defaultValue, String
            nullable, String dataType, String maximumLength) {
        super(tableName, columnName, position, defaultValue, nullable, dataType);
        this.maximumLength = maximumLength;
    }

    public String getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(String maximumLength) {
        this.maximumLength = maximumLength;
    }
}
