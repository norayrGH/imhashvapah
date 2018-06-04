package com.example.imhashvapahversion1.version1.Entity.partners.Customers;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
public class Sale implements GetWaletIn {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Համարը պարտադիր է:")
    private String saleNumber;

    @NotEmpty(message = "Տեսակը պարտադիր է:")
    private String typeSale ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date saleDate ;
    @NotEmpty(message = "Գումարը պարտադիր է")
    private String salesAmount;


    private Boolean personalWalletIn;

    private String contractNubmer ;
    //Նշումներ
    private String note;

    @Transient
    private Long customerId;
    @Transient
    private String customerType;
    @NotEmpty(message = "Գնորդի անունը պարտադիր է:")
    private String  customerIndex;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private CompanyCustomer companyCustomer;
    @ManyToOne
    private IndividualCustomer individualCustomer;
    @ManyToOne
    private PrivateEntrepreneurCustomer privateEntrepreneurCustomer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getTypeSale() {
        return typeSale;
    }

    public void setTypeSale(String typeSale) {
        this.typeSale = typeSale;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(String salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Boolean getPersonalWalletIn() {
        return personalWalletIn;
    }

    public void setPersonalWalletIn(Boolean personalWalletIn) {
        this.personalWalletIn = personalWalletIn;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public Long getCashInId() {
        return null;
    }

    @Override
    public WalletIn getWalletInImpl() {
        return null;
    }

    @Override
    public GeneralMethods getSupplier(){
        if(companyCustomer!=null)
            return companyCustomer;
        if(individualCustomer!=null)
            return individualCustomer;
        return privateEntrepreneurCustomer;
    }
}
