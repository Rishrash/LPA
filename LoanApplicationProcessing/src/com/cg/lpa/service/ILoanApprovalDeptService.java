package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.test.LoanProcessingException;

public interface ILoanApprovalDeptService {

	/*
	 * TODO : View all loan application for a specific loan program. TODO :
	 * Accept/Reject loan application status. TODO : After Interview change loan
	 * application status to approved/reject
	 */

	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificProgram(
			String loanProgram) throws LoanProcessingException;

	public boolean modifyApplicationStatus(int applicationId, String newStatus)
			throws LoanProcessingException;

}
