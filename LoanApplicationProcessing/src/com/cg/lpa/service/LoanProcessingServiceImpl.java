package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.dao.ILoanProcessingDao;
import com.cg.lpa.dao.LoanProcessingDaoImpl;
import com.cg.lpa.test.LoanProcessingException;

public class LoanProcessingServiceImpl implements ILoanProcessingService {
	ILoanProcessingDao dao = null;

	@Override
	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered()
			throws LoanProcessingException {
		dao = new LoanProcessingDaoImpl();
		return dao.viewLoanProgramsOffered();
	}

	@Override
	public int loginUser(String userId, String password)
			throws LoanProcessingException {
		dao = new LoanProcessingDaoImpl();
		return dao.loginUser(userId, password);
	}

}
