package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletOut;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForCreditPayment implements GetWaletOut {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Գործընկերը պարտադիր է:")
    private String otherPartnerIndex;


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
    IndividualOtherPartner  individualOtherPartner;
    @ManyToOne
    PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner;
    @ManyToOne
    private Organization organization;
    public CashOutForCreditPayment() {
    }

    public CashOutForCreditPayment(String otherPartnerIndex, WalletOut walletOut, String otherPartnerType, Long otherPartnerId, CompanyOtherPartner companyOtherPartner, IndividualOtherPartner individualOtherPartner, PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner, Organization organization) {
        this.otherPartnerIndex = otherPartnerIndex;
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

    public String getOtherPartnerIndex() {
        return otherPartnerIndex;
    }

    public void setOtherPartnerIndex(String otherPartnerIndex) {
        this.otherPartnerIndex = otherPartnerIndex;
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
    @Override
    public Long getCashOutId() {
        return id;
    }

    @Override
    public WalletOut getWalletOutImpl() {
        return walletOut;
    }

    @Override
    public GeneralMethods getSupplier(){
        if(companyOtherPartner!=null)
            return companyOtherPartner;
        if(individualOtherPartner!=null)
            return individualOtherPartner;
        return privateEntrepreneurOtherPartner;
    }
}
