package com.mciefova.dbconnector.module.api.service.reader;

import com.mciefova.dbconnector.exception.ApiException;
import com.mciefova.dbconnector.module.api.dto.SchemaDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Reads {@link ResultSet} result with schema information and transforms to DTO
 * object.
 */
@Component
public class SchemaReader implements DbInfoReader<SchemaDTO> {

    private static final String SCHEMA_NAME = "TABLE_SCHEM";
    private static final String CATALOG_NAME = "TABLE_CATALOG";

    @Override
    public Iterable<SchemaDTO> readInfo(ResultSet loadedDbInfo) {
        List<SchemaDTO> loadedSchemas = new ArrayList<>();

        try {
            while (loadedDbInfo.next()) {
                String schema = loadedDbInfo.getString(SCHEMA_NAME);
                String catalog = loadedDbInfo.getString(CATALOG_NAME);
                loadedSchemas.add(new SchemaDTO(schema, catalog));
            }
        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

        return loadedSchemas;
    }

}
