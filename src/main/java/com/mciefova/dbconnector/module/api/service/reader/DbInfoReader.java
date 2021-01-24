package com.mciefova.dbconnector.module.api.service.reader;

import java.sql.ResultSet;

/**
 * Reads {@link ResultSet} result and transforms to DTO object.
 */
public interface DbInfoReader<T> {

    Iterable<T> readInfo(ResultSet loadedDbInfo);
}
