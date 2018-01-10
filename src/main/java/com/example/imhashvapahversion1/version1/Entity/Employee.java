package com.example.imhashvapahversion1.version1.Entity;
import com.example.imhashvapahversion1.version1.Entity.action.area.CircleTax;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "tbl_employee")
public class Employee  {

    @Id
    @GeneratedValue
    private Long id ;
    //Անվանում
    @Column(name = "employee_name",unique = true,nullable = false)
    @NotNull(message = " Անվանում դաշտը հարկավոր է լրացնել")
    @Size(min=2, max=30 , message = " Գրեք ճիշտ Անվանում ")
    private String employeeName ;

    @Column(name = "employee_hch",unique = true,nullable = false)
    @NotNull( message = "ՀՑՀ  դաշտը անպայման է լրացնելու համար ")
    @Min(value = 10,message = "ՀՑՀ ունի 10 թիվ")
    private Long hch ;

    //Գրանցման համար
    @Column(name = "employee_reg_number",unique = true,nullable = true)
    @Min(value=16 , message = "Գրանցման համարը ունի 16 նիշ")
    private String registrationNumber;

    //Գրանցման ամսաթիվ
    @Column(name = "employee_reg_date",unique = false,nullable = false)
    @NotNull(message = " Գրանցման ամսաթիվ դաշտը պարտադիր է լրացնել ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    //Վկայականի համար
    @Column(name = "employee_certificate_num",unique = false,nullable = true)
    @Min(value=10 , message = "Վկայականի համարը ունի 10 նիշ")
    private String certificateNumber;

    //հարկ վճարողի հաշվառման համար   ՀՎՀՀ
    @Column(name = "employee_taaxpayer_ident_num",unique = true,nullable = false)
    @Min(value=8 , message = "ՀՎՀՀ համարը ունի 8 նիշ")
    @NotNull(message = "ՀՎՀՀ դաշտը պարտադիր է լրացման համար ")
    private String taxpayerIdentificationNumber;


    //Սկզբն. մնացորդների ամսաթիվ
    //Բոլոր էջերի սկզբնական մնացորդները պետք է լրացվեն ըստ այս ամսաթվի:
    @Column(name="employee_date_of_open_balances",unique = false,nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Սկզբնական մնացորդի ամսաթիվը պետկ է լրացնել")
    private Date dateOfOpeningBalances ;

    //Իրավաբանական հասցե
    @Column(name = "employee_juridical_address",unique = false,nullable = false)
    @NotNull(message = "Իրավաբանական հասցե պարտադիր է լրացման համար ")
    private String juridicalAddress;

    //Գործունեության հասցե
    @Column(name = "employee_acting_address",unique = false,nullable = false)
    @NotNull(message = "Գործունեության հասցեն պարտադիր է լրացման համար ")
    private String actingAddress;


    @NotNull
    @OneToOne(cascade=CascadeType.ALL )
    @Valid
    private CircleTax circleTax;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getHch() {
        return hch;
    }

    public void setHch(Long hch) {
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

    public CircleTax getCircleTax() {
        return circleTax;
    }

    public void setCircleTax(CircleTax circleTax) {
        this.circleTax = circleTax;
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

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + id +
            ", employeeName='" + employeeName + '\'' +
            ", hch=" + hch +
            ", registrationNumber='" + registrationNumber + '\'' +
            ", registrationDate=" + registrationDate +
            ", certificateNumber='" + certificateNumber + '\'' +
            ", taxpayerIdentificationNumber=" + taxpayerIdentificationNumber +
            ", circleTax=" + circleTax +
            ", dateOfOpeningBalances=" + dateOfOpeningBalances +
            ", juridicalAddress='" + juridicalAddress + '\'' +
            ", actingAddress='" + actingAddress + '\'' +
            '}';
}
}


