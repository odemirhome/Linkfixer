package com.entegman.radio.linkfixer.service.model;

import com.entegman.radio.linkfixer.model.SITEENUM;

public class UpdateLinlkFailureParam {
    int radioId;
    SITEENUM sitenum;

    public int getRadioId() {
        return radioId;
    }

    public void setRadioId(int radioId) {
        this.radioId = radioId;
    }

    public SITEENUM getSitenum() {
        return sitenum;
    }

    public void setSitenum(SITEENUM sitenum) {
        this.sitenum = sitenum;
    }
}
