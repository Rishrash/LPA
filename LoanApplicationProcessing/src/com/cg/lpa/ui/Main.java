package com.cg.lpa.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.lpa.bean.ApprovedLoanBean;
import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.service.AdminServiceImpl;
import com.cg.lpa.service.CustomerServiceImpl;
import com.cg.lpa.service.IAdminService;
import com.cg.lpa.service.ICustomerService;
import com.cg.lpa.service.ILoanApprovalDeptService;
import com.cg.lpa.service.ILoanProcessingService;
import com.cg.lpa.service.LoanApprovalDeptServiceImpl;
import com.cg.lpa.service.LoanProcessingServiceImpl;
import com.cg.lpa.test.LoanProcessingException;

public class Main {
	private static Scanner input;
	private static Scanner inputString;

	static ILoanProcessingService loanProcessingService = null;
	static ICustomerService customerService = null;
	static ILoanApprovalDeptService ladService = null;
	static IAdminService adminService = null;

	// MAIN METHOD
	public static void main(String[] args) throws LoanProcessingException {

		input = new Scanner(System.in);
		inputString = new Scanner(System.in);

		startTheProgram();
	}

	// START METHOD
	private static void startTheProgram() throws LoanProcessingException {

		System.out.println("Welcome To Loan Processing Application\n\n");
		System.out.println("Select your role from below :");
		System.out.println("1. Enter as a Customer");
		System.out.println("2. Login as Member Of Approval Department or Admin");
		System.out.println("3. Exit");

		String userInput = input.next();

		switch (userInput) {
		case "1":
			enteredAsCustomer();
			break;
		case "2":
			loginUser();
			break;
		case "3":
			System.out.println("Have a Nice Day :-) ");
			System.exit(0);
		default:
			System.out.println("Invalid choice");
		}
		startTheProgram();
	}

	// LOGIN METHOD

	private static void loginUser() throws LoanProcessingException {
		loanProcessingService = new LoanProcessingServiceImpl();
		input = new Scanner(System.in);
		System.out.print("UserId : ");
		String userId = input.next();
		while (!loanProcessingService.isValidUserId(userId)) {
			System.err.println("UserId Should contain only numbers and should be of length 4 ");
			System.out.print("UserId : ");
			userId = input.next();
		}
		System.out.print("Password : ");
		String password = input.next();
		while (!loanProcessingService.isValidPassword(password)) {
			System.err.println("Password should be less than 10 characters and should not contain spaces");
			System.out.print("Password : ");
			password = input.next();
		}

		try {
			if (loanProcessingService.loginUser(userId, password) == 0) {

				// Enter As Member of Loan Approval Board
				enteredAsMemberOfBoard();

			} else if (loanProcessingService.loginUser(userId, password) == 1) {

				// Enter As Admin
				enteredAsAdmin();

			} else {

				// Incorrect Password
				System.out.println("UserID or Password is wrong");

			}
		} catch (LoanProcessingException e) {

			throw new LoanProcessingException("problem : " + e.getMessage());
		}

	}

	// ================================================================
	// = 1. When User has Entered As a Customer
	// ================================================================

