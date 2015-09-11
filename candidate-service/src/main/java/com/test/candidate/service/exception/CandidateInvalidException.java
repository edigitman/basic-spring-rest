package com.test.candidate.service.exception;

/**
 * Created by gitmaal on 11/09/2015.
 */
public class CandidateInvalidException extends Exception {

    private static final long serialVersionUID = 1L;

    public CandidateInvalidException(String key) {
        super(key);
    }
}
