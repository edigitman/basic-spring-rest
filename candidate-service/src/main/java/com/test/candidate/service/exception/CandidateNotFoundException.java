package com.test.candidate.service.exception;

/**
 * Created by gitmaal on 10/09/2015.
 */
public class CandidateNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CandidateNotFoundException(String key) {
        super("candidate id [" + key + "] not available");
    }
}
