package com.test.candidate.service;

import com.test.candidate.dto.CandidateDTO;
import com.test.candidate.dto.DeleteIdListDTO;
import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.enums.CandidateStatusEnum;
import com.test.candidate.persistence.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Transactional
    public Candidate update(Long id, CandidateDTO candidateDTO) throws Exception {
        validateCandidate(id);

        Candidate candidate = candidateRepository.findOne(id);
        candidate.setName(candidateDTO.getName());
        candidate.setEnabled(candidateDTO.getEnabled());

        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate create(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate();
        candidate.setName(candidateDTO.getName());
        candidate.setEnabled(candidateDTO.getEnabled());
        candidate.setStatus(CandidateStatusEnum.NEW);
        candidate = candidateRepository.save(candidate);

        //notify the other system
        notificationService.notifyNewCandidate(candidate);

        return candidate;
    }

    @Transactional
    public void delete(DeleteIdListDTO deleteIdListDTO) {
        for (Long id : deleteIdListDTO.getIds()) {
            candidateRepository.delete(id);
        }
    }

    private void validateCandidate(Long id) throws Exception {
        if (id == null || id > 0)
            throw new Exception();

        if (!candidateRepository.exists(id)) {
            throw new Exception();
        }
    }
}
