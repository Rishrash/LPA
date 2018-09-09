package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.exception.LoanProcessingException;

public interface ILoanProcessingService {
	/*
	 * TODO : Login User. TODO : View all loan Program.
	 */
	public int loginUser(String userId, String password)
			throws LoanProcessingException;

	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered()
			throws LoanProcessingException;

	// Validations
	public abstract boolean isValidUserId(String userId);

	public abstract boolean isValidPassword(String password);

	public abstract boolean isValidString(String string);

	public abstract boolean isValidDouble(String number);

	public abstract boolean isValidAddress(String address);

	public abstract boolean isValidDocsProofString(String docsProof);

	public abstract boolean isValidGauranteeCoverString(String guaranteeCover);

	public abstract boolean isValidStringWithSpaces(String string);

	public abstract boolean isValidApplicantName(String applicantName);

	public abstract boolean isValidDate(String dob);

	public abstract boolean isValidMaritalStatus(String maritalStatus);

	public abstract boolean isValidPhoneNumber(String number);

	public abstract boolean isValidNumber(String number);

	public abstract boolean isValidEmailId(String emailId);

	public abstract boolean isValidLoanProgramDescription(String loanDescription);

	public abstract boolean isValidLoanType(String loanType);
}
