package com.example.imhashvapahversion1.version1.Entity.partners.purchase;

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
public class PurchaseService {
    @Id
    @GeneratedValue
    private Long id;

    //№
    @NotEmpty(message = "Համարը պարտադիր է:")
    private String purchaseNumber;
    @NotEmpty(message = "Ծառայությունը պարտադիր է: ")
    private String serviceName;

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

    //Մատակարարման ամսաթիվ
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ամսաթիվը պարտադիր է:")
    private Date supplerDate;

    //Ձեռքբերման գումար
    @NotNull(message = "Գումարը պարտադիր է:")
    private Integer amountOfReceipts;


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

}