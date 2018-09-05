package com.cg.lpa.service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.dao.ILoanProcessingDao;
import com.cg.lpa.dao.LoanProcessingDaoImpl;
import com.cg.lpa.test.LoanProcessingException;

public class LoanProcessingServiceImpl implements ILoanProcessingService {
	ILoanProcessingDao dao = null;

	@Override
	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered() throws LoanProcessingException {
		dao = new LoanProcessingDaoImpl();
		return dao.viewLoanProgramsOffered();
	}

	@Override
	public int loginUser(String userId, String password) throws LoanProcessingException {
		dao = new LoanProcessingDaoImpl();
		return dao.loginUser(userId, password);
	}

	@Override
	public boolean isValidUserId(String userId) {

		Pattern pattern = Pattern.compile("[0-9]{4}");
		Matcher matcher = pattern.matcher(userId);
		return matcher.matches();

	}

	@Override
	public boolean isValidPassword(String password) {

		Pattern pattern = Pattern.compile("[A-Za-z0-9&!@#$_]{4,10}");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();

	}

	@Override
	public boolean isValidString(String string) {
		Pattern pattern = Pattern.compile("[A-Za-z]{5,10}");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	@Override
	public boolean isValidDouble(String number) {
		Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]*");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();

	}
}
