package com.cg.lpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.cg.lpa.bean.ApprovedLoanBean;
import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.dbutil.DBUtil;
import com.cg.lpa.exception.LoanProcessingException;

public class AdminDaoImpl implements IAdminDao {
	LoanApplicationBean loanApplication = null;
	CustomerDetailsBean customer = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public boolean addLoanProgram(LoanProgramOfferedBean loanProgram)
			throws LoanProcessingException {
		int status = 0;
		try {

			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.ADD_NEW_LOAN_PROGRAM);
			pstmt.setString(1, loanProgram.getLoanProgramString());
			pstmt.setString(2, loanProgram.getDescription());
			pstmt.setString(3, loanProgram.getLoanType());
			pstmt.setInt(4, loanProgram.getDurationInYears());
			pstmt.setDouble(5, loanProgram.getMinLoanAmnt());
			pstmt.setDouble(6, loanProgram.getMaxLoanAmnt());
			pstmt.setDouble(7, loanProgram.getRateOfIntrest());
			pstmt.setString(8, loanProgram.getProofReq());
			status = pstmt.executeUpdate();
			// PreparedStatement ps = conn.prepareStatement("commit");
			// ps.executeUpdate();
			if (status == 1) {
				return true;
			}

		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());

		}
		return false;
	}

	@Override
	public boolean deleteLoanProgram(String loanProgram)
			throws LoanProcessingException {
		int status;
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.DELETE_LOAN_PROGRAM);
			pstmt.setString(1, loanProgram);
			status = pstmt.executeUpdate();
			if (status == 1) {
				return true;
			}

		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}
		return false;
	}

	@Override
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificStatus(
			String status) throws LoanProcessingException {
		ArrayList<LoanApplicationBean> loanApplicationList = new ArrayList();
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn
					.prepareStatement(IQueryMapper.GET_LOAN_APPLICATION_WITH_SPECIFIC_STATUS_STRING);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern("uuuu-MM-dd");
				String sqlApplicationDate = rs.getString(2);
				sqlApplicationDate = sqlApplicationDate.substring(0, 10);
				String sqlInterviewDate = rs.getString(11);
				sqlInterviewDate = sqlInterviewDate.substring(0, 10);
				LocalDate applicationDate = LocalDate.parse(sqlApplicationDate,
						formatter);
				LocalDate interviewDate = LocalDate.parse(sqlInterviewDate,
						formatter);

				LoanApplicationBean loanApplication = new LoanApplicationBean(
						rs.getInt(1), applicationDate, rs.getString(3),
						rs.getDouble(4), rs.getString(5), rs.getDouble(6),
						rs.getString(7), rs.getString(8), rs.getDouble(9),
						rs.getString(10), interviewDate);
				loanApplicationList.add(loanApplication);

			}
		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}

		return loanApplicationList;
	}

	@Override
	public ArrayList<ApprovedLoanBean> viewApprovedLoan()
			throws LoanProcessingException {
		ArrayList<ApprovedLoanBean> approvedLoanList = new ArrayList<ApprovedLoanBean>();
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.GET_APPROVED_LOANS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ApprovedLoanBean approvedLoan = new ApprovedLoanBean(
						rs.getInt(1), rs.getString(2), rs.getDouble(3),
						rs.getDouble(4), rs.getInt(5), rs.getDouble(6),
						rs.getDouble(7), rs.getDouble(8));
				approvedLoanList.add(approvedLoan);
			}

		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}

		return approvedLoanList;
	}
}
