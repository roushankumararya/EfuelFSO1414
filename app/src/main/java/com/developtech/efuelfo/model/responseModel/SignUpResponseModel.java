package com.developtech.efuelfo.model.responseModel;

import java.io.Serializable;

/**
 * Created by dt on 1/9/18.
 */

public class SignUpResponseModel implements Serializable {

    public String otpId,otpdigits;

    public String getId() {
        return otpId;
    }

    public void setId(String id) {
        this.otpId = id;
    }

   /* public String getOtpdigits(){
        return otpdigits;
    }

   public void setOtpdigits(String otpdnm){
        this.otpdigits=otpdnm;
   }
*/
}
