package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForLoanPayment {

    @Id
    @GeneratedValue
    private Long id;

    private String otherPartnerName;

    private String bankCommissions;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;

    @Transient
    private String otherPartnerType;
    @Transient
    private Long otherPartnerId;
    @ManyToOne
    CompanyOtherPartner companyOtherPartner;
    @ManyToOne
    IndividualOtherPartner individualOtherPartner;
    @ManyToOne
    PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner;
    @ManyToOne
    private Organization organization;

    public CashOutForLoanPayment() {
    }

    public CashOutForLoanPayment(String otherPartnerName, String bankCommissions, WalletOut walletOut, String otherPartnerType, Long otherPartnerId, CompanyOtherPartner companyOtherPartner, IndividualOtherPartner individualOtherPartner, PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner, Organization organization) {
        this.otherPartnerName = otherPartnerName;
        this.bankCommissions = bankCommissions;
        this.walletOut = walletOut;
        this.otherPartnerType = otherPartnerType;
        this.otherPartnerId = otherPartnerId;
        this.companyOtherPartner = companyOtherPartner;
        this.individualOtherPartner = individualOtherPartner;
        this.privateEntrepreneurOtherPartner = privateEntrepreneurOtherPartner;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtherPartnerName() {
        return otherPartnerName;
    }

    public void setOtherPartnerName(String otherPartnerName) {
        this.otherPartnerName = otherPartnerName;
    }

    public String getBankCommissions() {
        return bankCommissions;
    }

    public void setBankCommissions(String bankCommissions) {
        this.bankCommissions = bankCommissions;
    }

    public WalletOut getWalletOut() {
        return walletOut;
    }

    public void setWalletOut(WalletOut walletOut) {
        this.walletOut = walletOut;
    }

    public String getOtherPartnerType() {
        return otherPartnerType;
    }

    public void setOtherPartnerType(String otherPartnerType) {
        this.otherPartnerType = otherPartnerType;
    }

    public Long getOtherPartnerId() {
        return otherPartnerId;
    }

    public void setOtherPartnerId(Long otherPartnerId) {
        this.otherPartnerId = otherPartnerId;
    }

    public CompanyOtherPartner getCompanyOtherPartner() {
        return companyOtherPartner;
    }

    public void setCompanyOtherPartner(CompanyOtherPartner companyOtherPartner) {
        this.companyOtherPartner = companyOtherPartner;
    }

    public IndividualOtherPartner getIndividualOtherPartner() {
        return individualOtherPartner;
    }

    public void setIndividualOtherPartner(IndividualOtherPartner individualOtherPartner) {
        this.individualOtherPartner = individualOtherPartner;
    }

    public PrivateEntrepreneurOtherPartner getPrivateEntrepreneurOtherPartner() {
        return privateEntrepreneurOtherPartner;
    }

    public void setPrivateEntrepreneurOtherPartner(PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner) {
        this.privateEntrepreneurOtherPartner = privateEntrepreneurOtherPartner;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
