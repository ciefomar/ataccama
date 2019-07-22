package com.mciefova.ataccamatask.connection.api.service.reader.postgresql;

import com.mciefova.ataccamatask.connection.api.controller.converter.DataTypeConverter;
import com.mciefova.ataccamatask.connection.api.dto.*;
import com.mciefova.ataccamatask.connection.api.enums.DataType;
import com.mciefova.ataccamatask.connection.api.service.reader.DbInfoReader;
import com.mciefova.ataccamatask.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostgresqlTableColumnInfoReader implements DbInfoReader<TableColumnDTO> {

    //general properties
    private static final String TABLE_NAME = "table_name";
    private static final String COLUMN_NAME = "column_name";
    private static final String POSITION = "ordinal_position";
    private static final String DEFAULT = "column_default";
    private static final String NULLABLE = "is_nullable";
    private static final String DATA_TYPE = "data_type";

    //character types properties
    private static final String CHARACTER_MAXIMUM_LENGTH = "character_maximum_length";

    //numeric types properties
    private static final String NUMERIC_PRECISION = "numeric_precision";
    private static final String NUMERIC_SCALE = "numeric_scale";

    //datetime types properties
    private static final String DATE_PRECISION = "datetime_precision";

    @Autowired
    private DataTypeConverter dataTypeConverter;

    @Override
    public List<TableColumnDTO> readInfo(ResultSet loadedDbInfo) {

        List<TableColumnDTO> columnList = new ArrayList<>();

        try {
            while (loadedDbInfo.next()) {
                String tableName = loadedDbInfo.getString(TABLE_NAME);
                String columnName = loadedDbInfo.getString(COLUMN_NAME);
                String position = loadedDbInfo.getString(POSITION);
                String defaultValue = loadedDbInfo.getString(DEFAULT);
                String nullable = loadedDbInfo.getString(NULLABLE);
                String dataType = loadedDbInfo.getString(DATA_TYPE);

                DataType columnType = dataTypeConverter.convert(dataType);


                switch (columnType) {
                    case STRING:
                        columnList.add(new CharacterTableColumnDTO(
                                tableName,
                                columnName,
                                position,
                                defaultValue,
                                nullable,
                                dataType,
                                loadedDbInfo.getString(CHARACTER_MAXIMUM_LENGTH)
                        ));
                        break;

                    case NUMBER:
                        columnList.add(new NumericTableColumnDTO(
                                tableName,
                                columnName,
                                position,
                                defaultValue,
                                nullable,
                                dataType,
                                loadedDbInfo.getString(NUMERIC_PRECISION),
                                loadedDbInfo.getString(NUMERIC_SCALE)
                        ));
                        break;

                    case DATE:
                        columnList.add(new DateTableColumnDTO(
                                tableName,
                                columnName,
                                position,
                                defaultValue,
                                nullable,
                                dataType,
                                loadedDbInfo.getString(DATE_PRECISION)
                        ));
                        break;

                    default :
                        columnList.add(new TableColumnDTO(
                                tableName,
                                columnName,
                                position,
                                defaultValue,
                                nullable,
                                dataType
                        ));
                }
            }
        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

        return columnList;
    }
}
