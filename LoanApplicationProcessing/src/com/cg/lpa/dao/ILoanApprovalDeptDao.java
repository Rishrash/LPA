package com.cg.lpa.dao;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.test.LoanProcessingException;

public interface ILoanApprovalDeptDao {
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificProgram(
			String loanProgram) throws LoanProcessingException;

	public boolean modifyApplicationStatus(LoanApplicationBean loanapplication)
			throws LoanProcessingException;

}
