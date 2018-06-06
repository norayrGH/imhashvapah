package com.example.imhashvapahversion1.version1.Entity.partners.otherPartner;

import com.example.imhashvapahversion1.version1.Entity.GeneralMethods;
import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.otherPartner.OtherPartnerIndividual;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
public class IndividualOtherPartner  implements GeneralMethods {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private OtherPartnerIndividual individual;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ծննդյան ամսաթիվը պարտադիր է:")
    private Date bDate;
    @NotEmpty(message = "ՀԾՀ-ն պարտադիր է:")
    private String hch;
    @NotEmpty(message = " Անձնագրի համարը պարտադիր է:")
    private String passportNumber;
    @NotEmpty(message = " Անձնագրի ում կողմից տրված լինելը պարտադիր է:")
    private String passportSupplier;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = " Անձնագրի տրման ամսաթիվը պարտադիր է:")
    private Date passportViewingDate;
    @NotEmpty(message = "Սկզբնական մնացորդը պարտադիր է:")
    private String openingBalanceType;
    private String openingBalance;
    @NotEmpty(message = "հասցեն պարտադիր է:")

    private String address;
    private String phoneNumber;

    @ManyToOne
    private Organization organization;

    public IndividualOtherPartner() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public OtherPartnerIndividual getIndividual() {
        return individual;
    }

    public void setIndividual(OtherPartnerIndividual individual) {
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

    public String getOpeningBalanceType() {
        return openingBalanceType;
    }

    public void setOpeningBalanceType(String openingBalanceType) {
        this.openingBalanceType = openingBalanceType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportSupplier() {
        return passportSupplier;
    }

    public void setPassportSupplier(String passportSupplier) {
        this.passportSupplier = passportSupplier;
    }

    public Date getPassportViewingDate() {
        return passportViewingDate;
    }

    public void setPassportViewingDate(Date passportViewingDate) {
        this.passportViewingDate = passportViewingDate;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
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
        return "IndividualOtherPartner";
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
