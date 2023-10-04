/**
 * 
 */
package Bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides the main application for interacting with the central bank operations.
 * <p>
 * It offers a menu-driven interface to interact with the following banking functions:
 * <ul>
 *     <li>Add Customer</li>
 *     <li>Remove Customer</li>
 *     <li>Deposit</li>
 *     <li>Withdraw</li>
 *     <li>Create Account</li>
 *     <li>Close Account</li>
 *     <li>Transfer</li>
 *     <li>Get Transaction History</li>
 *     <li>Get Customer Summary</li>
 *     <li>Exit</li>
 * </ul>
 * </p>
 * <p>
 * The application assumes a single central bank instance and all operations are performed on this instance.
 * </p>
 */
public class centralBankApp {

	/**
     * Main entry point for the application.
     * 
     * @param args Command line arguments (not used in this application).
     */
	public static void main(String[] args) {
		CentralBank HSBC = new CentralBank();
		
		while(true) {
			Menu m = new Menu("HSBC Bank App", options);
			int choice =m.getUserChoice();
			
			if(choice ==10) {
				System.out.println("Thank you for using our bank");
				break;
			}
			processChoice(choice, HSBC);
			
		}
		

	}
	
	 /**
     * Provides the menu options for the user interface.
     */
	private static String[] options = {
		"Welcome to HSBC banking, glad you're banking with us! Please select one of our options below.",
		"Add Customer",
		"Remove Customer",
		"Deposit",
		"Withdraw",
		"Create Account",
		"Close Account",
		"Transfer",
		"Get Transaction History",
		"Get Customer Summary",
		"Exit"
		
	};
	
	 /**
     * Processes the choice made by the user from the main menu.
     * 
     * @param choice The choice selected by the user.
     * @param bank The instance of the CentralBank on which operations are to be performed.
     */
	private static void processChoice(int choice, CentralBank bank) {
		switch(choice) {
		
		case 1: 
			handleAddCustomer(bank);
			break;
		case 2: 
			handleRemoveCustomer(bank);
			break;
		case 3: 
			deposit(bank);
			break;
		case 4:
			handleWithdraw(bank);  
            break;
        case 5:
        	 handleCreateAccount(bank);
            break;
        case 6:
            handleCloseAccount(bank);
            break;
        case 7:
        	handleTransfer(bank);
            break;
        case 8:
        	handleDisplayTransactionHistory(bank); 
        case 9: 
        	handleGetCustomerSummary(bank);
            break;
            
        default:
            System.out.println("Invalid choice.");
            break;
			
		
		
		}
	}
	 /**
     * Handles the addition of a new customer.
     * 
     * @param bank The instance of the CentralBank where the customer will be added.
     */
	private static void handleAddCustomer (CentralBank bank) {
		Scanner sc1 = new Scanner(System.in);
		
		System.out.println("Please enter your name");
		String name = sc1.nextLine();
		
		System.out.println("Please enter your address");
		String address = sc1.nextLine();
		
		System.out.println("Please enter your date of birth - (Use format dd/MM/yyyy)");
		String DOBstring = sc1.nextLine();
		
		Date DOB = null;
		do {
		try {
			SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
			DOB = SDF.parse(DOBstring);
			
		} catch (ParseException e) {
			System.out.println("Invalid, please enter correct format.");
		}
		
		
		
		} while (DOB ==null);
		Customer newCustomer = new Customer(name, address, DOB);
		bank.addCustomer(newCustomer);
		
		System.out.println("Success!");
		
		
	}
	/**
     * Handles the removal of an existing customer.
     * 
     * @param bank The instance of the CentralBank where the customer will be removed.
     */
	private static void handleRemoveCustomer (CentralBank bank) {
		Scanner sc1 = new Scanner(System.in);
		
		System.out.println("Leaving? If so, please enter your customer ID number so we can delete you from existance");
		int customersID = sc1.nextInt();
		
		Customer customerRemoval = bank.getCustomerByID(customersID);
		
		if(bank.removeCustomer(customerRemoval)) {
			System.out.println("You've been removed");
		} else {
			System.out.println("Removal unsuccessful");
		}
		
			
	}
	/**
     * Handles the deposit operation for an account.
     * 
     * @param bank The instance of the CentralBank where the deposit operation will be performed.
     */
	private static void deposit(CentralBank bank) {
		Scanner sc1 = new Scanner(System.in);
		
		System.out.println("What is your account number?");
		int accountNumber = sc1.nextInt();
		
		Account a = bank.getAccountbyAccountID(accountNumber);
		
		System.out.println("How much would you like to deposit?");
		double depositAmount = sc1.nextDouble();
		
		if (bank.deposit(a, depositAmount)) {
			System.out.println("You have deposited "+depositAmount+" pounds");
		} else {
			System.out.println("Deposit of "+depositAmount+ " pounds failed");
		}
		
		
		
	}
	 /**
     * Handles the creation of a new account for a customer.
     * 
     * @param bank The instance of the CentralBank where the account will be created.
     */
	private static void handleCreateAccount(CentralBank bank) {
		
		 Scanner sc1 = new Scanner(System.in);

		    System.out.println("Please enter your customer ID to create an account:");
		    int customerId = sc1.nextInt();
		    sc1.nextLine();

		    Customer customer = bank.getCustomerByID(customerId);
		    if (customer == null) {
		        System.out.println("Customer not found.");
		        return;
		    }

		    System.out.println("Choose an account type: (1 for SAVINGS, 2 for CURRENT)");
		    int choice = sc1.nextInt();

		    AccountType accountType;
		    if (choice == 1) {
		        accountType = AccountType.SAVINGS;
		    } else {
		        accountType = AccountType.CURRENT;
		    }

		    if (bank.createAccount(customer, accountType)) {
		        System.out.println("Account created successfully!");
		    } else {
		        System.out.println("Failed to create an account.");
		    }
	}
	 /**
     * Handles the withdrawal operation for an account.
     * 
     * @param bank The instance of the CentralBank where the withdrawal operation will be performed.
     */
	private static void handleWithdraw(CentralBank bank) {
		 Scanner sc1 = new Scanner(System.in);

		    System.out.println("What is your account number?");
		    int accountNumber = sc1.nextInt();

		    Account a = bank.getAccountbyAccountID(accountNumber);
		    if (a == null) {
		        System.out.println("Account not found.");
		        return;
		    }

		    System.out.println("How much would you like to withdraw?");
		    double withdrawAmount = sc1.nextDouble();

		    if (bank.withdraw(a, withdrawAmount)) {
		        System.out.println("You have withdrawn " + withdrawAmount + " pounds.");
		    } else {
		        System.out.println("Withdrawal of " + withdrawAmount + " pounds failed.");
		    }
	}
	
