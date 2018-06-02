package com.example.imhashvapahversion1.version1.Entity.partners.purchase;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class PurchaseFixedAsset {
    @Id
    @GeneratedValue
    private Long id;

    //№
    @NotEmpty(message = "Համարը պարտադիր է:")
    private String purchaseNumber;
    //Տեսակ
    private String purchaseType;
    //Մատակարար

    // Ամսաթիվ
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ամսաթիվը պարտադիր է")
    private Date purchaseDate;

    //Փաստաթուղթ
    private String docType ;

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

    @ManyToOne
    private CompanySupplier companySupplier;
    @ManyToOne
    private IndividualSupplier individualSupplier;
    @ManyToOne
    private PrivateEntrepreneurSupplier privateEntrepreneurSupplier;
    @ManyToOne
    private Organization organization;

    public PurchaseFixedAsset() {
    }

    public PurchaseFixedAsset(String purchaseNumber, String purchaseType, Date purchaseDate, String docType, String docNumber, Integer amountOfReceipts, Boolean personalWalletOut, Date contractDate, String contractNumber, String note, String supplierIndex, Long supplierId, String supplierType, CompanySupplier companySupplier, IndividualSupplier individualSupplier, PrivateEntrepreneurSupplier privateEntrepreneurSupplier, Organization organization) {
        this.purchaseNumber = purchaseNumber;
        this.purchaseType = purchaseType;
        this.purchaseDate = purchaseDate;
        this.docType = docType;
        this.docNumber = docNumber;
        this.amountOfReceipts = amountOfReceipts;
        this.personalWalletOut = personalWalletOut;
        this.contractDate = contractDate;
        this.contractNumber = contractNumber;
        this.note = note;
        this.supplierIndex = supplierIndex;
        this.supplierId = supplierId;
        this.supplierType = supplierType;
        this.companySupplier = companySupplier;
        this.individualSupplier = individualSupplier;
        this.privateEntrepreneurSupplier = privateEntrepreneurSupplier;
        this.organization = organization;
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

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
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
}
