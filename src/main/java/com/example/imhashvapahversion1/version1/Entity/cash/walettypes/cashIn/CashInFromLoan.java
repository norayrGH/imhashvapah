package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashIn;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletIn;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class CashInFromLoan implements GetWaletIn {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "հարկավոր է ընտրել գործընկերոջը ")
    private String colleagues;
    @NotNull(message = "հարկավոր է նշել պայմանագրի ամսաթիվը ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractDate;
    @NotEmpty(message = "հարկավոր է նշել պայմանագրի համարը ")
    private String forContract;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletIn walletIn;
    @ManyToOne
    private Organization organization;

    public CashInFromLoan() {
    }

    public CashInFromLoan(String colleagues, Date contractDate, String forContract, WalletIn walletIn, Organization organization) {
        this.colleagues = colleagues;
        this.contractDate = contractDate;
        this.forContract = forContract;
        this.walletIn = walletIn;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColleagues() {
        return colleagues;
    }

    public void setColleagues(String colleagues) {
        this.colleagues = colleagues;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getForContract() {
        return forContract;
    }

    public void setForContract(String forContract) {
        this.forContract = forContract;
    }

    public WalletIn getWalletIn() {
        return walletIn;
    }
    @Override
    public WalletIn getWalletInImpl() {
        return walletIn;
    }

    public void setWalletIn(WalletIn walletIn) {
        this.walletIn = walletIn;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}