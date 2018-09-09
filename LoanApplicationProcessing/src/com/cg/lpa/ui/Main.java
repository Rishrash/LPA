package com.cg.lpa.ui;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.cg.lpa.bean.ApprovedLoanBean;
import com.cg.lpa.bean.CustomerDetailsBean;
import com.cg.lpa.bean.LoanApplicationBean;
import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.exception.LoanProcessingException;
import com.cg.lpa.service.AdminServiceImpl;
import com.cg.lpa.service.CustomerServiceImpl;
import com.cg.lpa.service.IAdminService;
import com.cg.lpa.service.ICustomerService;
import com.cg.lpa.service.ILoanApprovalDeptService;
import com.cg.lpa.service.ILoanProcessingService;
import com.cg.lpa.service.LoanApprovalDeptServiceImpl;
import com.cg.lpa.service.LoanProcessingServiceImpl;

public class Main {
    private static Scanner input;

    static ILoanProcessingService loanProcessingService = null;
    static ICustomerService customerService = null;
    static ILoanApprovalDeptService ladService = null;
    static IAdminService adminService = null;
    static Logger logg = Logger.getRootLogger();

    // MAIN METHOD
    public static void main(String[] args) throws LoanProcessingException {

        input = new Scanner(System.in);

        startTheProgram();
    }

