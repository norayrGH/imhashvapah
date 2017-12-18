package com.example.imhashvapahversion1.version1.Entity;
import com.example.imhashvapahversion1.version1.Entity.action.area.CircleTax;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

@Entity
public class Employee  {

    @Id
    @GeneratedValue
    private Long id ;

    @Column(name = "employee_name",unique = true,nullable = false)
    @NotNull
    @Size(min=2, max=30)
    private String employeeName ;

    @Column(name = "employee_hch",unique = true,nullable = false)
    private Long hch ;

    //Գրանցման համար
    @Column(name = "employee_reg_number",unique = true,nullable = true)
    private String registrationNumber;

    //Գրանցման ամսաթիվ
    @Column(name = "employee_reg_date",unique = false,nullable = false)
    private Date registrationDate;

    //Վկայականի համար
    @Column(name = "employee_certificate_num",unique = false,nullable = true)
    private String certificateNumber;

    //հարկ վճարողի հաշվառման համար   ՀՎՀՀ
    @Column(name = "employee_taaxpayer_ident_num",unique = true,nullable = false)
    private int taxpayerIdentificationNumber;



    @NotNull
   @OneToOne(cascade=CascadeType.ALL )
    private CircleTax circleTax;

    //Սկզբն. մնացորդների ամսաթիվ
    //Բոլոր էջերի սկզբնական մնացորդները պետք է լրացվեն ըստ այս ամսաթվի:


    @Column(name="employee_date_of_open_balances",unique = false,nullable = true)
    private Date dateOfOpeningBalances ;

    //Իրավաբանական հասցե
    @Column(name = "employee_juridical_address",unique = false,nullable = false)
    private String juridicalAddress;

    //Գործունեության հասցեներ
    @Column(name = "employee_acting_address",unique = false,nullable = false)
    private String actingAddress;

    public Employee() {
    }

    public Employee(String employeeName, Long hch, String registrationNumber, Date registrationDate, String certificateNumber, int taxpayerIdentificationNumber, CircleTax circleTax, Date dateOfOpeningBalances, String juridicalAddress, String actingAddress) {
        this.employeeName = employeeName;
        this.hch = hch;
        this.registrationNumber = registrationNumber;
        this.registrationDate = registrationDate;
        this.certificateNumber = certificateNumber;
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
        this.circleTax = circleTax;
        this.dateOfOpeningBalances = dateOfOpeningBalances;
        this.juridicalAddress = juridicalAddress;
        this.actingAddress = actingAddress;
    }

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


    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public int getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    public void setTaxpayerIdentificationNumber(int taxpayerIdentificationNumber) {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    public CircleTax getCircleTax() {
        return circleTax;
    }

    public void setCircleTax(CircleTax circleTax) {
        this.circleTax = circleTax;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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


