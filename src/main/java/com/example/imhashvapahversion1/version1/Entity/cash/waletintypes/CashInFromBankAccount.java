package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class CashInFromBankAccount  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bankAccount;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Valid
    private WalletIn walletIn;
    public CashInFromBankAccount() {
    }
}
