package com.test.candidate.service;

import com.test.candidate.dto.CandidateDTO;
import com.test.candidate.dto.DeleteIdListDTO;
import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.enums.CandidateStatusEnum;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.exception.CandidateInvalidException;
import com.test.candidate.service.exception.CandidateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 12/08/15.
 */
@Service
//TODO
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private NotificationService notificationService;

    public List<Candidate> getAll() {
        List<Candidate> target = new ArrayList<>();
        candidateRepository.findAll().forEach(target::add);
        return target;
    }

    public Candidate getById(Long id) throws CandidateNotFoundException {
        validateId(id);

        return candidateRepository.findOne(id);
    }

    @Transactional
    public Candidate update(Long id, CandidateDTO candidateDTO) throws CandidateNotFoundException, CandidateInvalidException {
        validateId(id);
        validateCandidate(candidateDTO);

        Candidate candidate = candidateRepository.findOne(id);
        candidate.setName(candidateDTO.getName());
        candidate.setEnabled(candidateDTO.getEnabled());

        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate create(CandidateDTO candidateDTO) throws CandidateInvalidException {
        validateCandidate(candidateDTO, "Invalid NewCandidate");

        Candidate candidate = new Candidate();
        candidate.setName(candidateDTO.getName());
        candidate.setEnabled(candidateDTO.getEnabled());
        candidate.setStatus(CandidateStatusEnum.NEW);
        candidate = candidateRepository.save(candidate);

//        notify the other system
        notificationService.notifyNewCandidate(candidate);

        return candidate;
    }

    @Transactional
    public void delete(DeleteIdListDTO deleteIdListDTO) throws CandidateNotFoundException {
        for (Long id : deleteIdListDTO.getIds()) {

            if (!candidateRepository.exists(id)) {
                throw new CandidateNotFoundException("" + id);
            }

            candidateRepository.delete(id);
        }
    }

    private void validateCandidate(CandidateDTO candidateDTO) throws CandidateInvalidException {
        validateCandidate(candidateDTO, null);
    }

    private void validateCandidate(CandidateDTO candidateDTO, String specificMessage) throws CandidateInvalidException {
        if (candidateDTO == null) {
            throw new CandidateInvalidException("object is null");
        }

        List<FieldError> fieldErrors = new ArrayList<>();

        if (StringUtils.isEmpty(candidateDTO.getName())) {
            FieldError fieldError = new FieldError(Candidate.class.getSimpleName(),
                    "name", candidateDTO.getName(), false, null, null, "NotNull");
            fieldErrors.add(fieldError);
        }

        if (candidateDTO.getEnabled() == null) {
            FieldError fieldError = new FieldError(Candidate.class.getSimpleName(),
                    "enabled", candidateDTO.getEnabled(), false, null, null, "NotNull");
            fieldErrors.add(fieldError);
        }

        if (!fieldErrors.isEmpty()) {
            if (specificMessage != null)
                throw new CandidateInvalidException(specificMessage, fieldErrors);

            throw new CandidateInvalidException("Invalid Candidate", fieldErrors);
        }
    }

    private void validateId(Long id) throws CandidateNotFoundException {
        if (id == null || id <= 0)
            throw new CandidateNotFoundException("" + id);

        if (!candidateRepository.exists(id)) {
            throw new CandidateNotFoundException("" + id);
        }
    }
}
