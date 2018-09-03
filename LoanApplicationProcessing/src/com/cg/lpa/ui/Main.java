package com.cg.lpa.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

		int userInput = input.nextInt();

		switch (userInput) {
		case 1:
			enteredAsCustomer();
			break;
		case 2:
			loginUser();
			break;
		case 3:
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
		System.out.print("Password : ");
		String password = input.next();

		try {
			if (loanProcessingService.loginUser(userId, password) == 0) {
				// Enter As Member of Loan Approval Board
				enteredAsMemberOfBoard();

			} else if (loanProcessingService.loginUser(userId, password) == 1) {
				// Enter As Admin
				enteredAsAdmin();

			} else {
				// Incorrect Password
				System.out.println("Password is wrong");

			}
		} catch (LoanProcessingException e) {

			throw new LoanProcessingException("problem : " + e.getMessage());
		}

	}

	// ================================================================
	// = =
	// = 1. When User has Entered As a Customer
	// =
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
			customerService = new CustomerServiceImpl();
			int customerChoice = input.nextInt();
			switch (customerChoice) {

			// View loan Programs
			case 1:
				if (displayLoanPrograms()) {
					System.out.println("No Record Found");
				}
				break;
			case 2:

				System.out.println("Fill Loan Application Form:");

				System.out.println("Enter Loan Program Name");
				String loanProgram = input.next();

				System.out.println("Enter Loan Amount");
				double loanAmount = input.nextDouble();

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

					System.out.println("Input Date Of Birth in dd-mm-yyyy Format");
					String dateOfBirth = input.next();
					// DateTimeFormatter formatter = DateTimeFormatter
					// .ofPattern("dd-mm-yyyy");
					// LocalDate dateOfBirth = LocalDate.parse(date, formatter);

					System.out.println("Maritial Status");
					String maritalStatus = input.next();

					System.out.println("Enter your Phone Number");
					long phoneNumber = input.nextLong();

					System.out.println("Enter your Mobile Number");
					long mobileNumber = input.nextLong();

					System.out.println("What is your Dependents Count ?");
					int dependentsCount = input.nextInt();

					System.out.println("Enter your Email ID");
					String emailId = input.next();

					CustomerDetailsBean customerDetails = new CustomerDetailsBean(applicationId, applicantName,
							dateOfBirth, maritalStatus, phoneNumber, mobileNumber, dependentsCount, emailId);
					status = customerService.fillCustomerDetails(customerDetails);

					if (status == true) {
						System.out.println("You successfully filled your Personal Details");
					} else {
						System.out.println("There was some problem in filling your Personal Details");
					}
				} else {
					System.out.println("There was some Problem Filling your Application Form");
				}

				break;
			case 3:

				System.out.println("Enter your Application Id");
				int applicationId = input.nextInt();

				String applicationStatusString = customerService.viewApplicationStatus(applicationId);
				System.out.println("Your status is : " + applicationStatusString);
				break;
			case 4:
				startTheProgram();
				break;
			case 5:
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
	// =
	// = 2. When User has Entered As
	// = ...Member Of Approval Department
	// =
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
			int ladchoice = input.nextInt();
			switch (ladchoice) {
			case 1:
				if (displayLoanPrograms()) {
					System.out.println("No Record Found");
				}
				break;
			case 2:
				System.out.println("Choose a Loan Program :-");
				System.out.println("1. HomeL");
				System.out.println("2. EduL");
				System.out.println("3. CarL");
				int choice = input.nextInt();
				String loanProgram = null;
				switch (choice) {
				case 1:
					loanProgram = "HomeL";
					break;
				case 2:
					loanProgram = "EduL";
					break;
				case 3:
					loanProgram = "CarL";
					break;
				default:
					System.out.println("Invalid choice");
					enteredAsMemberOfBoard();
				}

				if (displayLoanApplication(loanProgram)) {
					System.out.println("No Record Found");
				}

				break;
			case 3:
				int applicationId = 0;
				String newStatus = null;
				System.out.println("Enter application id to update application status");
				applicationId = input.nextInt();
				System.out.println("Select new status");
				System.out.println("1. Accepted");
				System.out.println("2. Approved");
				System.out.println("3. Rejected");
				int statusChoice = input.nextInt();
				switch (statusChoice) {
				case 1:
					newStatus = "Accepted";
					break;
				case 2:
					newStatus = "Approved";
					break;
				case 3:
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
				} else {
					System.out.println("There was a problem in updating application status");
				}
				break;
			case 4:
				startTheProgram();
				break;
			case 5:
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
			int adminChoice = input.nextInt();
			switch (adminChoice) {
			case 1:
				if (displayLoanPrograms()) {
					System.out.println("No Record Found");
				}
				break;
			case 2:
				System.out.println("Select option :-");
				System.out.println("1. Add a Loan Program");
				System.out.println("2. Delete a Loan Program");
				int loanProgramUpdateChoice = input.nextInt();
				switch (loanProgramUpdateChoice) {
				case 1:
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

					boolean b = adminService.addLoanProgram(newLoanProgram);
					if (b) {
						System.out.println("YEaaah");
					} else {
						System.out.println("Nooooo");
					}
					break;
				case 2:
					// adminService.deleteLoanProgram(loanProgram);
					break;
				default:
					System.out.println("Invalid Choice ");
					enteredAsAdmin();
				}
				break;
			case 3:
				System.out.println("1. View Loan Applications for Accepted Loan");
				System.out.println("2. View Loan Applicatoins for Approved Loan");
				ArrayList<LoanApplicationBean> loanApplication = null;
				String loanApplicationStatus = null;
				int loanApplicationStatusChoice = input.nextInt();
				switch (loanApplicationStatusChoice) {
				case 1:
					loanApplicationStatus = "Accepted";
					break;
				case 2:
					loanApplicationStatus = "Approved";
					break;
				default:
					System.out.println("Invalid Choice ");
					enteredAsAdmin();
				}
				loanApplication = adminService.viewLoanApplicationForSpecificStatus(loanApplicationStatus);
				break;
			case 4:
				startTheProgram();
			case 5:
				System.out.println("Have a Nice Day :-) ");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice ");
				enteredAsAdmin();

			}
		} catch (InputMismatchException e) {
			System.err.println("Error is in :" + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error is in :" + e.getMessage());

		}

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
