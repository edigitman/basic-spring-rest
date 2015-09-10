package com.test.candidate.service.controller;

import com.test.candidate.dto.CandidateDTO;
import com.test.candidate.dto.DeleteIdListDTO;
import com.test.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CandidateDTO> getAll() {
        return CandidateDTO.resolveList(candidateService.getAll());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CandidateDTO modify(@PathParam("id") Long id, @RequestBody CandidateDTO candidateDTO) throws Exception {
        return new CandidateDTO(candidateService.update(id, candidateDTO));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CandidateDTO create(@RequestBody CandidateDTO candidateDTO) throws Exception {
        return new CandidateDTO(candidateService.create(candidateDTO));
    }

    @RequestMapping(name = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody DeleteIdListDTO deleteIdListDTO) throws Exception {
        candidateService.delete(deleteIdListDTO);
    }


}
