package com.mycompany.springframework.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.springframework.WebAppTest;
import com.mycompany.springframework.dto.Ch13Account;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch16AccountServiceTest extends WebAppTest{
	
	@Autowired
	private Ch15AccountService accountService;
	
	@Test
	public void testTransfer() {
		Ch13Account fromAccount1 = accountService.getAccount(1);
		Ch13Account toAccount1 = accountService.getAccount(2);
		int fromAccount1Balance = fromAccount1.getBalance();
		int toAccount1Balance = toAccount1.getBalance();
		
		log.info(fromAccount1.toString());
		
		accountService.transfer(1, 2, 1000);
		
		Ch13Account fromAccount2 = accountService.getAccount(1);
		Ch13Account toAccount2 = accountService.getAccount(2);
		
		log.info(fromAccount1.toString());
		
		Assert.assertEquals(fromAccount1Balance-1000, fromAccount2.getBalance());
		Assert.assertEquals(toAccount1Balance+1000, toAccount2.getBalance());
	}

}
