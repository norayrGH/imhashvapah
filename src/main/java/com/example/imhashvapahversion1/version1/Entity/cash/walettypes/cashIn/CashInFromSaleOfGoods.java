package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.CompanyCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.IndividualCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.Customers.PrivateEntrepreneurCustomer;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.CompanyOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.IndividualOtherPartner;
import com.example.imhashvapahversion1.version1.Entity.partners.otherPartner.PrivateEntrepreneurOtherPartner;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.Valid;

import java.util.Date;

@Entity

public class CashInFromSaleOfGoods implements GetWaletIn {
    @Id
    @GeneratedValue
    private Long id;



    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractDate ;

    private String contractNubmer ;
    @Transient
    private Long customerId;
    @Transient
    private String customerType;
    @NotEmpty(message = "Գնորդի անունը պարտադիր է:")
    private String  customerIndex;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletIn walletIn;
    @ManyToOne
    private Organization organization;

    @ManyToOne
    private CompanyCustomer companyCustomer;
    @ManyToOne
    private IndividualCustomer individualCustomer;
    @ManyToOne
    private PrivateEntrepreneurCustomer privateEntrepreneurCustomer;

    public CashInFromSaleOfGoods() {
    }



    public CashInFromSaleOfGoods(Date contractDate, String contractNubmer, Long customerId, String customerType, String customerIndex, WalletIn walletIn, Organization organization, CompanyCustomer companyCustomer, IndividualCustomer individualCustomer, PrivateEntrepreneurCustomer privateEntrepreneurCustomer) {
        this.contractDate = contractDate;
        this.contractNubmer = contractNubmer;
        this.customerId = customerId;
        this.customerType = customerType;
        this.customerIndex = customerIndex;
        this.walletIn = walletIn;
        this.organization = organization;
        this.companyCustomer = companyCustomer;
        this.individualCustomer = individualCustomer;
        this.privateEntrepreneurCustomer = privateEntrepreneurCustomer;
    }

    @Override
    public Long getCashInId() {
        return id;
    }

    @Override
    public WalletIn getWalletInImpl() {
        return walletIn;
    }
@Override
    public GeneralMethods getSupplier(){
        if(companyCustomer!=null)
            return companyCustomer;
        if(individualCustomer!=null)
            return individualCustomer;
        return privateEntrepreneurCustomer;
    }


    public Long getId() {
        return id;
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

    public String getContractNubmer() {
        return contractNubmer;
    }

    public void setContractNubmer(String contractNubmer) {
        this.contractNubmer = contractNubmer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerIndex() {
        return customerIndex;
    }

    public void setCustomerIndex(String customerIndex) {
        this.customerIndex = customerIndex;
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

    public CompanyCustomer getCompanyCustomer() {
        return companyCustomer;
    }

    public void setCompanyCustomer(CompanyCustomer companyCustomer) {
        this.companyCustomer = companyCustomer;
    }

    public IndividualCustomer getIndividualCustomer() {
        return individualCustomer;
    }

    public void setIndividualCustomer(IndividualCustomer individualCustomer) {
        this.individualCustomer = individualCustomer;
    }

    public PrivateEntrepreneurCustomer getPrivateEntrepreneurCustomer() {
        return privateEntrepreneurCustomer;
    }

    public void setPrivateEntrepreneurCustomer(PrivateEntrepreneurCustomer privateEntrepreneurCustomer) {
        this.privateEntrepreneurCustomer = privateEntrepreneurCustomer;
    }
}
