package com.developtech.efuelfo.model.requestModel;

import java.io.Serializable;

/**
 * Created by dt on 1/9/18.
 */

public class ChangePasswordRequestModel implements Serializable {
    public String oldPassword, newPassword, oldPasscode, newPasscode;

    public String getOldPasscode() {
        return oldPasscode;
    }

    public void setOldPasscode(String oldPasscode) {
        this.oldPasscode = oldPasscode;
    }

    public String getNewPasscode() {
        return newPasscode;
    }

    public void setNewPasscode(String newPasscode) {
        this.newPasscode = newPasscode;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
