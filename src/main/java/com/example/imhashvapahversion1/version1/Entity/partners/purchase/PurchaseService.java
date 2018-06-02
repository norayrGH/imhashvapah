package com.example.imhashvapahversion1.version1.Entity.partners.purchase;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.partners.service.PeriodicService;
import com.example.imhashvapahversion1.version1.Entity.partners.service.rent.PeriodicServiceRentArea;
import com.example.imhashvapahversion1.version1.Entity.partners.service.rent.PeriodicServiceRentCar;
import com.example.imhashvapahversion1.version1.Entity.partners.service.rent.PeriodicServiceRentOther;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class PurchaseService {
    @Id
    @GeneratedValue
    private Long id;

    //№
    @NotEmpty(message = "Համարը պարտադիր է:")
    private String purchaseNumber;


    // Ամսաթիվ
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ամսաթիվը պարտադիր է")
    private Date purchaseDate;

    //Փաստաթուղթ
    private String docType;

    //Փաստաթուղթ
    @NotNull(message = "սերիա-համարը պարտադիր է:")
    private String docNumber;


    //Ձեռքբերման գումար
    @NotNull(message = "Գումարը պարտադիր է:")
    private Integer amountOfReceipts;

    //Ելք անել անձնական դրամապանակից
    private Boolean personalWalletOut;
    //Պայմանագրի ամսաթիվ
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractDate;
    //Պայմանագրի համար
    private String contractNumber;
    //Նշումներ
    private String note;
    @Transient
    @NotEmpty(message = "Մատակարարը պարտադիր է:")
    private String  supplierIndex;
    @Transient
    private Long supplierId;
    @Transient
    private String supplierType;
    @Transient
    @NotEmpty(message = "Ծառայությունը պարտադիր է:")
    private String  serviceIndex;
    @Transient
    private Long serviceId;
    @Transient

    private String serviceType;



    @ManyToOne
    private CompanySupplier companySupplier;
    @ManyToOne
    private IndividualSupplier individualSupplier;
    @ManyToOne
    private PrivateEntrepreneurSupplier privateEntrepreneurSupplier;
    @ManyToOne
    private Organization organization;

    @ManyToOne
    private PeriodicService periodicService;
    @ManyToOne
    private PeriodicServiceRentArea periodicServiceRentArea;
    @ManyToOne
    private PeriodicServiceRentCar periodicServiceRentCar;
    @ManyToOne
    private PeriodicServiceRentOther periodicServiceRentOther;

    public PurchaseService() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeneralMethods getSupplier(){
        if(companySupplier!=null)
            return companySupplier;
        if(individualSupplier!=null)
            return individualSupplier;
        return privateEntrepreneurSupplier;
    }
    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Integer getAmountOfReceipts() {
        return amountOfReceipts;
    }

    public void setAmountOfReceipts(Integer amountOfReceipts) {
        this.amountOfReceipts = amountOfReceipts;
    }

    public Boolean getPersonalWalletOut() {
        return personalWalletOut;
    }

    public void setPersonalWalletOut(Boolean personalWalletOut) {
        this.personalWalletOut = personalWalletOut;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSupplierIndex() {
        return supplierIndex;
    }

    public void setSupplierIndex(String supplierIndex) {
        this.supplierIndex = supplierIndex;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getServiceIndex() {
        return serviceIndex;
    }

    public void setServiceIndex(String serviceIndex) {
        this.serviceIndex = serviceIndex;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public CompanySupplier getCompanySupplier() {
        return companySupplier;
    }

    public void setCompanySupplier(CompanySupplier companySupplier) {
        this.companySupplier = companySupplier;
    }

    public IndividualSupplier getIndividualSupplier() {
        return individualSupplier;
    }

    public void setIndividualSupplier(IndividualSupplier individualSupplier) {
        this.individualSupplier = individualSupplier;
    }

    public PrivateEntrepreneurSupplier getPrivateEntrepreneurSupplier() {
        return privateEntrepreneurSupplier;
    }

    public void setPrivateEntrepreneurSupplier(PrivateEntrepreneurSupplier privateEntrepreneurSupplier) {
        this.privateEntrepreneurSupplier = privateEntrepreneurSupplier;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public PeriodicService getPeriodicService() {
        return periodicService;
    }

    public void setPeriodicService(PeriodicService periodicService) {
        this.periodicService = periodicService;
    }

    public PeriodicServiceRentArea getPeriodicServiceRentArea() {
        return periodicServiceRentArea;
    }

    public void setPeriodicServiceRentArea(PeriodicServiceRentArea periodicServiceRentArea) {
        this.periodicServiceRentArea = periodicServiceRentArea;
    }

    public PeriodicServiceRentCar getPeriodicServiceRentCar() {
        return periodicServiceRentCar;
    }

    public void setPeriodicServiceRentCar(PeriodicServiceRentCar periodicServiceRentCar) {
        this.periodicServiceRentCar = periodicServiceRentCar;
    }

    public PeriodicServiceRentOther getPeriodicServiceRentOther() {
        return periodicServiceRentOther;
    }

    public void setPeriodicServiceRentOther(PeriodicServiceRentOther periodicServiceRentOther) {
        this.periodicServiceRentOther = periodicServiceRentOther;
    }
}
