package com.cg.lpa.service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.dao.ILoanProcessingDao;
import com.cg.lpa.dao.LoanProcessingDaoImpl;
import com.cg.lpa.exception.LoanProcessingException;

public class LoanProcessingServiceImpl implements ILoanProcessingService {
	ILoanProcessingDao dao = null;

	@Override
	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered()
			throws LoanProcessingException {
		dao = new LoanProcessingDaoImpl();
		return dao.viewLoanProgramsOffered();
	}

	@Override
	public int loginUser(String userId, String password)
			throws LoanProcessingException {
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
		Pattern pattern = Pattern.compile("[A-Za-z]{2,10}");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	@Override
	public boolean isValidDouble(String number) {
		Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]*");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();

	}

	@Override
	public boolean isValidAddress(String address) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9 ,/#-]{4,30}");
		Matcher matcher = pattern.matcher(address);
		return matcher.matches();
	}

	@Override
	public boolean isValidDocsProofString(String docsProof) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9 ,/#-]{4,50}");
		Matcher matcher = pattern.matcher(docsProof);
		return matcher.matches();
	}

	@Override
	public boolean isValidGauranteeCoverString(String guaranteeCover) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9 ,/#-]{4,20}");
		Matcher matcher = pattern.matcher(guaranteeCover);
		return matcher.matches();
	}

	@Override
	public boolean isValidStringWithSpaces(String string) {
		Pattern pattern = Pattern.compile("[A-z ?!] ");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}

	@Override
	public boolean isValidApplicantName(String applicantName) {
		Pattern pattern = Pattern.compile("^[a-zA-Z_ ]*$");
		Matcher matcher = pattern.matcher(applicantName);
		return matcher.matches();
	}

	@Override
	public boolean isValidDate(String dob) {
		Pattern pattern = Pattern
				.compile("[0-31]{2}[-][JAN|Jan|jan|FEB|Feb|feb|MAR|Mar|mar|APR|Apr|apr|MAY|May|may|JUN|Jun|jun|JUL|Jul|jul|AUG|Aug|aug|SEP|Sep|sep|OCT|Oct|oct|NOV|Nov|nov|DEC|Dec|dec]{3}[-][0-9]{4}");
		Matcher matcher = pattern.matcher(dob);
		return matcher.matches();
	}

	@Override
	public boolean isValidMaritalStatus(String maritalStatus) {
		Pattern pattern = Pattern
				.compile("[ SINGLE|Single|Married|Married|DIVORCED|divorced|WIDOWED|widowed]{6,10}");
		Matcher matcher = pattern.matcher(maritalStatus);
		return matcher.matches();
	}

	@Override
	public boolean isValidPhoneNumber(String number) {
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{9}");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();

	}

	@Override
	public boolean isValidNumber(String number) {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();
	}

	@Override
	public boolean isValidEmailId(String emailId) {
		Pattern pattern = Pattern
				.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
		Matcher matcher = pattern.matcher(emailId);
		return matcher.matches();
	}

	@Override
	public boolean isValidLoanProgramDescription(String loanDescription) {
		Pattern pattern = Pattern.compile("[A-Za-z0-9 ,]{4,20}");
		Matcher matcher = pattern.matcher(loanDescription);
		return matcher.matches();
	}

	@Override
	public boolean isValidLoanType(String loanType) {
		Pattern pattern = Pattern.compile("[A-Za-z]{4,20}");
		Matcher matcher = pattern.matcher(loanType);
		return matcher.matches();
	}
}
