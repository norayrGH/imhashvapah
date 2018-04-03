package com.example.imhashvapahversion1.version1.repository;

import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromBankAccount;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromPointOfSale;
import org.springframework.data.repository.CrudRepository;

public interface CashInFromBankAccountRepository extends CrudRepository<CashInFromBankAccount,Long> {

}
