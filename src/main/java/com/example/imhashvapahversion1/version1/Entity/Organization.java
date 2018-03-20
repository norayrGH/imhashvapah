package com.example.imhashvapahversion1.version1.Entity;

import com.example.imhashvapahversion1.version1.Entity.action.area.CircleTax;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tbl_organization")
public class  Organization  {
    @Id
    @GeneratedValue
    private Long id ;
    //Անվանում
    @Column(name = "organization_name",unique = true,nullable = false)
    @Size(min=2, max=30 , message = " Գրեք ճիշտ Անվանում ")
    @NotEmpty(message = " Անվանում դաշտը հարկավոր է լրացնել")

    private String organizationName ;

    @Column(name = "organization_hch",unique = true,nullable = false)
    @NotNull( message = "ՀՑՀ  դաշտը անպայման է լրացնելու համար ")
    @Pattern(regexp = "([0-9]{10})" ,message = "ՀՑՀ ունի 10 Նիշ")
    private String hch ;

    //Գրանցման համար
    @Column(name = "organization_reg_number",unique = true,nullable = true)
    @Pattern(regexp = "([0-9]{16})" , message = "Գրանցման համարը ունի 16 նիշ")
    private String registrationNumber;

    //Գրանցման ամսաթիվ
    @Column(name = "organization_reg_date",unique = false,nullable = false)
    @NotNull(message = " Գրանցման ամսաթիվ դաշտը պարտադիր է լրացնել ")
    @DateTimeFormat(pattern = "yyyy-MM-dd" , iso = DateTimeFormat.ISO.DATE)
    private Date registrationDate;

    //Վկայականի համար
    @Column(name = "organization_certificate_num",unique = false,nullable = true)
    @Pattern(regexp = "([0-9]{10})", message = "Վկայականի համարը ունի 10 նիշ")
    private String certificateNumber;

    //հարկ վճարողի հաշվառման համար   ՀՎՀՀ
    @Column(name = "organization_taaxpayer_ident_num",unique = true,nullable = false)
    @NotNull(message = "ՀՎՀՀ դաշտը պարտադիր է լրացման համար ")
    @Pattern(regexp = "([0-9]{8})",message = "ՀՎՀՀ համարը ունի 8 նիշ")
    private String taxpayerIdentificationNumber;


    //Սկզբն. մնացորդների ամսաթիվ
    //Բոլոր էջերի սկզբնական մնացորդները պետք է լրացվեն ըստ այս ամսաթվի:
    @Column(name="organization_date_of_open_balances",unique = false,nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Սկզբնական մնացորդի ամսաթիվը պետկ է լրացնել")
    private Date dateOfOpeningBalances ;

    //Գործունեության հասցե
    @NotEmpty(message = " Գործունեության հասցեն պարտադիր է լրացման համար")
    @Column(name = "organization_acting_address",unique = false,nullable = false)
    @Size(min = 10 , message = "Գործունեության հասցեն հարկավոր է լրացնել ")
    private String actingAddress;

    //Իրավաբանական հասցե
    @NotEmpty(message = "Իրավաբանական հասցե պարտադիր է լրացման համար ")
    @Column(name = "organization_juridical_address",unique = false,nullable = false)
    private String juridicalAddress;




    @OneToOne(cascade=CascadeType.ALL )
    @Valid
    private CircleTax circleTax;





    public Organization() {

    }

    public Organization(String organizationName, String hch, String registrationNumber, Date registrationDate, String certificateNumber, String taxpayerIdentificationNumber, Date dateOfOpeningBalances, String juridicalAddress, String actingAddress, CircleTax circleTax) {
        this.organizationName = organizationName;
        this.hch = hch;
        this.registrationNumber = registrationNumber;
        this.registrationDate = registrationDate;
        this.certificateNumber = certificateNumber;
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
        this.dateOfOpeningBalances = dateOfOpeningBalances;
        this.juridicalAddress = juridicalAddress;
        this.actingAddress = actingAddress;
        this.circleTax = circleTax;

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getHch() {
        return hch;
    }

    public void setHch(String hch) {
        this.hch = hch;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    public Date getDateOfOpeningBalances() {
        return dateOfOpeningBalances;
    }

    public void setDateOfOpeningBalances(Date dateOfOpeningBalances) {
        this.dateOfOpeningBalances = dateOfOpeningBalances;
    }

    public String getJuridicalAddress() {
        return juridicalAddress;
    }

    public void setJuridicalAddress(String juridicalAddress) {
        this.juridicalAddress = juridicalAddress;
    }

    public String getActingAddress() {
        return actingAddress;
    }

    public void setActingAddress(String actingAddress) {
        this.actingAddress = actingAddress;
    }

    public CircleTax getCircleTax() {
        return circleTax;
    }

    public void setCircleTax(CircleTax circleTax) {
        this.circleTax = circleTax;
    }
}



