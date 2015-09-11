package com.test.candidate.service;

import com.test.candidate.dto.CandidateDTO;
import com.test.candidate.dto.DeleteIdListDTO;
import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.enums.CandidateStatusEnum;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.exception.CandidateInvalidException;
import com.test.candidate.service.exception.CandidateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        validateCandidate(candidateDTO);

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
            try {
                candidateRepository.delete(id);
            } catch (EmptyResultDataAccessException exception) {
                throw new CandidateNotFoundException("" + id);
            }
        }
    }

    private void validateCandidate(CandidateDTO candidateDTO) throws CandidateInvalidException {
        if (candidateDTO == null) {
            throw new CandidateInvalidException("object is null");
        }

        if (StringUtils.isEmpty(candidateDTO.getName())) {
            throw new CandidateInvalidException("name is null for cancidate id " + candidateDTO.getId());
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
