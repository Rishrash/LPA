package com.cg.lpa.service;

import java.util.ArrayList;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.bean.UserBean;

public interface ILoanProcessingService {
	/*
	 * TODO : Login User. TODO : View all loan Program.
	 * 
	 */
	public UserBean loginUser(UserBean newUser);

	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered();

}
