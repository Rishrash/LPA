package com.cg.lpa.dao;

import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.test.LoanProcessingException;

public interface ICustomerDao {

	public boolean applyLoan(LoanApplicationBean loanapplication)
			throws LoanProcessingException;

	public boolean fillCustomerDetails(CustomerDetailsBean customer)
			throws LoanProcessingException;

	public String viewApplicationStatus(int applicationID)
			throws LoanProcessingException;

	public boolean deleteLoanApplication(int applicationId)
			throws LoanProcessingException;

}
