package com.alpdurmaz.presentation.restservice.models.restmodels;

import java.util.List;

public class ApiError {
    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}