    // START METHOD
    private static void startTheProgram() throws LoanProcessingException {

        System.out
                .println("xxxxxxxxxxxxxx  Welcome To Loan Processing Application xxxxxxxxxxxxxxxx");
        System.out
                .println("x                                                                     x");
        System.out
                .println("x          ---- Select your role from below ----                      x");
        System.out
                .println("x        1. Enter as a Customer                                       x");
        System.out
                .println("x        2. Login as Member Of Approval Department or Admin           x");
        System.out
                .println("x        3. Exit                                                      x");
        System.out
                .println("x                                                                     x");
        System.out
                .println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        String userInput = input.nextLine();

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
                System.err.println("Invalid choice");
        }
        startTheProgram();
    }

    // LOGIN METHOD
    private static void loginUser() throws LoanProcessingException {
        loanProcessingService = new LoanProcessingServiceImpl();
        input = new Scanner(System.in);
        System.out.print("UserId1 : ");
        String userId = input.nextLine();
        userId = userId.replaceAll("\\s", "");
        while (!loanProcessingService.isValidUserId(userId)) {
            System.err.println("Invalid User ID");
            System.out.print("UserId : ");
            userId = input.nextLine();
            userId = userId.replaceAll("\\s", "");
        }
        System.out.print("Password : ");
        String password = input.nextLine();
        password = password.replaceAll("\\s", "");
        while (!loanProcessingService.isValidPassword(password)) {
            System.err.println("Invalid Password");
            System.out.print("Password : ");
            password = input.nextLine();
            password = password.replaceAll("\\s", "");
        }

        try {
            if (loanProcessingService.loginUser(userId, password) == 0) {

                // Enter As Member of Loan Approval Board
                enteredAsMemberOfBoard();
                logg.info("Login As Member Of Approval Department");

            } else if (loanProcessingService.loginUser(userId, password) == 1) {

                // Enter As Admin
                enteredAsAdmin();
                logg.info("Login As Admin Of Approval Department");
            } else {

                // Incorrect Password
                System.err.println("UserID or Password is wrong");

            }
        } catch (LoanProcessingException e) {
            String errorString = e.getMessage();
            System.err.println("Error is in :" + errorString);
            logg.error("Error occured while trying login : " + errorString);

        }

    }

    // ================================================================
    // = 1. When User has Entered As a Customer
    // ================================================================

    private static void enteredAsCustomer() {

        System.out
                .println("############################ Welcome Customer ###############################");
        System.out
                .println("#                                                                           #");
        System.out
                .println("#        ---  Select Options from Given menu  ---                           #");
        System.out
                .println("#          1. View all Loan Programs                                        #");
        System.out
                .println("#          2. Apply for a new loan                                          #");
        System.out
                .println("#          3. View Application status of your Loan Application              #");
        System.out
                .println("#          4. Previous Page                                                 #");
        System.out
                .println("#          5. Exit			                                    #");
        System.out
                .println("#                                                                           #");
        System.out
                .println("#############################################################################");
        String loanProgram = null;
        boolean applyLoanStatus = false;
        boolean customerDetailStatus = false;
        int applicationId = 0;
        try {
            loanProcessingService = new LoanProcessingServiceImpl();
            customerService = new CustomerServiceImpl();
            String customerChoice = input.nextLine();
            switch (customerChoice) {

                // View loan Programs
                case "1":
                    if (displayLoanPrograms()) {
                        System.out.println("No Record Found");
                    }
                    break;
                case "2":
                    System.out
                            .println("------------------- Fill Loan Application Form ---------------------");

                    // Input Loan Program Name
                    System.out.println("Enter Loan Program Name");
                    loanProgram = input.nextLine();
                    loanProgram = loanProgram.replaceAll("\\s", "");
                    while (!loanProcessingService.isValidString(loanProgram)) {
                        System.err
                                .println("Should contain a minimum of 5 Characters");
                        System.out.println("Enter Loan Program Name");
                        loanProgram = input.nextLine();
                        loanProgram = loanProgram.replaceAll("\\s", "");
                    }
                    // Input Loan Amount
                    System.out.println("Enter Loan Amount");
                    String loanAmountString = input.nextLine();
                    loanAmountString = loanAmountString.replaceAll("\\s", "");
                    while (!loanProcessingService.isValidDouble(loanAmountString)) {
                        System.err.println("Not a valid amount");
                        System.out.println("Enter Loan Amount");
                        loanAmountString = input.nextLine();
                        loanAmountString = loanAmountString.replaceAll("\\s", "");
                    }
                    double loanAmount = Double.parseDouble(loanAmountString);

                    // Input Property Address
                    System.out.println("Enter your Property Address");
                    String propertyAddress = input.nextLine();
                    propertyAddress = propertyAddress.replaceAll("\\s", "");
                    while (!loanProcessingService.isValidAddress(propertyAddress)) {
                        System.err
                                .println("Property Address should not have more than 30 characters");
                        System.out.println("Enter your Property Address");
                        propertyAddress = input.nextLine();
                        propertyAddress = propertyAddress.replaceAll("\\s", "");
                    }

                    // Input Annual Family Income
                    System.out.println("Enter Annual Family Income");
                    String annualFamilyIncomeString = input.nextLine();
                    annualFamilyIncomeString = annualFamilyIncomeString.replaceAll(
                            "\\s", "");
                    while (!loanProcessingService
                            .isValidDouble(annualFamilyIncomeString)) {
                        System.err.println("Not a valid amount");
                        System.out.println("Enter Annual Family Income");
                        annualFamilyIncomeString = input.nextLine();
                        annualFamilyIncomeString = annualFamilyIncomeString
                                .replaceAll("\\s", "");

                    }

                    double annualFamilyIncome = Double
                            .parseDouble(annualFamilyIncomeString);

                    // Input Documents Proof to be Displayed
                    System.out.println("Docs Proof to be diplayed");
                    String docsProof = input.nextLine();
                    docsProof = docsProof.replaceAll("\\s", "");
                    while (!loanProcessingService.isValidDocsProofString(docsProof)) {
                        System.err
                                .println("Docs Proof Details should not be more than 50 characters");
                        System.out.println("Docs Proof to be diplayed");
                        docsProof = input.nextLine();
                        docsProof = docsProof.replaceAll("\\s", "");
                    }

                    // Input Guarantee Cover Details
                    System.out.println("Guarantee Cover Details ");
                    String guaranteeCover = input.nextLine();
                    guaranteeCover = guaranteeCover.replaceAll("\\s", "");
                    while (!loanProcessingService
                            .isValidGauranteeCoverString(guaranteeCover)) {
                        System.err
                                .println("Guarantee Cover Details  should not be more than 20 characters");
                        System.out.println("Guarantee Cover Details ");
                        guaranteeCover = input.nextLine();
                        guaranteeCover = guaranteeCover.replaceAll("\\s", "");
                    }

                    // Input Market Value Of Guarantee Cover
                    System.out.println("Market Value Of your Guarantee Cover");
                    String marktValOfCoverString = input.nextLine();
                    marktValOfCoverString = marktValOfCoverString.replaceAll("\\s",
                            "");
                    while (!loanProcessingService
                            .isValidDouble(marktValOfCoverString)) {
                        System.err.println("Not a valid amount");
                        System.out.println("Market Value Of your Guarantee Cover");
                        marktValOfCoverString = input.nextLine();
                        marktValOfCoverString = marktValOfCoverString.replaceAll(
                                "\\s", "");
                    }
                    double marktValOfCover = Double
                            .parseDouble(marktValOfCoverString);

                    // Saving Details In Loan Application Bean Class
                    LoanApplicationBean loanApplication = new LoanApplicationBean(
                            loanProgram, loanAmount, propertyAddress,
                            annualFamilyIncome, docsProof, guaranteeCover,
                            marktValOfCover);
                    // Inserting Data In Loan Application Table
                    applyLoanStatus = customerService.applyLoan(loanApplication);

                    // Checking If Data is Inserted Successfully
                    if (applyLoanStatus == true) {
                        logg.info("Data is successfully inserted in Loan Application Form");
                        applicationId = loanApplication.getApplicationId();
                        System.out
                                .println("Application Id for your Application is : "
                                        + applicationId);
                        logg.info("Loan Application For Application Id "
                                + applicationId + " was filled successfully");
                        System.out.println("\n\n");
                        System.out
                                .println("------------------- Fill your Personal Details ---------------------------");

                        // Enter Applicant Name
                        System.out.println("Enter your Full Name");
                        String applicantName = input.nextLine();
                        applicantName = applicantName.replaceAll("\\s", "");
                        while (!loanProcessingService
                                .isValidApplicantName(applicantName)) {
                            System.err
                                    .println("Name should contain not more than 20 characters");
                            System.out.println("Enter your Full Name");
                            applicantName = input.nextLine();
                            applicantName = applicantName.replaceAll("\\s", "");
                        }

                        // Input Date Of Birth
                        System.out
                                .println("Input Date Of Birth in dd-MMM-yyyy Format");
                        String dateOfBirth = input.nextLine();
                        dateOfBirth = dateOfBirth.replaceAll("\\s", "");
                        while (!loanProcessingService.isValidDate(dateOfBirth)) {
                            System.err.println("Invalid Date Format");
                            System.out
                                    .println("Input Date Of Birth in dd-MMM-yyyy Format");
                            dateOfBirth = input.nextLine();
                            dateOfBirth = dateOfBirth.replaceAll("\\s", "");

                        }

                        // Input Maritial Status
                        System.out.println("Maritial Status");
                        String maritalStatus = input.nextLine();
                        maritalStatus = maritalStatus.replaceAll("\\s", "");
                        while (!loanProcessingService
                                .isValidMaritalStatus(maritalStatus)) {
                            System.err.println("Invalid Maritial Status");
                            System.out.println("Maritial Status");
                            maritalStatus = input.nextLine();
                            maritalStatus = maritalStatus.replaceAll("\\s", "");

                        }

                        // Input Phone Number
                        System.out.println("Enter your Phone Number");
                        String phoneNumberString = input.nextLine();
                        phoneNumberString = phoneNumberString.replaceAll("\\s", "");
                        while (!loanProcessingService
                                .isValidPhoneNumber(phoneNumberString)) {
                            System.err
                                    .println("Phone Number should contain 10 digits");
                            System.out.println("Enter your Phone Number");
                            phoneNumberString = input.nextLine();
                            phoneNumberString = phoneNumberString.replaceAll("\\s",
                                    "");
                        }
                        long phoneNumber = Long.parseLong(phoneNumberString);

                        // Input Mobile Number
                        System.out.println("Enter your Mobile Number");
                        String mobileNumberString = input.nextLine();
                        mobileNumberString = mobileNumberString.replaceAll("\\s",
                                "");
                        while (!loanProcessingService
                                .isValidPhoneNumber(mobileNumberString)) {
                            System.err
                                    .println("Phone Number should contain 10 digits");
                            System.out.println("Enter your Mobile Number");
                            mobileNumberString = input.nextLine();
                            mobileNumberString = mobileNumberString.replaceAll(
                                    "\\s", "");
                        }
                        long mobileNumber = Long.parseLong(mobileNumberString);

                        // Input Dependents Count
                        System.out.println("What is your Dependants Count ?");
                        String dependentsCountString = input.nextLine();
                        dependentsCountString = dependentsCountString.replaceAll(
                                "\\s", "");
                        while (!loanProcessingService
                                .isValidNumber(dependentsCountString)) {
                            System.err.println("Enter a valid Number");
                            System.out.println("What is your Dependants Count ?");
                            dependentsCountString = input.nextLine();
                            dependentsCountString = dependentsCountString
                                    .replaceAll("\\s", "");

                        }
                        int dependentsCount = Integer
                                .parseInt(dependentsCountString);

                        // Input Email Id
                        System.out.println("Enter your Email ID");
                        String emailId = input.nextLine();
                        emailId = emailId.replaceAll("\\s", "");
                        while (!loanProcessingService.isValidEmailId(emailId)) {
                            System.err.println("Not a Valid emailId");
                            System.out.println("Enter your Email ID");
                            emailId = input.nextLine();
                            emailId = emailId.replaceAll("\\s", "");
                        }
                        CustomerDetailsBean customerDetails = new CustomerDetailsBean(
                                applicationId, applicantName, dateOfBirth,
                                maritalStatus, phoneNumber, mobileNumber,
                                dependentsCount, emailId);
                        customerDetailStatus = customerService
                                .fillCustomerDetails(customerDetails);

                        if (customerDetailStatus == true) {
                            System.out
                                    .println("You successfully filled your Personal Details");
                            logg.info("New Loan Application is generated");
                        } else {
                            // Rollback Application
                            customerService.deleteLoanApplication(applicationId);
                            System.err
                                    .println("There was some problem in filling your Personal Details");
                            logg.error("There was some problem in filling your Personal Details For a Customer");
                        }
                    } else {
                        System.err
                                .println("There was some Problem Filling your Application Form");
                        logg.error("There was some Problem Filling Application Form For a Customer");
                    }
                    break;

                case "3":
                    // Input Application Id To Get your Application status
                    System.out.println("Enter your Application Id");
                    String applicationIdString = input.nextLine();
                    applicationIdString = applicationIdString.replaceAll("\\s", "");
                    while (!loanProcessingService
                            .isValidNumber(applicationIdString)) {
                        System.err.println("Invalid Application Id");
                        System.out.println("Enter your Application Id");
                        applicationIdString = input.nextLine();
                        applicationIdString = applicationIdString.replaceAll("\\s",
                                "");
                    }
                    applicationId = Integer.parseInt(applicationIdString);

                    String applicationStatusString = customerService
                            .viewApplicationStatus(applicationId);
                    if (applicationStatusString == null)
                        System.err.println("No record Found");
                    else
                        System.out.println("Your status is : "
                                + applicationStatusString);
                    break;
                case "4":
                    startTheProgram();
                    break;
                case "5":
                    System.out.println("Have a Nice Day :-) ");
                    System.exit(0);
                default:
                    System.err.println("Invalid choice");
            }
            enteredAsCustomer();
        } catch (LoanProcessingException e) {
            String errorString = e.getMessage();
            System.err.println("Error is in :" + errorString);
            logg.error("Customer faced some error : " + errorString);
            enteredAsCustomer();

        }

    }

    // ================================================================
    // 2. When User has Entered As
    // Member Of Approval Department
    // ================================================================
    private static void enteredAsMemberOfBoard() {
        System.out
                .println("############################# Welcome Member Of Loan Approval Department #############################");
        System.out
                .println("#                                                                                                    #");
        System.out
                .println("#                        ----Select Options from Given menu----                                      #");
        System.out
                .println("#                        1. View all Loan Programs                                                   #");
        System.out
                .println("#                        2. View All loan Applications for specific loan program                     #");
        System.out
                .println("#                        3. Update Status of Loan Application                                        #");
        System.out
                .println("#                        4. Previous Page                                                            #");
        System.out
                .println("#                        5. Exit                                                                     #");
        System.out
                .println("#                                                                                                    #");
        System.out
                .println("######################################################################################################");
        try {
            ladService = new LoanApprovalDeptServiceImpl();
            loanProcessingService = new LoanProcessingServiceImpl();
            String ladchoice = input.nextLine();
            switch (ladchoice) {
                case "1":
                    if (displayLoanPrograms()) {
                        System.err.println("No Record Found");
                    }
                    break;
                case "2":
                    // Input a Loan Program to display all Loan Applications related
                    // to that Program
                    System.out.println("Enter a Loan Program");
                    String loanProgram = input.nextLine();
                    loanProgram = loanProgram.replaceAll("\\s", "");
                    while (!loanProcessingService.isValidString(loanProgram)) {
                        System.err
                                .println("Should contain a minimum of 5 Characters");
                        System.out.println("Enter Loan Program Name");
                        loanProgram = input.nextLine();
                        loanProgram = loanProgram.replaceAll("\\s", "");
                    }

                    if (displayLoanApplication(loanProgram)) {
                        System.err.println("No Record Found");
                    }

                    break;
                case "3":
                    // Input Application Id To Update Application Status
                    customerService = new CustomerServiceImpl();
                    System.out
                            .println("Enter a application id to update application status");
                    String applicationIdString = input.nextLine();
                    applicationIdString = applicationIdString.replaceAll("\\s", "");
                    while (!loanProcessingService
                            .isValidNumber(applicationIdString)) {
                        System.err.println("Invalid Application Id");
                        System.out.println("Enter your Application Id");
                        applicationIdString = input.nextLine();
                        applicationIdString = applicationIdString.replaceAll("\\s",
                                "");
                    }
                    int applicationId = Integer.parseInt(applicationIdString);
                    String applicationStatusString = customerService
                            .viewApplicationStatus(applicationId);
                    if (applicationStatusString == null) {
                        System.err.println("No Application Id Found");
                        enteredAsMemberOfBoard();
                    }
                    // Select Application Status
                    System.out.println("Select new status");
                    System.out.println("1. Accepted");
                    System.out.println("2. Approved");
                    System.out.println("3. Rejected");
                    String statusChoice = input.nextLine();
                    String newStatus = null;
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
                            System.err.println("Invalid choice");
                            enteredAsMemberOfBoard();
                    }
                    if (newStatus.equals(applicationStatusString)) {
                        System.err
                                .println("Status is already Changed to What you are entering");
                        enteredAsMemberOfBoard();
                    } else if (applicationStatusString.equals("Rejected")) {
                        System.err
                                .println("Loan Application Status Cannot Be Changed As It is Already Rejected");
                        enteredAsMemberOfBoard();
                    } else if (newStatus.equals("Approved")
                            && applicationStatusString.equals("Applied")) {
                        System.err
                                .println("You Can't Approve a loan without changing it's status to Accepted");
                        enteredAsMemberOfBoard();
                    }
                    // Check If Status is Updated Successfully
                    boolean isStatusUpdated = ladService.modifyApplicationStatus(
                            applicationId, newStatus);

                    if (isStatusUpdated == true) {
                        System.out.println("Application status for application id "
                                + applicationId + " was successfully updated");
                        logg.info("Application status for application id "
                                + applicationId + " was successfully updated");

                        // If Status Is changed to Approved fill And Generate
                        // Approved Loan Details
                        if (newStatus == "Accepted") {
                            ladService.setInterviewDate(applicationId);
                        } else if (newStatus == "Approved") {
                            int applicationID = applicationId;

                            // Get Applicant Name From Customer_Details Table
                            String applicantName = ladService
                                    .getApplicantName(applicationId);

                            // Get Loan Amount Granted From Loan_Application Table
                            double loanAmountGranted = ladService
                                    .getLoanAmountGranted(applicationId);

                            // Get Loan Time Period in Years From
                            // Loan_Programs_Offered Table
                            int yearsTimePeriod = ladService
                                    .getLoanDurationInYears(applicationId);

                            // Get Rate Of Interest From Loan_Programs_Offered Table
                            double rateOfInterest = ladService
                                    .getRateOfInterest(applicationId);

                            // Calculate DownPayment for loan
                            double downPayment = loanAmountGranted / 10;

                            // Calculate Monthly Installments
                            double monthlyInstallments = (loanAmountGranted - downPayment)
                                    * rateOfInterest
                                    * 1.5
                                    / (yearsTimePeriod * 12 * 10);

                            // Calculate Total Payable Loan
                            double totalAmountPayable = (monthlyInstallments * 12 * yearsTimePeriod)
                                    + downPayment;

                            ApprovedLoanBean approvedLoan = new ApprovedLoanBean(
                                    applicationID, applicantName,
                                    loanAmountGranted, monthlyInstallments,
                                    yearsTimePeriod, downPayment, rateOfInterest,
                                    totalAmountPayable);

                            // Add Data To Approved Loans
                            boolean isCustomerAdded = ladService
                                    .fillApprovedLoanDetails(approvedLoan);

                            // Check If Data Is Successfully Added
                            if (isCustomerAdded) {
                                System.out
                                        .println("Loan is successfully Approved for Application ID : "
                                                + applicationId);
                                System.out
                                        .println("------------- Approved Loan Details -------------------");
                                System.out.println(approvedLoan);
                            } else {
                                System.err
                                        .println("There was Some problem in approving loan for Application Id : "
                                                + applicationId);
                            }

                        }
                    } else {
                        System.err
                                .println("There was a problem in updating application status");
                    }
                    break;
                case "4":
                    startTheProgram();
                    break;
                case "5":
                    System.out.println("Have a Nice Day :-) ");
                    System.exit(0);

                default:
                    System.err.println("Invalid choice");
            }
            enteredAsMemberOfBoard();
        } catch (LoanProcessingException e) {
            String errorString = e.getMessage();
            System.err.println("Error is in :" + errorString);
            logg.error("Member Of Loan Approval Department Faced Error in : "
                    + errorString);
            enteredAsMemberOfBoard();
        }
    }

    // ================================================================
    // 3. When User has Entered As a Admin
    // ================================================================

    private static void enteredAsAdmin() {
        System.out
                .println("############################ Welcome Admin ####################################");
        System.out
                .println("#                                                                             #");

        System.out
                .println("#              ---Slect Options from menu--                                   #");
        System.out
                .println("#              1. View all Loan Programs                                      #");
        System.out
                .println("#              2. Update Loan Program                                         #");
        System.out
                .println("#              3. View List of Loan Applications Approved/Accepted            #");
        System.out
                .println("#              4. View Approved Loans                                         #");
        System.out
                .println("#              5. Previous Page                                               #");
        System.out
                .println("#              6. Exit                                                        #");
        System.out
                .println("#                                                                             #");
        System.out
                .println("###############################################################################");
        try {
            adminService = new AdminServiceImpl();
            ladService = new LoanApprovalDeptServiceImpl();
            loanProcessingService = new LoanProcessingServiceImpl();
            String adminChoice = input.nextLine();
            switch (adminChoice) {
                case "1":
                    if (displayLoanPrograms()) {
                        System.err.println("No Record Found");
                    }
                    break;
                case "2":
                    System.out.println("--------- Select option -----------");
                    System.out.println("1. Add a Loan Program");
                    System.out.println("2. Delete a Loan Program");
                    String loanProgramUpdateChoice = input.nextLine();
                    switch (loanProgramUpdateChoice) {
                        case "1":

                            // Input Loan Program
                            System.out.println("Enter Loan Program Name");
                            String loanProgramString = input.nextLine();
                            loanProgramString = loanProgramString.replaceAll("\\s", "");
                            while (!loanProcessingService
                                    .isValidString(loanProgramString)) {
                                System.err
                                        .println("Should contain a minimum of 5 Characters");
                                System.out.println("Enter Loan Program Name");
                                loanProgramString = input.nextLine();
                                loanProgramString = loanProgramString.replaceAll("\\s",
                                        "");
                            }

                            // Input Loan Program Description
                            System.out.println("Enter Loan Program Description");
                            String description = input.nextLine();
                            description = description.replaceAll("\\s", "");
                            while (!loanProcessingService
                                    .isValidLoanProgramDescription(description)) {
                                System.err
                                        .println("Loan Program Description  should not be more than 20 characters");
                                System.out.println("Enter Loan Program Description");
                                description = input.nextLine();
                                description = description.replaceAll("\\s", "");
                            }

                            // Input Loan Type
                            System.out.println("Specify Loan type");
                            String loanType = input.nextLine();
                            loanType = loanType.replaceAll("\\s", "");
                            while (!loanProcessingService.isValidLoanType(loanType)) {
                                System.err.println("Not a Valid Loan Type");
                                System.out.println("Specify Loan type");
                                loanType = input.nextLine();
                                loanType = loanType.replaceAll("\\s", "");
                            }

                            // Input Loan Program Duration in Years
                            System.out.println("Loan Program duration in years");
                            String durationInYearsString = input.nextLine();
                            durationInYearsString = durationInYearsString.replaceAll(
                                    "\\s", "");
                            while (!loanProcessingService
                                    .isValidNumber(durationInYearsString)) {
                                System.err.println("Invalid Loan Duration");
                                System.out.println("Loan Program duration in years");
                                durationInYearsString = input.nextLine();
                                durationInYearsString = durationInYearsString
                                        .replaceAll("\\s", "");
                            }
                            int durationInYears = Integer
                                    .parseInt(durationInYearsString);

                            // Input Minimum Loan Amount
                            System.out.println("Minimum Loan Amount");
                            String minLoanAmntString = input.nextLine();
                            minLoanAmntString = minLoanAmntString.replaceAll("\\s", "");
                            while (!loanProcessingService
                                    .isValidDouble(minLoanAmntString)) {
                                System.err.println("Invalid amount");
                                System.out.println("Minimum Loan Amount");
                                minLoanAmntString = input.nextLine();
                                minLoanAmntString = minLoanAmntString.replaceAll("\\s",
                                        "");
                            }
                            double minLoanAmnt = Double.parseDouble(minLoanAmntString);

                            // Input Maximum Loan Amount
                            System.out.println("Maximum Loan Amount");
                            String maxLoanAmntString = input.nextLine();
                            maxLoanAmntString = maxLoanAmntString.replaceAll("\\s", "");
                            while (!loanProcessingService
                                    .isValidDouble(maxLoanAmntString)) {
                                System.err.println("Invalid amount");
                                System.out.println("Maximum Loan Amount");
                                maxLoanAmntString = input.nextLine();
                                maxLoanAmntString = maxLoanAmntString.replaceAll("\\s",
                                        "");
                            }
                            double maxLoanAmnt = Double.parseDouble(maxLoanAmntString);

                            // Input Rate Of Interest
                            System.out.println("Rate of intrest on Loan");
                            String rateOfIntrestString = input.nextLine();
                            rateOfIntrestString = rateOfIntrestString.replaceAll("\\s",
                                    "");
                            while (!loanProcessingService
                                    .isValidDouble(rateOfIntrestString)) {
                                System.err.println("Invalid amount");
                                System.out.println("Rate of intrest on Loan");
                                rateOfIntrestString = input.nextLine();
                                rateOfIntrestString = rateOfIntrestString.replaceAll(
                                        "\\s", "");
                            }
                            double rateOfIntrest = Double
                                    .parseDouble(rateOfIntrestString);

                            // Input Guarantee Proof Required
                            System.out.println("Mention all the proofs Required");
                            String proofReq = input.nextLine();
                            proofReq = proofReq.replaceAll("\\s", "");
                            while (!loanProcessingService
                                    .isValidGauranteeCoverString(proofReq)) {
                                System.err
                                        .println("Proof details should not be more than 20 characters");
                                System.out.println("Mention all the proofs Required");
                                proofReq = input.nextLine();
                                proofReq = proofReq.replaceAll("\\s", "");
                            }

                            LoanProgramOfferedBean newLoanProgram = new LoanProgramOfferedBean(
                                    loanProgramString, description, loanType,
                                    durationInYears, minLoanAmnt, maxLoanAmnt,
                                    rateOfIntrest, proofReq);

                            boolean isLoanProgramAdded = adminService
                                    .addLoanProgram(newLoanProgram);
                            if (isLoanProgramAdded) {
                                System.out.println("Loan Program Added Successfully");
                                logg.info("Admin Added a new Loan Program");
                            } else {
                                System.err
                                        .println("There was Some Problem in Adding Loan Program");
                            }
                            break;
                        case "2":

                            // Input Loan Program To Delete Details
                            System.out.println("Enter Loan Program name to be Deleted");
                            String loanProgram = input.nextLine();
                            while (!loanProcessingService.isValidString(loanProgram)) {
                                System.err
                                        .println("Should contain a minimum of 5 Characters");
                                System.out.println("Enter Loan Program Name");
                                loanProgram = input.nextLine();
                            }

                            if (isLoanProgramAlreadyExisting(loanProgram)) {
                                System.err
                                        .println("This Loan Program Cannot be deleted as it exists in Loan Application table");
                            } else {
                                boolean isLoanProgramDeleted = adminService
                                        .deleteLoanProgram(loanProgram);
                                if (isLoanProgramDeleted) {
                                    System.out
                                            .println("Loan Program Deleted Successfully");
                                    logg.info("Admin Deleted a Loan Program");
                                } else {
                                    System.err
                                            .println("There was some Problem in deleting Loan Program");
                                    logg.error("There was Some Problem in deleting a loan Program");
                                }

                            }

                            break;
                        default:
                            System.err.println("Invalid Choice ");
                            enteredAsAdmin();
                    }
                    break;
                case "3":
                    System.out
                            .println("1. View Loan Applications for Accepted Loan");
                    System.out
                            .println("2. View Loan Applicatoins for Approved Loan");
                    ArrayList<LoanApplicationBean> loanApplicationList = null;
                    String loanApplicationStatus = null;
                    String loanApplicationStatusChoice = input.nextLine();
                    switch (loanApplicationStatusChoice) {
                        case "1":
                            loanApplicationStatus = "Accepted";
                            break;
                        case "2":
                            loanApplicationStatus = "Approved";
                            break;
                        default:
                            System.err.println("Invalid Choice ");
                            enteredAsAdmin();
                    }

                    loanApplicationList = adminService
                            .viewLoanApplicationForSpecificStatus(loanApplicationStatus);

                    if (loanApplicationList.isEmpty()) {
                        System.err.println("No Record Found");
                    } else {
                        System.out
                                .println("---------------- Loan Applications -----------------");
                        for (LoanApplicationBean loanApplication : loanApplicationList) {
                            System.out.println(loanApplication);
                        }
                        System.out
                                .println("--------------------------------------------------");
                    }
                    break;
                case "4":
                    if (displayApprovedLoan()) {
                        System.err.println("No Record Found");
                    }
                    break;

                case "5":
                    startTheProgram();
                case "6":
                    System.out.println("Have a Nice Day :-) ");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid Choice ");
                    enteredAsAdmin();

            }
            enteredAsAdmin();
        } catch (Exception e) {
            String errorString = e.getMessage();
            System.err.println("Error is in :" + errorString);
            logg.error("Admin faced error in : " + errorString);

        }

    }

    // Display Approved Loans
    public static boolean displayApprovedLoan() throws LoanProcessingException {
        boolean checkIfListIsEmpty = false;
        adminService = new AdminServiceImpl();
        ArrayList<ApprovedLoanBean> approvedLoanList = null;
        int count = 1;
        try {
            approvedLoanList = adminService.viewApprovedLoan();

            if (approvedLoanList.isEmpty()) {

                checkIfListIsEmpty = true;
            } else {
                System.out
                        .println("---------------- Approved Loans -----------------");

                for (ApprovedLoanBean approvedLoan : approvedLoanList) {
                    System.out.println("Record :" + count++);
                    System.out.println(approvedLoan);
                }
            }
            System.out
                    .println("------------------------------------------------------\n\n");
        } catch (LoanProcessingException e) {
            System.err.println("Error is " + e.getMessage());
            logg.error("Exception occured while Displaying Approved Loans");
        }
        return checkIfListIsEmpty;
    }

    // METHOD TO CHECK IF LOAN PROGRAM IS EXISTING IN LOAN APPLICATION TABLE

    public static boolean isLoanProgramAlreadyExisting(String loanProgram)
            throws LoanProcessingException {
        ArrayList<LoanApplicationBean> loanApplicationList = null;
        try {
            loanApplicationList = ladService
                    .viewLoanApplicationForSpecificProgram(loanProgram);

            if (loanApplicationList.isEmpty()) {

                return false;
            }

        } catch (LoanProcessingException e) {
            System.err.println("Error is " + e.getMessage());
        }
        return true;

    }

    // Display Loan Application For Specific Loan Program

    public static boolean displayLoanApplication(String loanProgram)
            throws LoanProcessingException {
        boolean checkIfListIsEmpty = false;
        int count = 1;
        ArrayList<LoanApplicationBean> loanApplicationList = null;
        try {
            loanApplicationList = ladService
                    .viewLoanApplicationForSpecificProgram(loanProgram);

            if (loanApplicationList.isEmpty()) {

                checkIfListIsEmpty = true;
            } else {
                System.out
                        .println("----------------Loan Applications-----------------");
                for (LoanApplicationBean loanApplication : loanApplicationList) {
                    System.out.println("Record :" + count++);
                    System.out.println(loanApplication);
                }
            }
            System.out
                    .println("------------------------------------------------------\n\n");
        } catch (LoanProcessingException e) {
            System.err.println("Error is " + e.getMessage());
            logg.error("Exception occured while Displaying Loan Applications");
        }
        return checkIfListIsEmpty;
    }

    // Display All Loan Programs

    private static boolean displayLoanPrograms() throws LoanProcessingException {
        boolean checkIfListIsEmpty = false;
        ArrayList<LoanProgramOfferedBean> loanProgramList = null;
        int count = 1;
        loanProcessingService = new LoanProcessingServiceImpl();
        try {
            loanProgramList = loanProcessingService.viewLoanProgramsOffered();
            if (loanProgramList.isEmpty()) {
                checkIfListIsEmpty = true;
            } else {
                System.out
                        .println("-------------------Offered Loan Programs------------------------");
                for (LoanProgramOfferedBean loanProgram : loanProgramList) {
                    System.out.println("Record :" + count++);
                    System.out.println(loanProgram);
                }
                System.out
                        .println("----------------------------------------------------------------\n\n");
            }
        } catch (LoanProcessingException e) {
            System.err.println("Error is " + e.getMessage());
            logg.error("Exception occured while Displaying Loan Programs");
        }

        return checkIfListIsEmpty;
    }
}
