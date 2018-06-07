package com.example.imhashvapahversion1.version1.Entity.partners.suppliers;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierIndividual;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class IndividualSupplier implements GeneralMethods{
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private SupplierIndividual individual;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ծննդյան ամսաթիվը պարտադիր է:")
    private Date bDate;
    @NotEmpty(message = " ՀԾՀ-ն պարտադիր է:")
    private String hch;
    @NotEmpty(message = "Անձնագրի համարը պարտադիր է:")
    private String passportNumber;
    @NotEmpty(message = "Անձնագրի ում կողմից տրված լինելը պարտադիր է:")
    private String passportViewingDate;
    @NotEmpty(message = "Սկզբնական մնացորդի տեսակը պարտադիր է:")
    private String openingBalanceType;
    @NotEmpty(message = "Սկզբնական մնացորդը պարտադիր է:")
    private String openingBalance;
    @NotEmpty(message = "Հարկավոր է ընտրել մատակարարի տեսակը:")
    private String supply;
    private String address;
    private String phoneNumber;


    @ManyToOne
    private Organization organization;

    public IndividualSupplier() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return individual.getName();
    }

    @Override
    public Long getInnerId() {
        return individual.getId();
    }

    @Override
    public Long getClientOrganizationId() {
        return null;
    }

    @Override
    public Long getIndividualId() {
        return individual.getId();
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SupplierIndividual getIndividual() {
        return individual;
    }

    public void setIndividual(SupplierIndividual individual) {
        this.individual = individual;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public String getHch() {
        return hch;
    }

    public void setHch(String hch) {
        this.hch = hch;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportViewingDate() {
        return passportViewingDate;
    }

    public void setPassportViewingDate(String passportViewingDate) {
        this.passportViewingDate = passportViewingDate;
    }

    public String getOpeningBalanceType() {
        return openingBalanceType;
    }

    public void setOpeningBalanceType(String openingBalanceType) {
        this.openingBalanceType = openingBalanceType;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getHvhh() {
        return null;
    }

    @Override
    public String getType() {
        return "IndividualSupplier";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
