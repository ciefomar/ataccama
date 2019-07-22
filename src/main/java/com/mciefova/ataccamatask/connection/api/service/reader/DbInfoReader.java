package com.mciefova.ataccamatask.connection.api.service.reader;

import java.sql.ResultSet;
import java.util.List;

public interface DbInfoReader<T> {

    List<T> readInfo (ResultSet loadedDbInfo);
}
