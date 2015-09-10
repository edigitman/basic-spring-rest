package com.test.candidate.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gitmaal on 10/09/2015.
 */
public class DeleteIdListDTO implements Serializable {

    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
