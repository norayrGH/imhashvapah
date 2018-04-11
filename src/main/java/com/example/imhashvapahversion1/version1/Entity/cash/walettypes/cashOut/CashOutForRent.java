package com.example.imhashvapahversion1.version1.Entity.cash.walettypes.cashOut;

import com.example.imhashvapahversion1.version1.Entity.Organization;
import com.example.imhashvapahversion1.version1.Entity.cash.WalletOut;

import javax.persistence.*;
import javax.validation.Valid;
@Entity
public class CashOutForRent {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Organization organization;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletOut walletOut;
}
