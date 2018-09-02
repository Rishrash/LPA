package com.cg.lpa.dao;

public interface IQueryMapper {
	// COMMON QUERIES
	public static final String GET_LOGIN_DETAILS = "SELECT * FROM users WHERE login_id=? AND password=?";
	public static final String GET_LOAN_PROGRAMS_OFFERED = "SELECT * FROM loan_programs_offered";

	// QUERIES FOR CUSTOMER
	public static final String INSERT_LOAN_APPLICATION_DETAILS = "INSERT INTO  loan_application(application_id,"
			+ " application_date, loan_program, amount_of_loan, address_of_property,"
			+ " annual_family_income, doc_proof_available, guarantee_cover, market_val_of_cover,"
			+ " date_of_interview) VALUES (seq_app_id.NEXTVAL,SYSDATE,?,?,?,?,?,?,?,SYSDATE+7)";
	public static final String GET_CURRENT_APPLICATION_ID = "SELECT seq_app_id.CURRVAL FROM DUAL";
	public static final String INSERT_CUSTOMER_DETAILS = "INSERT INTO CUSTOMER_DETAILS VALUES(?,?,?,?,?,?,?,?)";

	// QUERIES FOR LOAN APPROVAL DEPARTMENT
	public static final String GET_LOAN_APPLICATION_STATUS = "SELECT status FROM loan_application WHERE application_id = ?";

}
