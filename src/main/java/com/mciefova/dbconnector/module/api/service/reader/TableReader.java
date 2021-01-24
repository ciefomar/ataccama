package com.mciefova.dbconnector.module.api.service.reader;

import com.mciefova.dbconnector.exception.ApiException;
import com.mciefova.dbconnector.module.api.dto.TableDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Reads {@link ResultSet} result with table information and transforms to DTO
 * object.
 */
@Component
public class TableReader implements DbInfoReader<TableDTO> {

    private static final String SCHEMA_NAME = "TABLE_SCHEM";
    private static final String CATALOG_NAME = "TABLE_CAT";
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String TABLE_TYPE = "TABLE_TYPE";
    private static final String REMARKS = "REMARKS";

    @Override
    public Iterable<TableDTO> readInfo(ResultSet loadedDbInfo) {
        List<TableDTO> loadedTables = new ArrayList<>();

        try {
            while (loadedDbInfo.next()) {
                String tableName = loadedDbInfo.getString(TABLE_NAME);
                String tableType = loadedDbInfo.getString(TABLE_TYPE);
                String catalogName = loadedDbInfo.getString(CATALOG_NAME);
                String schemaName = loadedDbInfo.getString(SCHEMA_NAME);
                String remarks = loadedDbInfo.getString(REMARKS);
                loadedTables.add(new TableDTO(tableName, tableType, catalogName,
                                              schemaName, remarks));
            }
        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

        return loadedTables;
    }

}
