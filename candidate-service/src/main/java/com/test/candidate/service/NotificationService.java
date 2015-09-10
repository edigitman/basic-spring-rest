package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.enums.CandidateStatusEnum;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.remote.IntakeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by gitmaal on 10/09/2015.
 */
@Service
public class NotificationService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private IntakeGenerationService intakeGenerationService;

    @Async
    @Transactional
    public void notifyNewCandidate(Candidate candidate) {

        candidate.setStatus(CandidateStatusEnum.SEND_TO_PROCESS);
        candidateRepository.save(candidate);

        intakeGenerationService.intakeGeneration(candidate);
    }

}
