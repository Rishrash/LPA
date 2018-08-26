package com.cg.lpa.dao;

public interface IQueryMapper {

	public static final String RETRIEVE_LOAN_PROGRAMS_OFFERED_STRING = "SELECT * FROM loan_programs_offered";
	public static final String RETRIEVE_LOAN_APPLICATION_STATUS = "SELECT status FROM loan_application WHERE application_id = 1001";

}
