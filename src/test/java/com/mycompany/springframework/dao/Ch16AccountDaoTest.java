package com.mycompany.springframework.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.springframework.WebAppTest;
import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch13Account;

public class Ch16AccountDaoTest extends WebAppTest{
	@Autowired
	private Ch13AccountDao accountDao;

	@Test
	public void testSelectAll() {
		List<Ch13Account> list = accountDao.selectAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void testSelectByAno() {
		Ch13Account account = accountDao.selectByAno(1);
		Assert.assertNotNull(account);
		Assert.assertEquals(1, account.getAno());
		Assert.assertNotNull(account.getOwner());
		Assert.assertNotNull(account.getBalance());
	}
	
	@Test
	public void testUpdate() {
		Ch13Account account1 = accountDao.selectByAno(1);	// 1번 행을 가져옴(홍길동)
		account1.setBalance(account1.getBalance() + 1000);
		accountDao.update(account1);
		
		Ch13Account account2 = accountDao.selectByAno(1);
		
		Assert.assertEquals(account1.getBalance(), account2.getBalance());
	}

}
