package com.cg.lpa.service;

import com.cg.lpa.bean.LoanApplicationBean;

public interface ICustomerService {

	/*
	 * TODO : View All Loan Programs. TODO : Apply For a Loan Program. TODO : View
	 * Application Status by entering application id.
	 */

	public boolean applyLoan(LoanApplicationBean loanapplication);

	public String viewApplicationStatus();

}
