package com.example.imhashvapahversion1.version1.Entity.action.area;

import com.example.imhashvapahversion1.version1.Entity.Employee;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "tbl_circle_tax")

public class CircleTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    //Շրջանառության հարկ վճարող տեսակ
    @Column(name = "circle_tax_type", unique = true, nullable = false)
    @NotEmpty(message = "Շրջհարկի տեսակը պարտիդիր պետկ է նշվի")
    private String circleTaxType;

    //Շրջանառության հարկ վճարող
    @Column(name = "circle_tax_payer", unique = true, nullable = false)
    @NotEmpty(message = "Շրջհարկի տեսակը պարտիդիր պետկ է նշվի")
    private String circleTaxPayer;

    //Տնտեսական գործունեության տեսակների դասակարգիչ
    @Column(name = "Class_of_econom_act", unique = false, nullable = false)
    @NotEmpty(message = "Խնդրում ենք  նշել տնտեսական գործունեության տեսակների դասակարգիչ")
    private String circleTaxClassificationOfEconomicActivity;

    //Գործունեության վայր / հասցե
    @Column(name = "action_address", unique = false, nullable = false)
    @NotEmpty(message = "Խնդրում ենք նշել Գործունեության վայր / հասցեն")
    private String circleTaxActionAddress;

    //Գործունեության ոլորտի նկարագրություն
    @Column(name = "circle_tax_type_description", unique = false, nullable = false)
    @NotEmpty(message = "Խնդրում ենք նշել Գործունեության ոլորտի նկարագրություն")
    private String circleTaxTypeDesc;

    public CircleTax() {
    }

    public CircleTax(String circleTaxType, String circleTaxPayer, String circleTaxClassificationOfEconomicActivity, String circleTaxActionAddress, String circleTaxTypeDesc) {
        this.circleTaxType = circleTaxType;
        this.circleTaxPayer = circleTaxPayer;
        this.circleTaxClassificationOfEconomicActivity = circleTaxClassificationOfEconomicActivity;
        this.circleTaxActionAddress = circleTaxActionAddress;
        this.circleTaxTypeDesc = circleTaxTypeDesc;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCircleTaxType() {
        return circleTaxType;
    }

    public void setCircleTaxType(String circleTaxType) {
        this.circleTaxType = circleTaxType;
    }

    public String getCircleTaxPayer() {
        return circleTaxPayer;
    }

    public void setCircleTaxPayer(String circleTaxPayer) {
        this.circleTaxPayer = circleTaxPayer;
    }

    public String getCircleTaxClassificationOfEconomicActivity() {
        return circleTaxClassificationOfEconomicActivity;
    }

    public void setCircleTaxClassificationOfEconomicActivity(String circleTaxClassificationOfEconomicActivity) {
        this.circleTaxClassificationOfEconomicActivity = circleTaxClassificationOfEconomicActivity;
    }

    public String getCircleTaxActionAddress() {
        return circleTaxActionAddress;
    }

    public void setCircleTaxActionAddress(String circleTaxActionAddress) {
        this.circleTaxActionAddress = circleTaxActionAddress;
    }

    public String getCircleTaxTypeDesc() {
        return circleTaxTypeDesc;
    }

    public void setCircleTaxTypeDesc(String circleTaxTypeDesc) {
        this.circleTaxTypeDesc = circleTaxTypeDesc;
    }
}