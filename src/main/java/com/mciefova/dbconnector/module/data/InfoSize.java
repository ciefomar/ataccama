package com.mciefova.dbconnector.module.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Connection entity info size.
 */
public enum InfoSize {

    @JsonProperty("short")
    SHORT,
    @JsonProperty("full")
    FULL
}
