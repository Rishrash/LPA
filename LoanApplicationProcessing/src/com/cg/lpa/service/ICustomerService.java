package com.cg.lpa.service;

import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.test.LoanProcessingException;

public interface ICustomerService {

	/*
	 * TODO : View All Loan Programs. TODO : Apply For a Loan Program. TODO :
	 * View Application Status by entering application id.
	 */

	public boolean applyLoan(LoanApplicationBean loanapplication)
			throws LoanProcessingException;

	public boolean fillCustomerDetails(CustomerDetailsBean customer)
			throws LoanProcessingException;

	public String viewApplicationStatus(int applicationID)
			throws LoanProcessingException;

	public boolean deleteLoanApplication(int applicationId)
			throws LoanProcessingException;

}
