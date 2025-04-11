package com.example.infrauserservice.web.exception.model;

import lombok.Getter;

@Getter
public enum ExceptionStatus {
    US001("Entity Not Found"),
    US002("Invalid Argument Exception");

    private final String label;

    ExceptionStatus(String label) {
        this.label = label;
    }
}
