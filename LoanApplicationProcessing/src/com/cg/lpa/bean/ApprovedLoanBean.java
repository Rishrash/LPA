package com.cg.lpa.bean;

public class ApprovedLoanBean {

	private int applicationId;
	private String applicantName;
	private double loanAmountGranted;
	private double monthlyInstallments;
	private int yearsTimePeriod;
	private double downPayment;
	private double rateOfInterest;
	private double totalAmountPayable;

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

	public double getLoanAmountGranted() {
		return loanAmountGranted;
	}

	public void setLoanAmountGranted(double loanAmountGranted) {
		this.loanAmountGranted = loanAmountGranted;
	}

	public double getMonthlyInstallments() {
		return monthlyInstallments;
	}

	public void setMonthlyInstallments(double monthlyInstallments) {
		this.monthlyInstallments = monthlyInstallments;
	}

	public int getYearsTimePeriod() {
		return yearsTimePeriod;
	}

	public void setYearsTimePeriod(int yearsTimePeriod) {
		this.yearsTimePeriod = yearsTimePeriod;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public double getTotalAmountPayable() {
		return totalAmountPayable;
	}

	public void setTotalAmountPayable(double totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
	}

	public ApprovedLoanBean() {
	}

	public ApprovedLoanBean(int applicationId, String applicantName,
			double loanAmountGranted, double monthlyInstallments,
			int yearsTimePeriod, double downPayment, double rateOfInterest,
			double totalAmountPayable) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.loanAmountGranted = loanAmountGranted;
		this.monthlyInstallments = monthlyInstallments;
		this.yearsTimePeriod = yearsTimePeriod;
		this.downPayment = downPayment;
		this.rateOfInterest = rateOfInterest;
		this.totalAmountPayable = totalAmountPayable;
	}

	@Override
	public String toString() {
		return "ApprovedLoanBean [applicationId=" + applicationId
				+ ", applicantName=" + applicantName + ", loanAmountGranted="
				+ loanAmountGranted + ", monthlyInstallments="
				+ monthlyInstallments + ", yearsTimePeriod=" + yearsTimePeriod
				+ ", downPayment=" + downPayment + ", rateOfInterest="
				+ rateOfInterest + ", totalAmountPayable=" + totalAmountPayable
				+ "]";
	}

}
