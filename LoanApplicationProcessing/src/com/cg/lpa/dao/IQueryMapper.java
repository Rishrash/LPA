package com.cg.lpa.dao;

public interface IQueryMapper {

	// COMMON QUERIES
	public static final String GET_USER_ROLE = "SELECT role FROM users WHERE login_id=? AND password=?";
	public static final String GET_LOAN_PROGRAMS_OFFERED = "SELECT * FROM loan_programs_offered";

	// QUERIES FOR CUSTOMER
	public static final String INSERT_LOAN_APPLICATION_DETAILS = "INSERT INTO  loan_application(application_id,"
			+ " application_date, loan_program, loan_amount, address_of_property,"
			+ " annual_family_income, doc_proof_available, guarantee_cover, market_val_of_cover, date_of_interview)"
			+ " VALUES (seq_app_id.NEXTVAL,SYSDATE,?,?,?,?,?,?,?,SYSDATE+20)";
	public static final String GET_CURRENT_APPLICATION_ID = "SELECT seq_app_id.CURRVAL FROM DUAL";
	public static final String INSERT_CUSTOMER_DETAILS = "INSERT INTO CUSTOMER_DETAILS VALUES(?,?,?,?,?,?,?,?)";
	public static final String GET_STATUS_FOR_LOAN_APPLICATION = "SELECT status FROM loan_application WHERE application_id = ?";

	// QUERIES FOR LOAN APPROVAL DEPARTMENT
	public static final String GET_LOAN_APPLICATION_FOR_SPECIFIC_PROGRAM = "SELECT * FROM loan_application WHERE loan_program = ?";
	public static final String UPDATE_APPLICATION_STATUS = "UPDATE loan_application SET status = ? WHERE application_id = ? ";
	public static final String GET_APPLICANT_NAME = "SELECT applicant_name FROM customer_details WHERE application_id = ? ";
	public static final String GET_LOAN_AMOUNT = "SELECT loan_amount FROM loan_application WHERE application_id = ? ";
	public static final String GET_RATE_OF_INTEREST = "SELECT rate_of_interest FROM loan_programs_offered WHERE loan_program = (SELECT loan_program from loan_application WHERE application_id = ? ) ";
	public static final String GET_LOAN_DURATION_IN_YEARS = "SELECT duration_in_years FROM loan_programs_offered WHERE loan_program = (SELECT loan_program from loan_application WHERE application_id = ? ) ";
	public static final String INSERT_LOAN_APPROVED_DETAILS = "INSERT INTO approved_loans VALUES(?, ?, ?, ?, ?, ?, ?, ? )";
	public static final String UPDATE_INTERVIEW_DATE = "UPDATE loan_application SET date_of_interview = SYSDATE + 7 WHERE application_id = ? ";
	// QUERIES FOR ADMIN
	public static final String DELETE_LOAN_PROGRAM = "DELETE FROM LOAN_PROGRAMS_OFFERED WHERE loan_program = ?";
	public static final String GET_LOAN_APPLICATION_WITH_SPECIFIC_STATUS_STRING = "SELECT * FROM loan_application WHERE status = ? ";
	public static final String ADD_NEW_LOAN_PROGRAM = "INSERT INTO loan_programs_offered VALUES( ?, ?, ?, ?, ?, ?, ?, ? )";
	public static final String GET_APPROVED_LOANS = "SELECT * FROM approved_loans";
}
