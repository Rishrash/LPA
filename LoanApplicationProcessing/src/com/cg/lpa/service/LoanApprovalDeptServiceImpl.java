package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.dao.ILoanApprovalDeptDao;
import com.cg.lpa.dao.LoanApprovalDeptDaoImpl;
import com.cg.lpa.test.LoanProcessingException;

public class LoanApprovalDeptServiceImpl implements ILoanApprovalDeptService {
	ILoanApprovalDeptDao ladDao = null;

	@Override
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificProgram(
			String loanProgram) throws LoanProcessingException {
		ladDao = new LoanApprovalDeptDaoImpl();

		return ladDao.viewLoanApplicationForSpecificProgram(loanProgram);
	}

	@Override
	public boolean modifyApplicationStatus(LoanApplicationBean loanapplication)
			throws LoanProcessingException {
		ladDao = new LoanApprovalDeptDaoImpl();

		return ladDao.modifyApplicationStatus(loanapplication);
	}

}
