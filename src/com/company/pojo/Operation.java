package com.company.pojo;

import com.company.enums.Validation;

public class Operation {
    private String message;
    private Validation validation;

    public Operation(String message, Validation validation) {
        this.message = message;
        this.validation = validation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }
}

