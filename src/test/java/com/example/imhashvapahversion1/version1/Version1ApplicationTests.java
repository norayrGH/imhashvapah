package com.example.imhashvapahversion1.version1;

import com.example.imhashvapahversion1.version1.Entity.enums.DateRange;
import com.example.imhashvapahversion1.version1.repository.CashInFromBankAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Version1ApplicationTests {
@Autowired
CashInFromBankAccountRepository cashInFromBankAccountRepository;

	@Test
	public void contextLoads() {





	}
	@Test
	public void genericHibernate() {
		DateRange dateRange = new DateRange();
		dateRange.setStart(new Date(2018,3,22));
		Calendar calStart = Calendar.getInstance();
		calStart.set(Calendar.YEAR,dateRange.getStart().toLocalDate().getYear());
		calStart.set(Calendar.MONTH,  dateRange.getStart().toLocalDate().getDayOfMonth() - 1);
		calStart.set(Calendar.DAY_OF_MONTH, dateRange.getStart().toLocalDate().getMonthValue()+1);
		calStart.set(Calendar.HOUR_OF_DAY,0);
		calStart.set(Calendar.MINUTE,0);
		calStart.set(Calendar.SECOND,0);
		java.sql.Date dateStart = new java.sql.Date(calStart.getTimeInMillis());
		cashInFromBankAccountRepository.findByRangeStart(dateStart);

	}

}
