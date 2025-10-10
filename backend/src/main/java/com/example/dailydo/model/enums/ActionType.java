package com.example.dailydo.model.enums;

import lombok.Getter;

@Getter
public enum ActionType {
    CREATE(1, "Create"),
    UPDATE(2, "Update"),
    COMPLETE(3, "Complete"),
    REOPEN(4, "Reopen"),
    DELETE(5, "Delete"),
    COMMENT(6, "Comment");

    private final int code;
    private final String label;

    ActionType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static ActionType fromCode(int code) {
        for (ActionType type : values()) {
            if (type.code == code) return type;
        }
        throw new IllegalArgumentException("Invalid ActionType code: " + code);
    }
}
