package com.cg.lpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.dbutil.DBUtil;
import com.cg.lpa.test.LoanProcessingException;

public class LoanApprovalDeptDaoImpl implements ILoanApprovalDeptDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificProgram(String loanProgram)
			throws LoanProcessingException {
		ArrayList<LoanApplicationBean> loanApplicationList = new ArrayList();
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.GET_LOAN_APPLICATION_FOR_SPECIFIC_PROGRAM);
			pstmt.setString(1, loanProgram);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LoanApplicationBean loanApplication = new LoanApplicationBean(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getDouble(6), rs.getString(7),
						rs.getString(8), rs.getDouble(9), rs.getString(10), rs.getString(11));
				loanApplicationList.add(loanApplication);
			}

		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}

		return loanApplicationList;
	}

	@Override
	public boolean modifyApplicationStatus(int applicationId, String newStatus) throws LoanProcessingException {
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.UPDATE_APPLICATION_STATUS);
			pstmt.setString(1, newStatus);
			pstmt.setInt(2, applicationId);
			int status = pstmt.executeUpdate();
			if (status == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}

	}

}
