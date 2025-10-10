package com.example.dailydo.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    PENDING (0, "Pending"),
    IN_PROGRESS(1, "In_progress"),
    DONE(2, "Done");

    private final int code;
    private final String label;

    Status(int code, String label)
    {
        this.code = code;
        this.label = label;
    }

    public static Status fromCode(int code) {
        for (Status s : Status.values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid Status code: " + code);
    }
}
