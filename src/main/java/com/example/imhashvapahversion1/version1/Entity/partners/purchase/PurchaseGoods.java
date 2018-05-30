package com.example.imhashvapahversion1.version1.Entity.partners.purchase;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
public class PurchaseGoods {
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
    private String docType ;

    //Փաստաթուղթ
    @NotNull(message = "սերիա-համարը պարտադիր է:")
    private String docNumber;

    //Մատակարարման ամսաթիվ
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message ="Ամսաթիվը պարտադիր է:")
    private Date supplerDate;

    //Ձեռքբերման գումար
    @NotNull(message = "Գումարը պարտադիր է:")
    private Integer amountOfReceipts;

    //Վաճառքի ենթադրյալ գումար
    @NotNull(message = "Գումարը պարտադիր է:")
    private Integer sellingValue;

    //Ելք անել անձնական դրամապանակից
    private Boolean personalWalletOut;

    //Նշումներ
    private String note;
    private String purchaseType;
    @Transient
    private Long supplierId;
    @Transient
    private String supplierType;
    @Transient
    @NotEmpty(message = "Մատակարարը պարտադիր է:")
    private String  supplierIndex;
    @ManyToOne
    private CompanySupplier companySupplier;
    @ManyToOne
    private IndividualSupplier individualSupplier;
    @ManyToOne
    private PrivateEntrepreneurSupplier privateEntrepreneurSupplier;
    @ManyToOne
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getSupplerDate() {
        return supplerDate;
    }

    public void setSupplerDate(Date supplerDate) {
        this.supplerDate = supplerDate;
    }

    public Integer getAmountOfReceipts() {
        return amountOfReceipts;
    }

    public void setAmountOfReceipts(Integer amountOfReceipts) {
        this.amountOfReceipts = amountOfReceipts;
    }

    public Integer getSellingValue() {
        return sellingValue;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public void setSellingValue(Integer sellingValue) {
        this.sellingValue = sellingValue;
    }

    public Boolean getPersonalWalletOut() {
        return personalWalletOut;
    }

    public void setPersonalWalletOut(Boolean personalWalletOut) {
        this.personalWalletOut = personalWalletOut;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getSupplierIndex() {
        return supplierIndex;
    }

    public void setSupplierIndex(String supplierIndex) {
        this.supplierIndex = supplierIndex;
    }
}
