package com.test.candidate.service.controller;

import com.test.candidate.dto.CandidateDTO;
import com.test.candidate.dto.DeleteIdListDTO;
import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.service.CandidateService;
import com.test.candidate.service.ErrorDetail;
import com.test.candidate.service.exception.CandidateInvalidException;
import com.test.candidate.service.exception.CandidateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by oleg on 12/08/15.
 */
@RestController
@RequestMapping("/candidate")
//TODO
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @ExceptionHandler(CandidateNotFoundException.class)
    public ErrorDetail handleCandidateNotFound(HttpServletRequest request, Exception exception) {
        ErrorDetail error = new ErrorDetail();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getLocalizedMessage());
        error.setUrl(request.getRequestURL().append("/<id>").toString());
        return error;
    }

    @ExceptionHandler(CandidateInvalidException.class)
    public ErrorDetail handleInvalidaData(HttpServletRequest request, Exception exception) {
        ErrorDetail error = new ErrorDetail();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getLocalizedMessage());
        error.setUrl(request.getRequestURL().toString());
        return error;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CandidateDTO> getAll() {

        return CandidateDTO.resolveList(candidateService.getAll());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidateDTO getOne(@PathVariable("id") Long id) throws CandidateNotFoundException {

        Candidate candidate = candidateService.getById(id);
        return new CandidateDTO(candidate);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CandidateDTO modify(@PathVariable("id") Long id, @RequestBody CandidateDTO candidateDTO) throws CandidateNotFoundException, CandidateInvalidException {

        return new CandidateDTO(candidateService.update(id, candidateDTO));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidateDTO create(@RequestBody CandidateDTO candidateDTO) throws CandidateInvalidException {

        return new CandidateDTO(candidateService.create(candidateDTO));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody DeleteIdListDTO deleteIdListDTO) throws CandidateNotFoundException {

        candidateService.delete(deleteIdListDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteMethod(@RequestBody DeleteIdListDTO deleteIdListDTO) throws CandidateNotFoundException {

        candidateService.delete(deleteIdListDTO);
    }
}
