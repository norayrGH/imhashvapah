package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class CashInFromCredit implements GetWaletIn{

    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private Long otherPartnerId;
    @Transient
    private String otherPartnerType;

    @NotEmpty(message = "Հարկավոր է ընտրել գործընկերոջը:")
    private String  otherPartnerIndex;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletIn walletIn;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private CompanyOtherPartner companyOtherPartner;
    @ManyToOne
    private IndividualOtherPartner individualOtherPartner;
    @ManyToOne
    private PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner;

    public CashInFromCredit() {
    }

    public CashInFromCredit(Long otherPartnerId, String otherPartnerType, String otherPartnerIndex, WalletIn walletIn, Organization organization, CompanyOtherPartner companyOtherPartner, IndividualOtherPartner individualOtherPartner, PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner) {
        this.otherPartnerId = otherPartnerId;
        this.otherPartnerType = otherPartnerType;
        this.otherPartnerIndex = otherPartnerIndex;
        this.walletIn = walletIn;
        this.organization = organization;
        this.companyOtherPartner = companyOtherPartner;
        this.individualOtherPartner = individualOtherPartner;
        this.privateEntrepreneurOtherPartner = privateEntrepreneurOtherPartner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOtherPartnerId() {
        return otherPartnerId;
    }

    public void setOtherPartnerId(Long otherPartnerId) {
        this.otherPartnerId = otherPartnerId;
    }

    public String getOtherPartnerType() {
        return otherPartnerType;
    }

    public void setOtherPartnerType(String otherPartnerType) {
        this.otherPartnerType = otherPartnerType;
    }

    public String getOtherPartnerIndex() {
        return otherPartnerIndex;
    }

    public void setOtherPartnerIndex(String otherPartnerIndex) {
        this.otherPartnerIndex = otherPartnerIndex;
    }

    public WalletIn getWalletIn() {
        return walletIn;
    }

    public void setWalletIn(WalletIn walletIn) {
        this.walletIn = walletIn;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
    public GeneralMethods getSupplier(){
        if(companyOtherPartner!=null)
            return companyOtherPartner;
        if(individualOtherPartner!=null)
            return individualOtherPartner;
        return privateEntrepreneurOtherPartner;
    }

    @Override
    public Long getCashInId() {
        return id;
    }

    @Override
    public WalletIn getWalletInImpl() {
        return walletIn;
    }
}
