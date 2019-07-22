package com.mciefova.ataccamatask.connection.api.controller;

import com.mciefova.ataccamatask.connection.api.dto.SchemaDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableColumnDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableDTO;
import com.mciefova.ataccamatask.connection.api.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping(value = "schemas/{connectionName}")
    public List<SchemaDTO> listSchemasFromDatabase (@PathVariable String connectionName) {
        return connectionService.listDatabaseSchemas(connectionName);
    }

    @RequestMapping(value = "tables/{connectionName}")
    public List<TableDTO> listTablesFromDatabase (@PathVariable String connectionName) {
        return connectionService.listDatabaseTables(connectionName);
    }

    @RequestMapping(value = "tables/{connectionName}", params = {"schema"})
    public List<TableDTO> listTablesFromDatabase (@PathVariable String connectionName, @RequestParam String schema) {
        return connectionService.listDatabaseTables(connectionName, schema);
    }

    @RequestMapping(value = "columns/{connectionName}/{schemaName}/{tableName}")
    public List<TableColumnDTO> listTableColumns (@PathVariable String connectionName,
                                                  @PathVariable("schemaName") String schema,
                                                  @PathVariable ("tableName") String table) {
        return connectionService.listTableColumns(connectionName, schema, table);
    }

    @RequestMapping(value = "records/{connectionName}/{schemaName}/{tableName}")
    public List<Map<String, String>> loadTableView (@PathVariable String connectionName,
                                                    @PathVariable("schemaName") String schema,
                                                    @PathVariable ("tableName") String table,
                                                    @RequestParam(required = false) String columns,
                                                    @RequestParam(required = false) String orderBy,
                                                    @RequestParam(required = false) String limit) {

        return connectionService.loadTableView(connectionName, schema, table, columns, orderBy, limit);
    }
}
