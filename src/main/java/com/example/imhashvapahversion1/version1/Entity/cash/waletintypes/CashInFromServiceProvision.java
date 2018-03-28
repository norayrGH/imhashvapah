package com.example.imhashvapahversion1.version1.Entity.cash.waletintypes;

import com.example.imhashvapahversion1.version1.Entity.cash.WalletIn;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class CashInFromServiceProvision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    @Valid
    private WalletIn walletIn;
}
