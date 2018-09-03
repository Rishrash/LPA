package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.test.LoanProcessingException;

public interface IAdminService {
	/*
	 * TODO : Update Info Of Long Program TODO : View List of loan applications
	 * approved/accepted (waiting for interview)/rejected for a loan program.
	 */

	public boolean addLoanProgram(LoanProgramOfferedBean loanProgram)
			throws LoanProcessingException;

	public boolean deleteLoanProgram(String loanProgram)
			throws LoanProcessingException;

	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificStatus(
			String status) throws LoanProcessingException;

}
