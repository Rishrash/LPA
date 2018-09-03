package com.cg.lpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.dbutil.DBUtil;
import com.cg.lpa.test.LoanProcessingException;

public class AdminDaoImpl implements IAdminDao {
	LoanApplicationBean loanApplication = null;
	CustomerDetailsBean customer = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public boolean addLoanProgram(LoanProgramOfferedBean loanProgram) throws LoanProcessingException {
		int status;
		try {

			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement("INSERT INTO loan_program_offered VALUES( ?, ?, ?, ?, ?, ?, ?, ? )");
			pstmt.setString(1, loanProgram.getLoanProgramString());
			pstmt.setString(2, loanProgram.getDescription());
			pstmt.setString(3, loanProgram.getLoanType());
			pstmt.setInt(4, loanProgram.getDurationInYears());
			pstmt.setDouble(5, loanProgram.getMinLoanAmnt());
			pstmt.setDouble(6, loanProgram.getMaxLoanAmnt());
			pstmt.setDouble(7, loanProgram.getRateOfIntrest());
			pstmt.setString(8, loanProgram.getProofReq());
			status = pstmt.executeUpdate();
			if (status == 1) {
				return true;
			}

		} catch (SQLException e) {

		}
		return false;
	}

	@Override
	public boolean deleteLoanProgram(String loanProgram) throws LoanProcessingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificStatus(String status)
			throws LoanProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

}
