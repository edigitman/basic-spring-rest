package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.remote.IntakeGenerationService;

/**
 * Created by gitmaal on 10/09/2015.
 */
public class IntakeGenerationServiceImpl implements IntakeGenerationService {
    @Override
    public void intakeGeneration(Candidate candidate) {
        //TODO update user status and generate intake tests
        System.out.println("fork github");
    }
}