	private static void enteredAsCustomer() {
		System.out.println("Welcome Customer");
		System.out.println("Select Options from Given menu");
		System.out.println("1. View all Loan Programs");
		System.out.println("2. Apply for a new loan");
		System.out.println("3. View Application status of your Loan Application");
		System.out.println("4. Previous Page");
		System.out.println("5. Exit");
		try {
			loanProcessingService = new LoanProcessingServiceImpl();
			customerService = new CustomerServiceImpl();
			String customerChoice = input.next();
			switch (customerChoice) {

			// View loan Programs
			case "1":
				if (displayLoanPrograms()) {
					System.out.println("No Record Found");
				}
				break;
			case "2":

				System.out.println("Fill Loan Application Form:");

				System.out.println("Enter Loan Program Name");
				String loanProgram = input.next();
				while (!loanProcessingService.isValidString(loanProgram)) {
					System.err.println("Should contain a minimum of 5 Characters");
					System.out.println("Enter Loan Program Name");
					loanProgram = input.next();
				}

				System.out.println("Enter Loan Amount");
				String loanAmountString = input.next();
				while (!loanProcessingService.isValidDouble(loanAmountString)) {
					System.err.println("Not a valid amount");
					System.out.println("Enter Loan Amount");
					loanAmountString = input.next();
				}
				double loanAmount = Double.parseDouble(loanAmountString);

				System.out.println("Enter your Property Address");
				String propertyAddress = inputString.nextLine();

				System.out.println("Enter Annual Family Income");
				double annualFamilyIncome = input.nextDouble();

				System.out.println("Docs Proof to be diplayed");
				String docsProof = inputString.nextLine();

				System.out.println("Guarantee Cover Details ");
				String guaranteeCover = inputString.nextLine();

				System.out.println("Market Value Of your Guarantee Cover");
				double marktValOfCover = input.nextDouble();

				LoanApplicationBean loanApplication = new LoanApplicationBean(loanProgram, loanAmount, propertyAddress,
						annualFamilyIncome, docsProof, guaranteeCover, marktValOfCover);

				boolean status = customerService.applyLoan(loanApplication);

				if (status == true) {
					int applicationId = loanApplication.getApplicationId();
					System.out.println("Application Id for your Application is : " + applicationId);

					System.out.println("\n\nFill your Personal Details ");

					System.out.println("Enter your Full Name");
					String applicantName = inputString.nextLine();

					System.out.println("Input Date Of Birth in dd-MMM-yyyy Format");
					String dateOfBirth = input.next();
					// DateTimeFormatter formatter = DateTimeFormatter
					// .ofPattern("dd-MMM-yyyy");
					// LocalDate dateOfBirth = LocalDate.parse(dob, formatter);

					System.out.println("Maritial Status");
					String maritalStatus = input.next();

					System.out.println("Enter your Phone Number");
					long phoneNumber = input.nextLong();

					System.out.println("Enter your Mobile Number");
					long mobileNumber = input.nextLong();

					System.out.println("What is your Dependants Count ?");
					int dependentsCount = input.nextInt();

					System.out.println("Enter your Email ID");
					String emailId = input.next();

					CustomerDetailsBean customerDetails = new CustomerDetailsBean(applicationId, applicantName,
							dateOfBirth, maritalStatus, phoneNumber, mobileNumber, dependentsCount, emailId);
					status = customerService.fillCustomerDetails(customerDetails);

					if (status == true) {
						System.out.println("You successfully filled your Personal Details");
					} else {
						// Rollback Application
						customerService.deleteLoanApplication(applicationId);
						System.out.println("There was some problem in filling your Personal Details");
					}
				} else {
					System.out.println("There was some Problem Filling your Application Form");
				}

				break;
			case "3":

				System.out.println("Enter your Application Id");
				int applicationId = input.nextInt();

				String applicationStatusString = customerService.viewApplicationStatus(applicationId);
				System.out.println("Your status is : " + applicationStatusString);
				break;
			case "4":
				startTheProgram();
				break;
			case "5":
				System.out.println("Have a Nice Day :-) ");
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
			enteredAsCustomer();
		} catch (InputMismatchException e) {
			System.err.println("Error is in :" + e.getMessage());
		} catch (LoanProcessingException e) {
			System.err.println("Error is in :" + e.getMessage());
		}

	}

	// ================================================================
	// = 2. When User has Entered As
	// = Member Of Approval Department
	// ================================================================
	private static void enteredAsMemberOfBoard() {
		System.out.println("Welcome Member Of Loan Approval Department\n");
		System.out.println("Select Options from Given menu");
		System.out.println("1. View all Loan Programs");
		System.out.println("2. View All loan Applications for specific loan program");
		System.out.println("3. Update Status of Loan Application");
		System.out.println("4. Previous Page");
		System.out.println("5. Exit");
		try {
			ladService = new LoanApprovalDeptServiceImpl();
			String ladchoice = input.next();
			switch (ladchoice) {
			case "1":
				if (displayLoanPrograms()) {
					System.out.println("No Record Found");
				}
				break;
			case "2":
				System.out.println("Enter a Loan Program");
				String loanProgram = inputString.nextLine();

				if (displayLoanApplication(loanProgram)) {
					System.out.println("No Record Found");
				}

				break;
			case "3":
				int applicationId = 0;
				String newStatus = null;
				System.out.println("Enter application id to update application status");
				applicationId = input.nextInt();
				System.out.println("Select new status");
				System.out.println("1. Accepted");
				System.out.println("2. Approved");
				System.out.println("3. Rejected");
				String statusChoice = input.next();
				switch (statusChoice) {
				case "1":
					newStatus = "Accepted";
					break;
				case "2":
					newStatus = "Approved";
					break;
				case "3":
					newStatus = "Rejected";
					break;
				default:
					System.out.println("Invalid choice");
					enteredAsMemberOfBoard();
				}
				boolean isStatusUpdated = ladService.modifyApplicationStatus(applicationId, newStatus);
				if (isStatusUpdated == true) {
					System.out.println(
							"Application status for application id " + applicationId + " was successfully updated");
					if (newStatus == "Approved") {
						System.out.println("Fill Approved Loan Record Details");
						int applicationID = applicationId;
						String applicantName = ladService.getApplicantName(applicationId);
						// System.out.println("Enter Loan Amount Granted :- ");
						double loanAmountGranted = ladService.getLoanAmountGranted(applicationId);

						System.out.println("Enter Monthly Installments");
						double monthlyInstallments = input.nextDouble();

						// System.out.println("Enter Loan Time Period in Years");
						int yearsTimePeriod = ladService.getLoanDurationInYears(applicationId);

						System.out.println("Enter DownPayment to be given");
						double downPayment = input.nextDouble();

						// System.out.println("Enter Rate Of Interest");
						double rateOfInterest = ladService.getRateOfInterest(applicationId);

						System.out.println("Enter Total Payable Amount");
						double totalAmountPayable = input.nextDouble();

						ApprovedLoanBean approvedLoan = new ApprovedLoanBean(applicationID, applicantName,
								loanAmountGranted, monthlyInstallments, yearsTimePeriod, downPayment, rateOfInterest,
								totalAmountPayable);

						boolean isCustomerAdded = ladService.fillApprovedLoanDetails(approvedLoan);
						if (isCustomerAdded) {
							System.out.println("Loan is successfully Approved for Application ID : " + applicationId);
						} else {
							System.out.println(
									"There was Some problem in approving loan for Application Id : " + applicationId);
						}

					}
				} else {
					System.out.println("There was a problem in updating application status");
				}
				break;
			case "4":
				startTheProgram();
				break;
			case "5":
				System.out.println("Have a Nice Day :-) ");
				System.exit(0);

			default:
				System.out.println("Invalid choice");
			}
			enteredAsMemberOfBoard();
		} catch (InputMismatchException e) {
			System.err.println("Error is in :" + e.getMessage());
		} catch (LoanProcessingException e) {
			System.err.println("Error is in :" + e.getMessage());
		}
	}

	// Display Loan Application For Specific Loan Program

	public static boolean displayLoanApplication(String loanProgram) throws LoanProcessingException {
		boolean checkIfListIsEmpty = false;
		ArrayList<LoanApplicationBean> loanApplicationList = null;
		try {
			loanApplicationList = ladService.viewLoanApplicationForSpecificProgram(loanProgram);

			if (loanApplicationList.isEmpty()) {

				checkIfListIsEmpty = true;
			} else {

				for (LoanApplicationBean loanApplication : loanApplicationList) {
					System.out.println(loanApplication);
				}
			}
		} catch (LoanProcessingException e) {
			System.err.println("Error is " + e.getMessage());
		}
		return checkIfListIsEmpty;
	}

	// ================================================================
	// 3. When User has Entered As a Admin
	// ================================================================

	private static void enteredAsAdmin() {
		System.out.println("Welcome Admin");

		System.out.println("Select Options from menu");
		System.out.println("1. View all Loan Programs");
		System.out.println("2. Update Loan Program");
		System.out.println("3. View List of Loan Applications Approved/Accepted");
		System.out.println("4. Previous Page");
		System.out.println("5. Exit");
		try {
			adminService = new AdminServiceImpl();
			ladService = new LoanApprovalDeptServiceImpl();
			String adminChoice = input.next();
			switch (adminChoice) {
			case "1":
				if (displayLoanPrograms()) {
					System.out.println("No Record Found");
				}
				break;
			case "2":
				System.out.println("Select option :-");
				System.out.println("1. Add a Loan Program");
				System.out.println("2. Delete a Loan Program");
				String loanProgramUpdateChoice = input.next();
				switch (loanProgramUpdateChoice) {
				case "1":
					System.out.println("Enter Loan Program Name");
					String loanProgramString = input.next();
					System.out.println("Enter Loan Program Description");
					String description = inputString.nextLine();
					System.out.println("Specify Loan type");
					String loanType = input.next();
					System.out.println("Loan Program duration in years");
					int durationInYears = input.nextInt();
					System.out.println("Minimum Loan Amount");
					double minLoanAmnt = input.nextDouble();
					System.out.println("Maximum Loan Amount");
					double maxLoanAmnt = input.nextDouble();
					System.out.println("Rate of intrest on Loan");
					double rateOfIntrest = input.nextDouble();
					System.out.println("Mention all the proofs Required");
					String proofReq = inputString.nextLine();

					LoanProgramOfferedBean newLoanProgram = new LoanProgramOfferedBean(loanProgramString, description,
							loanType, durationInYears, minLoanAmnt, maxLoanAmnt, rateOfIntrest, proofReq);

					boolean isLoanProgramAdded = adminService.addLoanProgram(newLoanProgram);
					if (isLoanProgramAdded) {
						System.out.println("Loan Program Added Successfully");
					} else {
						System.out.println("There was Some Problem in Adding Loan Program");
					}
					break;
				case "2":
					System.out.println("Enter Loan Program name to be Deleted");
					String loanProgram = inputString.nextLine();

					if (isLoanProgramAlreadyExisting(loanProgram)) {
						System.out
								.println("This Loan Program Cannot be deleted as it exists in Loan Application table");
					} else {
						boolean isLoanProgramDeleted = adminService.deleteLoanProgram(loanProgram);
						if (isLoanProgramDeleted) {
							System.out.println("Loan Program Deleted Successfully");
						} else {
							System.out.println("There was some Problem in deleting Loan Program");
						}

					}

					break;
				default:
					System.out.println("Invalid Choice ");
					enteredAsAdmin();
				}
				break;
			case "3":
				System.out.println("1. View Loan Applications for Accepted Loan");
				System.out.println("2. View Loan Applicatoins for Approved Loan");
				ArrayList<LoanApplicationBean> loanApplicationList = null;
				String loanApplicationStatus = null;
				String loanApplicationStatusChoice = input.next();
				switch (loanApplicationStatusChoice) {
				case "1":
					loanApplicationStatus = "Accepted";
					break;
				case "2":
					loanApplicationStatus = "Approved";
					break;
				default:
					System.out.println("Invalid Choice ");
					enteredAsAdmin();
				}
				loanApplicationList = adminService.viewLoanApplicationForSpecificStatus(loanApplicationStatus);
				if (loanApplicationList.isEmpty()) {
					System.out.println("No Record Found");
				} else {
					for (LoanApplicationBean loanApplication : loanApplicationList) {
						System.out.println(loanApplication);
					}
				}
				break;
			case "4":
				startTheProgram();
			case "5":
				System.out.println("Have a Nice Day :-) ");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice ");
				enteredAsAdmin();

			}
			enteredAsAdmin();
		} catch (InputMismatchException e) {
			System.err.println("Error is in :" + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error is in :" + e.getMessage());

		}

	}

	// METHOD TO CHECK IF LOAN PROGRAM IS EXISTING IN LOAN APPLICATION TABLE
	public static boolean isLoanProgramAlreadyExisting(String loanProgram) throws LoanProcessingException {
		ArrayList<LoanApplicationBean> loanApplicationList = null;
		try {
			loanApplicationList = ladService.viewLoanApplicationForSpecificProgram(loanProgram);

			if (loanApplicationList.isEmpty()) {

				return false;
			}

		} catch (LoanProcessingException e) {
			System.err.println("Error is " + e.getMessage());
		}
		return true;

	}

	// ================================================================
	// 4. Common methods
	// ================================================================
	private static boolean displayLoanPrograms() throws LoanProcessingException {
		boolean checkIfListIsEmpty = false;
		ArrayList<LoanProgramOfferedBean> loanProgramList = null;
		loanProcessingService = new LoanProcessingServiceImpl();
		try {
			loanProgramList = loanProcessingService.viewLoanProgramsOffered();
			if (loanProgramList.isEmpty()) {
				checkIfListIsEmpty = true;
			} else {
				for (LoanProgramOfferedBean loanProgram : loanProgramList) {
					System.out.println(loanProgram);
				}
			}
		} catch (LoanProcessingException e) {
			System.err.println("Error is " + e.getMessage());
		}

		return checkIfListIsEmpty;
	}
}
