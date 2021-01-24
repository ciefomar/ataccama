package com.mciefova.dbconnector.module.api.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Table record DTO.
 */
public class Record implements Serializable {

    private Set<RecordValue> values;

    public Record() {
        this.values = new HashSet<>();
    }

    public Set<RecordValue> getValues() {
        return values;
    }

    public void setValues(Set<RecordValue> values) {
        this.values = values;
    }

    public void addValue(RecordValue value) {
        this.values.add(value);
    }
}
