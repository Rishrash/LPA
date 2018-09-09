package com.cg.lpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.bean.UserBean;
import com.cg.lpa.dbutil.DBUtil;
import com.cg.lpa.exception.LoanProcessingException;

public class LoanProcessingDaoImpl implements ILoanProcessingDao {
	UserBean user = new UserBean();
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	// User login class

	@Override
	public int loginUser(String userId, String password)
			throws LoanProcessingException {
		String type = "";
		int n = -1;

		try {
			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.GET_USER_ROLE);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				type = rs.getString(1);
			}
			if ("admin".equals(type)) {
				n = 1;
			} else if ("lad".equals(type)) {
				n = 0;
			} else {
				n = -1;
			}

		} catch (SQLException e1) {
			throw new LoanProcessingException("problem : " + e1.getMessage());

		}
		return n;
	}

	@Override
	public ArrayList<LoanProgramOfferedBean> viewLoanProgramsOffered()
			throws LoanProcessingException {
		ArrayList<LoanProgramOfferedBean> loanProgramList = new ArrayList();
		try {
			conn = DBUtil.establishConnection();
			stmt = conn.createStatement();
			ResultSet rst;
			rst = stmt.executeQuery(IQueryMapper.GET_LOAN_PROGRAMS_OFFERED);

			while (rst.next()) {
				LoanProgramOfferedBean loanProgram = new LoanProgramOfferedBean(
						rst.getString(1), rst.getString(2), rst.getString(3),
						rst.getInt(4), rst.getDouble(5), rst.getDouble(6),
						rst.getDouble(7), rst.getString(8));
				loanProgramList.add(loanProgram);
			}
		} catch (Exception e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}

		return loanProgramList;
	}
}
