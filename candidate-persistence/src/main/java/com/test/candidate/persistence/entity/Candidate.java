package com.test.candidate.persistence.entity;

import com.test.candidate.persistence.enums.CandidateStatusEnum;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Created by oleg on 09/08/15.
 */
//TODO map entity using JPA annotations
@Entity
@Table(name = "candidates")
public class Candidate extends AbstractPersistable<Long> {

    private Long id;
    private String name;
    private Boolean enabled;
    private CandidateStatusEnum status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", length = 30)
    @NotNull(message = "First name is compulsory")
    @NotBlank(message = "First name is compulsory")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "enabled")
    @NotNull
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public CandidateStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CandidateStatusEnum status) {
        this.status = status;
    }
}
