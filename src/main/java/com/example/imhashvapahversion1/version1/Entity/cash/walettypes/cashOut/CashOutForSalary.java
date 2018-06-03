package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;
import com.example.imhashvapahversion1.version1.Entity.cash.walettypes.GetWaletOut;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForSalary implements GetWaletOut {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Organization organization;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;

    @Override
    public Long getCashOutId() {
        return id;
    }

    @Override
    public WalletOut getWalletOutImpl() {
        return walletOut;
    }
}
