package com.example.dailydo.model.enums;

import lombok.Getter;

@Getter
public enum Frequency {
    DAILY(0, "Daily"),
    WEEKLY(1, "Weekly");

    private int code;
    private String label;

    Frequency(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static Frequency fromCode(int code) {
        for (Frequency f : Frequency.values()) {
            if (f.getCode() == code) {
                return f;
            }
        }
        throw new IllegalArgumentException("Invalid Frequency code: " + code);
    }

    public String getLabelByCode(int code) {
        return fromCode(code).getLabel();
    }
}
