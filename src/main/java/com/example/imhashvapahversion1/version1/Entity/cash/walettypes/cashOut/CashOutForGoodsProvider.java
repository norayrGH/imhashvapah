package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierClientOrganization;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.formHelpClasses.supplier.SupplierIndividual;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.CompanySupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.IndividualSupplier;
import com.example.imhashvapahversion1.version1.Entity.partners.suppliers.PrivateEntrepreneurSupplier;
import sun.text.SupplementaryCharacterData;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class CashOutForGoodsProvider {
    @Id
    @GeneratedValue
    private Long id;
    //Ամսաթիվ
    @NotNull(message = "հարկավոր է նշել Ելքի ամսաթիվը")
    private Date outDate;
    //Ելքի գումար
    @NotNull(message = "հարկավոր է նշել Ելքի գումարը")
    private String outSum;
    //Բանկային միջնորդավճար
    private String bankCommissions;
    //Պայմանագրի ամսաթիվ
    private Date contractDate;
    //Պայմանագրի համար
    private String contractNumber;
    //Նշումներ
    private String note;
    @ManyToOne
    private CompanySupplier companySupplier;
    @ManyToOne
    private IndividualSupplier individualSupplier;
    @ManyToOne
    private PrivateEntrepreneurSupplier privateEntrepreneurSupplier;
    @ManyToOne
    private Organization organization;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;


}
