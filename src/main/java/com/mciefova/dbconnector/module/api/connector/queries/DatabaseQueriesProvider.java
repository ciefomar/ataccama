package com.mciefova.dbconnector.module.api.connector.queries;

import java.util.Optional;

/**
 * Database queries provider.
 */
public interface DatabaseQueriesProvider {

    /**
     * Creates table view query.
     *
     * @param schemaName schema name
     * @param tableName table name
     * @param limit record number limitation
     * @param orderBy column to order result by
     * @return table view
     */
    String createLoadTableViewQuery(String schemaName, String tableName,
                                    Optional<String> limit, Optional<String> orderBy);
}
