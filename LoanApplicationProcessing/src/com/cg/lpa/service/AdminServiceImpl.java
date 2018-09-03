package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.dao.AdminDaoImpl;
import com.cg.lpa.dao.IAdminDao;
import com.cg.lpa.test.LoanProcessingException;

public class AdminServiceImpl implements IAdminService {
	IAdminDao adminDao = null;

	@Override
	public boolean addLoanProgram(LoanProgramOfferedBean loanProgram)
			throws LoanProcessingException {
		adminDao = new AdminDaoImpl();
		return adminDao.addLoanProgram(loanProgram);
	}

	@Override
	public boolean deleteLoanProgram(String loanProgramName)
			throws LoanProcessingException {
		adminDao = new AdminDaoImpl();

		return adminDao.deleteLoanProgram(loanProgramName);
	}

	@Override
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificStatus(
			String status) throws LoanProcessingException {
		adminDao = new AdminDaoImpl();

		return adminDao.viewLoanApplicationForSpecificStatus(status);
	}

}
