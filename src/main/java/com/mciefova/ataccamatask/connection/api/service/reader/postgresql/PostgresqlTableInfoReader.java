package com.mciefova.ataccamatask.connection.api.service.reader.postgresql;

import com.mciefova.ataccamatask.connection.api.dto.TableDTO;
import com.mciefova.ataccamatask.connection.api.service.reader.DbInfoReader;
import com.mciefova.ataccamatask.exception.ApiException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostgresqlTableInfoReader implements DbInfoReader<TableDTO> {

    private static final String TABLE_NAME = "table_name";
    private static final String TABLE_TYPE = "table_type";
    private static final String TABLE_CATALOG = "table_catalog";
    private static final String TABLE_SCHEMA = "table_schema";

    @Override
    public List<TableDTO> readInfo(ResultSet loadedDbInfo) {
        List<TableDTO> tableList = new ArrayList<>();

        try {
            while (loadedDbInfo.next()) {
                String tableName = loadedDbInfo.getString(TABLE_NAME);
                String tableType = loadedDbInfo.getString(TABLE_TYPE);
                String catalogName = loadedDbInfo.getString(TABLE_CATALOG);
                String schemaName = loadedDbInfo.getString(TABLE_SCHEMA);

                tableList.add(new TableDTO(tableName, tableType, catalogName, schemaName));
            }
        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

        return tableList;
    }
}
