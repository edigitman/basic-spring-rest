package com.test.candidate.service.remote;

import com.test.candidate.persistence.entity.Candidate;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by gitmaal on 10/09/2015.
 */
public interface IntakeGenerationService extends Remote{

    String RMI_NAME = "IntakeGenerationService";

    void intakeGeneration(Candidate candidate) throws RemoteException;

}
