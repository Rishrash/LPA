package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.ApprovedLoanBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.exception.LoanProcessingException;

public interface IAdminService {

	public boolean addLoanProgram(LoanProgramOfferedBean loanProgram)
			throws LoanProcessingException;

	public boolean deleteLoanProgram(String loanProgram)
			throws LoanProcessingException;

	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificStatus(
			String status) throws LoanProcessingException;

	public ArrayList<ApprovedLoanBean> viewApprovedLoan()
			throws LoanProcessingException;

}
