package com.example.imhashvapahversion1.version1;

import com.example.imhashvapahversion1.version1.Entity.cash.signIn.type.SignInGoodsSale;
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

		SignInGoodsSale signInGoodsSale = new SignInGoodsSale();
		signInGoodsSale.setEntherCash("123000");




	}

}
