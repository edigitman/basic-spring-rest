package com.test.candidate.service.exception;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gitmaal on 11/09/2015.
 */
public class CandidateInvalidException extends Exception {

    private static final long serialVersionUID = 1L;

    private List<FieldError> errorList = null;

    public CandidateInvalidException(String key, List<FieldError> fieldErrors) {
        super(key);
        this.errorList = fieldErrors;
    }

    public CandidateInvalidException(String key, FieldError fieldError) {
        super(key);
        this.errorList = new ArrayList<>();
        this.errorList.add(fieldError);
    }

    public CandidateInvalidException(String key) {
        super(key);
    }

    public List<FieldError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<FieldError> errorList) {
        this.errorList = errorList;
    }
}