	 /**
     * Handles the transfer of funds between two accounts.
     * 
     * @param bank The instance of the CentralBank where the transfer operation will be performed.
     */
	private static void handleTransfer(CentralBank bank) {
	    Scanner sc1 = new Scanner(System.in);

	    System.out.println("Enter your source account number:");
	    int fromAccountNumber = sc1.nextInt();

	    System.out.println("Enter the destination account number:");
	    int toAccountNumber = sc1.nextInt();

	    Account fromAccount = bank.getAccountbyAccountID(fromAccountNumber);
	    Account toAccount = bank.getAccountbyAccountID(toAccountNumber);

	    if (fromAccount == null || toAccount == null) {
	        System.out.println("One or both accounts not found.");
	        return;
	    }

	    System.out.println("How much would you like to transfer?");
	    double transferAmount = sc1.nextDouble();

	    if (bank.transfer(fromAccount, toAccount, transferAmount)) {
	        System.out.println("Transferred " + transferAmount + " pounds successfully.");
	    } else {
	        System.out.println("Transfer of " + transferAmount + " pounds failed.");
	    }

}
	
	/**
     * Displays the transaction history for a given customer.
     * 
     * @param bank The instance of the CentralBank from which transaction history will be retrieved.
     */
	private static void handleDisplayTransactionHistory(CentralBank bank) {
	    Scanner sc1 = new Scanner(System.in);

	    System.out.println("Enter your customer ID to view transaction history:");
	    int customerId = sc1.nextInt();

	    Customer customer = bank.getCustomerByID(customerId);
	    if (customer == null) {
	        System.out.println("Customer not found.");
	        return;
	    }

	    List<TransactionLog> logs = bank.getTransactionHistory(customer);
	    if (logs.isEmpty()) {
	        System.out.println("No transactions found.");
	    } else {
	        for (TransactionLog log : logs) {
	            System.out.println(log);  // Assuming the TransactionLog class has an appropriate toString() method
	        }
	    }
	}
	
	 /**
     * Handles the closing of an existing account.
     * 
     * @param bank The instance of the CentralBank where the account will be closed.
     */
	private static void handleCloseAccount(CentralBank bank) {
	    Scanner sc1 = new Scanner(System.in);

	    System.out.println("Enter your customer ID to close an account:");
	    int customerId = sc1.nextInt();
	    sc1.nextLine();  // consume newline

	    Customer customer = bank.getCustomerByID(customerId);
	    if (customer == null) {
	        System.out.println("Customer not found.");
	        return;
	    }

	    System.out.println("Enter the account number of the account you wish to close:");
	    int accountNumber = sc1.nextInt();
	    
	    Account accountToClose = bank.getAccountbyAccountID(accountNumber);
	    if (accountToClose == null) {
	        System.out.println("Account not found.");
	        return;
	    }

	    if (bank.closeAccount(customer, accountToClose)) {
	        System.out.println("Account closed successfully!");
	    } else {
	        System.out.println("Failed to close the account.");
	    }
	}
	
	/**
     * Retrieves and displays a summary for a given customer.
     * 
     * @param bank The instance of the CentralBank from which the customer summary will be retrieved.
     */
	private static void handleGetCustomerSummary(CentralBank bank) {
	    Scanner sc1 = new Scanner(System.in);

	    System.out.println("Enter your customer ID to get a summary:");
	    int customerId = sc1.nextInt();

	    Customer customer = bank.getCustomerByID(customerId);
	    if (customer == null) {
	        System.out.println("Customer not found.");
	        return;
	    }

	    String summary = bank.getCustomerSummary(customer);
	    System.out.println(summary);
	}
}
	 
		
	

