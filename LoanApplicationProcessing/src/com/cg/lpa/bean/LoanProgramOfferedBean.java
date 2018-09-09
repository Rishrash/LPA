package com.cg.lpa.bean;

/**
 * @author rabbhi
 *
 */
public class LoanProgramOfferedBean {
	private String loanProgramString;
	private String description;
	private String loanType;
	private int durationInYears;
	private double minLoanAmnt;
	private double maxLoanAmnt;
	private double rateOfIntrest;
	private String proofReq;

	public String getLoanProgramString() {
		return loanProgramString;
	}

	public void setLoanProgramString(String loanProgramString) {
		this.loanProgramString = loanProgramString;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getDurationInYears() {
		return durationInYears;
	}

	public void setDurationInYears(int durationInYears) {
		this.durationInYears = durationInYears;
	}

	public double getMinLoanAmnt() {
		return minLoanAmnt;
	}

	public void setMinLoanAmnt(double minLoanAmnt) {
		this.minLoanAmnt = minLoanAmnt;
	}

	public double getMaxLoanAmnt() {
		return maxLoanAmnt;
	}

	public void setMaxLoanAmnt(double maxLoanAmnt) {
		this.maxLoanAmnt = maxLoanAmnt;
	}

	public double getRateOfIntrest() {
		return rateOfIntrest;
	}

	public void setRateOfIntrest(double rateOfIntrest) {
		this.rateOfIntrest = rateOfIntrest;
	}

	public String getProofReq() {
		return proofReq;
	}

	public void setProofReq(String proofReq) {
		this.proofReq = proofReq;
	}

	public LoanProgramOfferedBean() {

	}

	public LoanProgramOfferedBean(String loanProgramString, String description,
			String loanType, int durationInYears, double minLoanAmnt,
			double maxLoanAmnt, double rateOfIntrest, String proofReq) {
		super();
		this.loanProgramString = loanProgramString;
		this.description = description;
		this.loanType = loanType;
		this.durationInYears = durationInYears;
		this.minLoanAmnt = minLoanAmnt;
		this.maxLoanAmnt = maxLoanAmnt;
		this.rateOfIntrest = rateOfIntrest;
		this.proofReq = proofReq;
	}

	@Override
	public String toString() {
		return "Loan Program        :" + loanProgramString
				+ "\nDescription         :" + description
				+ "\nLoan Type           :" + loanType
				+ "\nDuration In Years   :" + durationInYears
				+ "\nMinimum Loan Amount :" + minLoanAmnt
				+ "\nMaximum Loan Amount :" + maxLoanAmnt
				+ "\nRate Of Intrest     :" + rateOfIntrest
				+ "\nProof Required      :" + proofReq
				+ "\n.......................................................";
	}

}
