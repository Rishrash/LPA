package com.cg.lpa.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.service.CustomerServiceImpl;
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
		ladService = new LoanApprovalDeptServiceImpl();
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
	// =
	// =3. When User has Entered As a Admin
	// =
	// ================================================================

	private static void enteredAsAdmin() {
		System.out.println("Welcome Admin");
	}

	// ================================================================
	// = =
	// = 3. Common methods =
	// = =
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
