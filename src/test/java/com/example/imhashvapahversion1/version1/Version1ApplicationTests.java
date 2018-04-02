package com.example.imhashvapahversion1.version1;

import com.example.imhashvapahversion1.version1.Entity.cash.WaletIn;
import com.example.imhashvapahversion1.version1.Entity.cash.waletintypes.CashInFromBankAccount;
import com.example.imhashvapahversion1.version1.repository.WalletInRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Version1ApplicationTests {
@Autowired
	WalletInRepository walletInRepository;

	@Test
	public void contextLoads() {





	}
	@Test
	public void genericHibernate() {

        CashInFromBankAccount cashInFromBankAccount = new CashInFromBankAccount();

		WaletIn<CashInFromBankAccount> in = new WaletIn<CashInFromBankAccount>(cashInFromBankAccount);
		walletInRepository.save(in);



	}

}
