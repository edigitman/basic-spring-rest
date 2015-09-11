package com.test.candidate.service.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * Created by gitmaal on 11/09/2015.
 */
public class ErrorDetail {

    private String type;
    private String message;
    private String url;
    private List<FieldError> fieldErrors = null;

    public ErrorDetail() {
    }

    public ErrorDetail(String type, String message, String url) {
        this.type = type;
        this.message = message;
        this.url = url;
    }

    public ErrorDetail(String type, String message, String url, List<FieldError> fieldErrors) {
        this.type = type;
        this.message = message;
        this.url = url;
        this.fieldErrors = fieldErrors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
