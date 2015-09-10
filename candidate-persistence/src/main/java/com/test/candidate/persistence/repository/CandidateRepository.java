package com.test.candidate.persistence.repository;

import com.test.candidate.persistence.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by oleg on 09/08/15.
 */
//TODO create CandidateRepository interface
@Transactional
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
