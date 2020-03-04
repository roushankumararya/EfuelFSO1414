package com.developtech.efuelfo.model.requestModel;

import java.io.Serializable;

/**
 * Created by dt on 1/9/18.
 */

public class GetTransactionRequestModel implements Serializable {
    public String createdById;

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }
}
