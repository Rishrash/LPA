package com.cg.lpa.dao;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.test.LoanProcessingException;

public interface ILoanProcessingDao {

	public int loginUser(String userId, String password)
			throws LoanProcessingException;

	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered()
			throws LoanProcessingException;

}
