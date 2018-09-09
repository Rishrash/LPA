package com.cg.lpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.cg.lpa.dao.ILoanProcessingDao;
import com.cg.lpa.dao.LoanProcessingDaoImpl;
import com.cg.lpa.exception.LoanProcessingException;

public class LoginTest {
	ILoanProcessingDao loanProcessingDao = null;

	@Test
	public void testLoginUser() throws LoanProcessingException {
		loanProcessingDao = new LoanProcessingDaoImpl();
		assertEquals(1, loanProcessingDao.loginUser("1001", "admin1"));
		assertEquals(0, loanProcessingDao.loginUser("2001", "lad1"));
		assertNotEquals(1, loanProcessingDao.loginUser("1001", "wrong"));
		assertNotEquals(-1, loanProcessingDao.loginUser("1001", "admin1"));
	}

}
