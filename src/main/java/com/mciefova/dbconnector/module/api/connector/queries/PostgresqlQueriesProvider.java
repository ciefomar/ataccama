package com.mciefova.dbconnector.module.api.connector.queries;

import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Query provider for postgresql database.
 */
@Service
public class PostgresqlQueriesProvider implements DatabaseQueriesProvider {

    private static final String LOAD_TABLE_VIEW_TEMPLATE = "SELECT * FROM %s.%s";
    private static final String LIMIT = "LIMIT";
    private static final String ORDER_BY = "ORDER BY";

    /**
     * {@inheritDoc }.
     * @param schemaName schema name
     * @param tableName table name
     * @param limit limit
     * @param orderBy order by
     * @return table view
     */
    @Override
    public String createLoadTableViewQuery(String schemaName, String tableName,
                                           Optional<String> limit, Optional<String> orderBy) {
        if (schemaName != null && !schemaName.isEmpty()
                && tableName != null && !tableName.isEmpty()) {
            StringBuilder query = new StringBuilder();
            query.append(String.format(LOAD_TABLE_VIEW_TEMPLATE, schemaName, tableName));

            if (orderBy.isPresent()) {
                query.append(" ").append(ORDER_BY).append(" ").append(orderBy.get());
            }

            if (limit.isPresent()) {
                query.append(" ").append(LIMIT).append(" ").append(limit.get());
            }

            return query.toString();

        } else {
            throw new IllegalArgumentException("Schema or table name is missing");
        }
    }
}
