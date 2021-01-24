package com.mciefova.dbconnector.module.api.service.reader;

import com.mciefova.dbconnector.exception.ApiException;
import com.mciefova.dbconnector.module.api.dto.Record;
import com.mciefova.dbconnector.module.api.dto.RecordValue;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Reads {@link ResultSet} result with record information and transforms to DTO
 * object.
 */
@Component
public class RecordReader implements DbInfoReader<Record> {

    @Override
    public Iterable<Record> readInfo(ResultSet loadedDbInfo) {
        List<Record> recordList = new ArrayList<>();

        try {
            List<String> columnNames = readResultSetColumnNames(loadedDbInfo);

            while (loadedDbInfo.next()) {
                Record record = new Record();

                for (String columnName : columnNames) {
                    RecordValue value
                            = new RecordValue(columnName,
                                              loadedDbInfo.getString(columnName));
                    record.addValue(value);
                }

                recordList.add(record);
            }
        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

        return recordList;
    }

    private List<String> readResultSetColumnNames(ResultSet result) throws SQLException {
        List<String> columnNames = new ArrayList<>();

        ResultSetMetaData resultMetaData = result.getMetaData();

        for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
            columnNames.add(resultMetaData.getColumnName(i));
        }

        return columnNames;
    }

}
