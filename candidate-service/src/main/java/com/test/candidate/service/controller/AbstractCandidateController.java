package com.test.candidate.service.controller;

import com.test.candidate.service.exception.CandidateInvalidException;
import com.test.candidate.service.exception.CandidateNotFoundException;
import com.test.candidate.service.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gitmaal on 11/09/2015.
 */
public class AbstractCandidateController {

    @ExceptionHandler(CandidateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handleCandidateNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorDetail("InvalidRequestException", exception.getLocalizedMessage(), request.getRequestURL().toString());
    }

    @ExceptionHandler(CandidateInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handleInvalidaData(HttpServletRequest request, Exception exception) {
        return new ErrorDetail("InvalidRequestException", exception.getLocalizedMessage(), request.getRequestURL().toString(), ((CandidateInvalidException) exception).getErrorList());
    }

}
