package com.mciefova.ataccamatask.connection.api.service.reader.postgresql;

import com.mciefova.ataccamatask.connection.api.dto.SchemaDTO;
import com.mciefova.ataccamatask.connection.api.service.reader.DbInfoReader;
import com.mciefova.ataccamatask.exception.ApiException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostgresqlSchemaInfoReader implements DbInfoReader<SchemaDTO> {

    private static final String CATALOG_NAME = "catalog_name";
    private static final String SCHEMA_NAME = "schema_name";
    private static final String SCHEMA_OWNER = "schema_owner";

    @Override
    public List<SchemaDTO> readInfo(ResultSet loadedDbInfo) {
        List<SchemaDTO> schemaList = new ArrayList<>();

        try {
            while (loadedDbInfo.next()) {
                String schemaName = loadedDbInfo.getString(SCHEMA_NAME);
                String catalogName = loadedDbInfo.getString(CATALOG_NAME);
                String schemaOwner = loadedDbInfo.getString(SCHEMA_OWNER);

                schemaList.add(new SchemaDTO(schemaName, catalogName, schemaOwner));
            }
        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

        return schemaList;
    }
}
