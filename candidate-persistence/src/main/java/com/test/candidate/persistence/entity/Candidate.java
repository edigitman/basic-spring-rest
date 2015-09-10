package com.test.candidate.persistence.entity;

import com.test.candidate.persistence.enums.CandidateStatusEnum;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by oleg on 09/08/15.
 */
//TODO map entity using JPA annotations
@Entity
@Table(name = "candidate")
public class Candidate extends AbstractPersistable<Long> {


    private String name;
    private Boolean enabled;
    private CandidateStatusEnum status;

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
