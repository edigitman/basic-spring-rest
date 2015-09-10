package com.test.candidate.service.remote;

import com.test.candidate.persistence.entity.Candidate;

/**
 * Created by gitmaal on 10/09/2015.
 */
public interface IntakeGenerationService {

    String RMI_NAME = "IntakeGenerationService";

    void intakeGeneration(Candidate candidate);

}
