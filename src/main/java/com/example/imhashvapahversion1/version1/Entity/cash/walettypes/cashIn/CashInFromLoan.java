package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class CashInFromLoan implements GetWaletIn  {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Հարկավոր է նշել պայմանագրի ամսաթիվը:")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractDate;
    @NotEmpty(message = "Հարկավոր է նշել պայմանագրի համարը:")
    private String forContract;

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

    public CashInFromLoan() {
    }

    public CashInFromLoan(Date contractDate, String forContract, Long otherPartnerId, String otherPartnerType, String otherPartnerIndex, WalletIn walletIn, Organization organization, CompanyOtherPartner companyOtherPartner, IndividualOtherPartner individualOtherPartner, PrivateEntrepreneurOtherPartner privateEntrepreneurOtherPartner) {
        this.contractDate = contractDate;
        this.forContract = forContract;
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


    public GeneralMethods getSupplier(){
        if(companyOtherPartner!=null)
            return companyOtherPartner;
        if(individualOtherPartner!=null)
            return individualOtherPartner;
        return privateEntrepreneurOtherPartner;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getForContract() {
        return forContract;
    }

    public void setForContract(String forContract) {
        this.forContract = forContract;
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

    @Override
    public Long getCashInId() {
        return id;
    }

    @Override
    public WalletIn getWalletInImpl() {
        return walletIn;
    }
}