package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.enums.CandidateStatusEnum;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.remote.IntakeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.RemoteException;

/**
 * Created by gitmaal on 10/09/2015.
 */
@Service
public class NotificationService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    @Qualifier("rmiIntakeGenerationService")
    private IntakeGenerationService intakeGenerationService;

    @Async
    @Transactional
    public void notifyNewCandidate(Candidate candidate) {

        candidate.setStatus(CandidateStatusEnum.SEND_TO_PROCESS);
        candidateRepository.save(candidate);

        try {
            intakeGenerationService.intakeGeneration(candidate);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
