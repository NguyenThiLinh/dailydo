package com.example.dailydo.model.enums;

import lombok.Getter;

@Getter
public enum Priority {
    LOW (0, "Low"),
    MEDIUM (1, "Medium"),
    HIGH(2, "High");

    private final int code;
    private final String label;

    Priority(int code, String label) {
        this.code = code;
        this.label = label;
    }
    public static Priority fromCode(int code) {
        for (Priority p : Priority.values()) {
            if (p.getCode() == code) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid Priority code: " + code);
    }
}
