package com.cg.lpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.cg.lpa.bean.ApprovedLoanBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.dbutil.DBUtil;
import com.cg.lpa.test.LoanProcessingException;

public class LoanApprovalDeptDaoImpl implements ILoanApprovalDeptDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public ArrayList<LoanApplicationBean> viewLoanApplicationForSpecificProgram(
			String loanProgram) throws LoanProcessingException {
		ArrayList<LoanApplicationBean> loanApplicationList = new ArrayList();
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn
					.prepareStatement(IQueryMapper.GET_LOAN_APPLICATION_FOR_SPECIFIC_PROGRAM);
			pstmt.setString(1, loanProgram);
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
	public boolean modifyApplicationStatus(int applicationId, String newStatus)
			throws LoanProcessingException {
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn
					.prepareStatement(IQueryMapper.UPDATE_APPLICATION_STATUS);
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

	@Override
	public String getApplicantName(int applicationId)
			throws LoanProcessingException {
		String applicantName = null;
		try {

			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.GET_APPLICANT_NAME);
			pstmt.setInt(1, applicationId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				applicantName = rs.getString(1);

			} else {

			}
		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}
		return applicantName;
	}

	@Override
	public boolean fillApprovedLoanDetails(ApprovedLoanBean approvedLoan)
			throws LoanProcessingException {
		int status;
		try {
			conn = DBUtil.establishConnection();

			pstmt = conn
					.prepareStatement(IQueryMapper.INSERT_LOAN_APPROVED_DETAILS);
			pstmt.setInt(1, approvedLoan.getApplicationId());
			pstmt.setString(2, approvedLoan.getApplicantName());
			pstmt.setDouble(3, approvedLoan.getLoanAmountGranted());
			pstmt.setDouble(4, approvedLoan.getMonthlyInstallments());
			pstmt.setInt(5, approvedLoan.getYearsTimePeriod());
			pstmt.setDouble(6, approvedLoan.getDownPayment());
			pstmt.setDouble(7, approvedLoan.getRateOfInterest());
			pstmt.setDouble(8, approvedLoan.getTotalAmountPayable());
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
	public double getLoanAmountGranted(int applicationId)
			throws LoanProcessingException {
		double loanAmount = 0;
		try {

			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.GET_LOAN_AMOUNT);
			pstmt.setInt(1, applicationId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				loanAmount = rs.getInt(1);

			} else {

			}
		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}
		return loanAmount;

	}

	@Override
	public double getRateOfInterest(int applicationId)
			throws LoanProcessingException {
		double rateOfInterest = 0;
		try {

			conn = DBUtil.establishConnection();
			pstmt = conn.prepareStatement(IQueryMapper.GET_RATE_OF_INTEREST);
			pstmt.setInt(1, applicationId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rateOfInterest = rs.getDouble(1);

			} else {

			}
		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}
		return rateOfInterest;
	}

	@Override
	public int getLoanDurationInYears(int applicationId)
			throws LoanProcessingException {

		int yearsTimePeriod = 0;
		try {

			conn = DBUtil.establishConnection();
			pstmt = conn
					.prepareStatement(IQueryMapper.GET_LOAN_DURATION_IN_YEARS);
			pstmt.setInt(1, applicationId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				yearsTimePeriod = rs.getInt(1);

			} else {

			}
		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}
		return yearsTimePeriod;
	}
}
