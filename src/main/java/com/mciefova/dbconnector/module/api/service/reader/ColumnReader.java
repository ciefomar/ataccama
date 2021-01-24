package com.mciefova.dbconnector.module.api.service.reader;

import com.mciefova.dbconnector.exception.ApiException;
import com.mciefova.dbconnector.module.api.dto.TableColumnDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Reads {@link ResultSet} result with column information and transforms to DTO
 * object.
 */
@Component
public class ColumnReader implements DbInfoReader<TableColumnDTO> {

    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String COLUMN_NAME = "COLUMN_NAME";
    private static final String DATA_TYPE = "DATA_TYPE";
    private static final String POSITION = "ORDINAL_POSITION";
    private static final String DEFAULT_VALUE = "COLUMN_DEF";
    private static final String NULLABLE = "NULLABLE";
    private static final String REMARKS = "REMARKS";

    @Override
    public Iterable<TableColumnDTO> readInfo(ResultSet loadedDbInfo) {
        List<TableColumnDTO> loadedTableColumns = new ArrayList<>();
        try {
            while (loadedDbInfo.next()) {
                String columnName = loadedDbInfo.getString(COLUMN_NAME);
                String tableName = loadedDbInfo.getString(TABLE_NAME);
                String dataType = loadedDbInfo.getString(DATA_TYPE);
                String position = loadedDbInfo.getString(POSITION);
                String defaultValue = loadedDbInfo.getString(DEFAULT_VALUE);
                String nullable = loadedDbInfo.getString(NULLABLE);
                String remarks = loadedDbInfo.getString(REMARKS);
                loadedTableColumns
                        .add(new TableColumnDTO(tableName, columnName, position,
                                                defaultValue, nullable, dataType,
                                                remarks));
            }
        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

        return loadedTableColumns;
    }

}
