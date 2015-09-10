package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.service.remote.IntakeGenerationService;
import org.springframework.stereotype.Service;

/**
 * Created by gitmaal on 10/09/2015.
 */
@Service
public class IntakeGenerationServiceImpl implements IntakeGenerationService {
    @Override
    public void intakeGeneration(Candidate candidate) {
        //TODO update user status and generate intake tests
        System.out.println("fork github");
    }
}
