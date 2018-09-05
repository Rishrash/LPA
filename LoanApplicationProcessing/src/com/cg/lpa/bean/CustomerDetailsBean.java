/**
 * 
 */
package com.cg.lpa.bean;


//import java.time.String;

/**
 * @author rabbhi
 *
 */
public class CustomerDetailsBean {

	private int applicationId;
	private String applicantName;
	private String dateOfBirth;
	private String maritalStatus;
	private long phoneNumber;
	private long mobileNumber;
	private int dependentsCount;
	private String emailId;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(int dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public CustomerDetailsBean() {

	}

	public CustomerDetailsBean(int applicationId, String applicantName,
			String dateOfBirth, String maritalStatus, long phoneNumber,
			long mobileNumber, int dependentsCount, String emailId) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.dateOfBirth = dateOfBirth;
		this.maritalStatus = maritalStatus;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.dependentsCount = dependentsCount;
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "CustomerDetailsBean [applicationId=" + applicationId
				+ ", applicantName=" + applicantName + ", dateOfBirth="
				+ dateOfBirth + ", maritalStatus=" + maritalStatus
				+ ", phoneNumber=" + phoneNumber + ", mobileNumber="
				+ mobileNumber + ", dependentsCount=" + dependentsCount
				+ ", emailId=" + emailId + "]";
	}

}
