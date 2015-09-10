package com.test.candidate.service;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gitmaal on 10/09/2015.
 */
public class CandidateException extends Exception {

    public static final String ID_NOT_FOUND = "ID not found";
    public static final String INVALID_ID = "Invalid ID";
    public static final String NOT_NULL = "NotNull";

    enum Type {
        InvalidRequestException, DataNotFoundException
    }

    private Type type;
    private String message;
    private String resource;
    private List<FieldError> fieldErrors = new ArrayList<>();


    public CandidateException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public CandidateException withFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
        return this;
    }

    public CandidateException withResource(String resource) {
        this.resource = resource;
        return this;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
