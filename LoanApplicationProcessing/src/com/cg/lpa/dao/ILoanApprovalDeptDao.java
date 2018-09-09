package com.cg.lpa.dao;

import java.util.ArrayList;

import com.cg.lpa.bean.ApprovedLoanBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.exception.LoanProcessingException;

public interface ILoanApprovalDeptDao {
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificProgram(
			String loanProgram) throws LoanProcessingException;

	public boolean modifyApplicationStatus(int applicationId, String newStatus)
			throws LoanProcessingException;

	public String getApplicantName(int applicationId)
			throws LoanProcessingException;

	public boolean fillApprovedLoanDetails(ApprovedLoanBean approvedLoan)
			throws LoanProcessingException;

	public double getLoanAmountGranted(int applicationId)
			throws LoanProcessingException;

	public double getRateOfInterest(int applicationId)
			throws LoanProcessingException;

	public int getLoanDurationInYears(int applicationId)
			throws LoanProcessingException;

	public boolean setInterviewDate(int applicationId)
			throws LoanProcessingException;

}
