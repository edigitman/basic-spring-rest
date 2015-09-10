package com.test.candidate.dto;

import com.test.candidate.persistence.entity.Candidate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gitmaal on 10/09/2015.
 */
public class CandidateDTO implements Serializable {

    private String name;
    private Boolean enabled;

    public CandidateDTO(Candidate candidate) {
        this.name = candidate.getName();
        this.enabled = candidate.getEnabled();
    }

    public static List<CandidateDTO> resolveList(List<Candidate> list) {
        List<CandidateDTO> result = new ArrayList<>();
        for (Candidate candidate : list) {
            result.add(new CandidateDTO(candidate));
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Candidate getCandidate() {
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setEnabled(enabled);
        return candidate;
    }
}
