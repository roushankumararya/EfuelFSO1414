package com.developtech.efuelfo.model.requestModel;

import java.util.List;

/**
 * Created by dt on 3/17/18.
 */

public class EmailInvoiceRequestModel {
    List<String> id;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public EmailInvoiceRequestModel(List<String> id) {
        this.id = id;
    }
}
