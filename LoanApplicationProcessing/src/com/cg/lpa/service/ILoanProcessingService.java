package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.test.LoanProcessingException;

public interface ILoanProcessingService {
	/*
	 * TODO : Login User. TODO : View all loan Program.
	 */
	public int loginUser(String userId, String password)
			throws LoanProcessingException;

	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered()
			throws LoanProcessingException;

}
