package com.company.enums;

public enum Choice {
    EXIT("exit"),
    SUM("sum"),
    SQRT("sqrt"),
    MULTI_SUM("multi");

    private String choice;

    Choice(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }
}
