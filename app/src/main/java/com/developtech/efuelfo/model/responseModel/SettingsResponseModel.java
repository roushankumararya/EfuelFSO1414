package com.developtech.efuelfo.model.responseModel;

/**
 * Created by dt on 3/20/18.
 */

public class SettingsResponseModel {
    String faqs, disclaimer, licenseAgreement, paymentAgreement, creditAgreement, termAndCondition, privacyPolicy;

    public String getFaqs() {
        return faqs;
    }

    public void setFaqs(String faqs) {
        this.faqs = faqs;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicenseAgreement() {
        return licenseAgreement;
    }

    public void setLicenseAgreement(String licenseAgreement) {
        this.licenseAgreement = licenseAgreement;
    }

    public String getPaymentAgreement() {
        return paymentAgreement;
    }

    public void setPaymentAgreement(String paymentAgreement) {
        this.paymentAgreement = paymentAgreement;
    }

    public String getCreditAgreement() {
        return creditAgreement;
    }

    public void setCreditAgreement(String creditAgreement) {
        this.creditAgreement = creditAgreement;
    }

    public String getTermAndCondition() {
        return termAndCondition;
    }

    public void setTermAndCondition(String termAndCondition) {
        this.termAndCondition = termAndCondition;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }
}
