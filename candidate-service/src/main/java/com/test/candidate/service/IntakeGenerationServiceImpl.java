package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.enums.CandidateStatusEnum;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.remote.IntakeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gitmaal on 10/09/2015.
 */
@Service
public class IntakeGenerationServiceImpl implements IntakeGenerationService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public void intakeGeneration(Candidate candidate) {
        //TODO update user status and generate intake tests

        candidate.setStatus(CandidateStatusEnum.PROCESSED);
        candidateRepository.save(candidate);

        System.out.println("fork github");
    }
}
