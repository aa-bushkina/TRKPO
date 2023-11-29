package com.cygans.security.db;

public enum RoleEnum {
    PARTICIPANT("PARTICIPANT"),
    MENTOR("MENTOR");
    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

