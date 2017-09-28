package com.workshop.enums;

public enum LogType {
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR");

    private String logType;

    LogType(String url) {
        this.logType = url;
    }

    public String getType() {
        return logType;
    }
}
