package com.mciefova.dbconnector.module.api;

import com.mciefova.dbconnector.module.api.dto.Record;
import com.mciefova.dbconnector.module.api.dto.SchemaDTO;
import com.mciefova.dbconnector.module.api.dto.TableColumnDTO;
import com.mciefova.dbconnector.module.api.dto.TableDTO;
import com.mciefova.dbconnector.module.api.service.ConnectionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller defining endpoints for connection provider.
 *
 */
@RestController
public class ConnectionController {

    /**
     * Provides connection.
     */
    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    /**
     * Load db schema.
     *
     * @param id connection entity id
     * @return schema list
     */
    @RequestMapping(value = "connections/{id}/schemas")
    public Iterable<SchemaDTO> listSchemasFromDatabase(@PathVariable Long id) {
        return connectionService.listDatabaseSchemas(id);
    }

    /**
     * Load db tables.
     *
     * @param id connection entity id
     * @param schema schema name
     * @return table list
     */
    @RequestMapping(value = "connections/{id}/tables")
    public Iterable<TableDTO>
            listTablesFromDatabase(@PathVariable Long id,
                                   @RequestParam Optional<String> schema) {
        return connectionService.listDatabaseTables(id, schema);
    }

    /**
     * Load db columns.
     *
     * @param id connection entity id
     * @param schema schema name
     * @param table table name
     * @return column list
     */
    @RequestMapping(value = "connections/{id}/columns")
    public Iterable<TableColumnDTO>
            listTableColumns(@PathVariable Long id,
                             @RequestParam Optional<String> schema,
                             @RequestParam Optional<String> table) {
        return connectionService.listTableColumns(id, schema, table);
    }

    /**
     * Load table view.
     *
     * @param id connection entity id
     * @param schema schema name
     * @param table table name
     * @param orderBy column to order by
     * @param limit record limit
     * @return table view
     */
    @RequestMapping(value = "connections/{id}/records")
    public Iterable<Record> loadTableView(@PathVariable Long id,
                                          @RequestParam String schema,
                                          @RequestParam String table,
                                          @RequestParam Optional<String> orderBy,
                                          @RequestParam Optional<String> limit) {

        return connectionService
                .loadTableView(id, schema, table, orderBy, limit);
    }
}
