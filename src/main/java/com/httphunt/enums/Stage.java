package com.httphunt.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Stage {

    FIRST("1/4"), SECOND("2/4"), THIRD("3/4"), FOURTH("4/4");

    private String value;

    Stage(String stage) {
        this.value = stage;
    }


    @JsonCreator
    public static Stage fromValue(String value) {

        for (Stage stage : values()) {
            if (stage.value.equals(value))
                return stage;
        }
        return null;
    }
}
