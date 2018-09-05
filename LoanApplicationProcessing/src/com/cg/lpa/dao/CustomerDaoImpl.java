package com.cg.lpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.dbutil.DBUtil;
import com.cg.lpa.test.LoanProcessingException;

public class CustomerDaoImpl implements ICustomerDao {
	LoanApplicationBean loanApplication = null;
	CustomerDetailsBean customer = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	@Override
	public boolean applyLoan(LoanApplicationBean loanapplication)
			throws LoanProcessingException {

		int status;
		try {

			conn = DBUtil.establishConnection();

			pstmt = conn
					.prepareStatement(IQueryMapper.INSERT_LOAN_APPLICATION_DETAILS);

			pstmt.setString(1, loanapplication.getLoanProgram());
			pstmt.setDouble(2, loanapplication.getLoanAmount());
			pstmt.setString(3, loanapplication.getPropertyAddress());
			pstmt.setDouble(4, loanapplication.getAnnualFamilyIncome());
			pstmt.setString(5, loanapplication.getDocsProof());
			pstmt.setString(6, loanapplication.getGuaranteeCover());
			pstmt.setDouble(7, loanapplication.getMarktValOfCover());

			status = pstmt.executeUpdate();
			if (status == 1) {

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt
						.executeQuery(IQueryMapper.GET_CURRENT_APPLICATION_ID);
				while (rs.next()) {
					loanapplication.setApplicationId(rs.getInt(1));
				}

			} else {

				return false;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new LoanProcessingException("Error in " + e.getMessage());

		}
		return true;

	}

	@Override
	public boolean fillCustomerDetails(CustomerDetailsBean customer)
			throws LoanProcessingException {

		int status = 0;
		try {

			conn = DBUtil.establishConnection();

			pstmt = conn.prepareStatement(IQueryMapper.INSERT_CUSTOMER_DETAILS);

			pstmt.setInt(1, customer.getApplicationId());
			pstmt.setString(2, customer.getApplicantName());
			pstmt.setString(3, customer.getDateOfBirth());
			pstmt.setString(4, customer.getMaritalStatus());
			pstmt.setLong(5, customer.getPhoneNumber());
			pstmt.setLong(6, customer.getMobileNumber());
			pstmt.setInt(7, customer.getDependentsCount());
			pstmt.setString(8, customer.getEmailId());

			status = pstmt.executeUpdate();
			if (status == 1) {
				return true;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new LoanProcessingException("Error in " + e.getMessage());

		}
		return false;
	}

	@Override
	public String viewApplicationStatus(int applicationId)
			throws LoanProcessingException {
		String applicationStatus = null;
		try {
			conn = DBUtil.establishConnection();

			pstmt = conn
					.prepareStatement(IQueryMapper.GET_STATUS_FOR_LOAN_APPLICATION);
			pstmt.setInt(1, applicationId);
			Statement stmt = conn.createStatement();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				applicationStatus = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}
		return applicationStatus;
	}

	@Override
	public boolean deleteLoanApplication(int applicationId)
			throws LoanProcessingException {
		int status = 0;
		try {
			conn = DBUtil.establishConnection();
			pstmt = conn
					.prepareStatement(" DELETE FROM loan_application WHERE application_id = ? ");
			pstmt.setInt(1, applicationId);
			status = pstmt.executeUpdate();
			if (status == 1) {
				return true;
			}

		} catch (SQLException e) {
			throw new LoanProcessingException("Error in " + e.getMessage());
		}

		return false;
	}

}
