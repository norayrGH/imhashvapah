package com.example.imhashvapahversion1.version1.Entity.partners.otherPartner;

public class OtherPartner {
    private Long id ;
    private String otherPartnerType;
    private String  otherPartnerName;


    public OtherPartner() {
    }

    public OtherPartner(Long id, String otherPartnerType, String otherPartnerName) {
        this.id = id;
        this.otherPartnerType = otherPartnerType;
        this.otherPartnerName = otherPartnerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtherPartnerType() {
        return otherPartnerType;
    }

    public void setOtherPartnerType(String otherPartnerType) {
        this.otherPartnerType = otherPartnerType;
    }

    public String getOtherPartnerName() {
        return otherPartnerName;
    }

    public void setOtherPartnerName(String otherPartnerName) {
        this.otherPartnerName = otherPartnerName;
    }
}
