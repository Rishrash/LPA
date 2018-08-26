package com.cg.lpa.bean;

/**
 * @author rabbhi
 *
 */
import java.time.LocalDate;

public class LoanApplicationBean {

	private int applicationId;
	private LocalDate applicationDate;
	private String loanProgram;
	private double loanAmount;
	private String propertyAddress;
	private double annualFamilyIncome;
	private String docsProof;
	private String guaranteeCoverString;
	private double marktValOfCover;
	private String status;
	private LocalDate interviewDate;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getLoanProgram() {
		return loanProgram;
	}

	public void setLoanProgram(String loanProgram) {
		this.loanProgram = loanProgram;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public double getAnnualFamilyIncome() {
		return annualFamilyIncome;
	}

	public void setAnnualFamilyIncome(double annualFamilyIncome) {
		this.annualFamilyIncome = annualFamilyIncome;
	}

	public String getDocsProof() {
		return docsProof;
	}

	public void setDocsProof(String docsProof) {
		this.docsProof = docsProof;
	}

	public String getGuaranteeCoverString() {
		return guaranteeCoverString;
	}

	public void setGuaranteeCoverString(String guaranteeCoverString) {
		this.guaranteeCoverString = guaranteeCoverString;
	}

	public double getMarktValOfCover() {
		return marktValOfCover;
	}

	public void setMarktValOfCover(double marktValOfCover) {
		this.marktValOfCover = marktValOfCover;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}

	public LoanApplicationBean() {

	}

	public LoanApplicationBean(int applicationId, String loanProgram,
			double loanAmount, String propertyAddress,
			double annualFamilyIncome, String docsProof,
			String guaranteeCoverString, double marktValOfCover, String status,
			LocalDate interviewDate) {
		super();
		this.applicationId = applicationId;
		this.loanProgram = loanProgram;
		this.loanAmount = loanAmount;
		this.propertyAddress = propertyAddress;
		this.annualFamilyIncome = annualFamilyIncome;
		this.docsProof = docsProof;
		this.guaranteeCoverString = guaranteeCoverString;
		this.marktValOfCover = marktValOfCover;
		this.status = status;
		this.interviewDate = interviewDate;
	}

	@Override
	public String toString() {
		return "LoanApplicationBean [applicationId=" + applicationId
				+ ", applicationDate=" + applicationDate + ", loanProgram="
				+ loanProgram + ", loanAmount=" + loanAmount
				+ ", propertyAddress=" + propertyAddress
				+ ", annualFamilyIncome=" + annualFamilyIncome + ", docsProof="
				+ docsProof + ", guaranteeCoverString=" + guaranteeCoverString
				+ ", marktValOfCover=" + marktValOfCover + ", status=" + status
				+ ", interviewDate=" + interviewDate + "]";
	}

}
