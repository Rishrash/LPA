package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanApplicationBean;

public interface IMemberOfApprovalDeptService {

	/*
	 * TODO : View all loan application for a specific loan program.
	 * TODO : Accept/Reject loan application status.
	 * TODO : After Interview change loan application status to approved/reject
	 */

	ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificProgram(String loanProgram);
	
	boolean modifyApplicationStatus(LoanApplicationBean loanapplication);

}
