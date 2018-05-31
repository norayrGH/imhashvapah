package com.example.imhashvapahversion1.version1.Entity.partners.service.rent;

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
public class PeriodicServiceRentCar {
    //Անվանում
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Ծառայության անունը չի կարող լինել դատարկ:")
    private String name;



    @NotEmpty(message = "Տեսակը պարտադիր է:")
    private String type;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ամսաթիվը պարտադիր է:")
    private Date startDate;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    //Cost
    private String cost;

    //Մակնիշ
    @NotEmpty(message = "Մակնիշը պարտադիր է:")
    private String model;
    //Պետ. համարանիշ
    @NotEmpty(message = "Պետ. համարանիշ պարտադիր է:")
    private String carNumber;



    //Պայմանագրի ամսաթիվ
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ամսաթիվը պարտադիր է:")
    private Date contractDate;

    //Պայմանագրի համար
    @NotEmpty(message = "Պայմանագրի համարը պարտադիր է:")
    private String contractNumber;

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

    public PeriodicServiceRentCar() {
    }

    public PeriodicServiceRentCar(String name, String type, Date startDate, Date endDate, String cost, String model, String carNumber, Date contractDate, String contractNumber, Long supplierId, String supplierType, String supplierIndex, CompanySupplier companySupplier, IndividualSupplier individualSupplier, PrivateEntrepreneurSupplier privateEntrepreneurSupplier, Organization organization) {
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.model = model;
        this.carNumber = carNumber;
        this.contractDate = contractDate;
        this.contractNumber = contractNumber;
        this.supplierId = supplierId;
        this.supplierType = supplierType;
        this.supplierIndex = supplierIndex;
        this.companySupplier = companySupplier;
        this.individualSupplier = individualSupplier;
        this.privateEntrepreneurSupplier = privateEntrepreneurSupplier;
        this.organization = organization;
    }
    public GeneralMethods getSupplier(){
        if (companySupplier!=null)
            return companySupplier;
        if (individualSupplier!=null)
            return companySupplier;
        if (privateEntrepreneurSupplier!=null)
            return privateEntrepreneurSupplier;
        return null;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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

    public String getSupplierIndex() {
        return supplierIndex;
    }

    public void setSupplierIndex(String supplierIndex) {
        this.supplierIndex = supplierIndex;
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
