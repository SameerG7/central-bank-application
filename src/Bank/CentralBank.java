/**
 * 
 */
package Bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a central bank that manages customers and their respective accounts.
 * Implements the functionalities provided by Transactable and Searchable interfaces.
 */
public class CentralBank implements Transactable, Searchable {

	 /** List of customers registered with the central bank. */
    private ArrayList<Customer> Customers;
    
    /** List of accounts associated with the central bank. */
    private ArrayList<Account> Accounts;
    
    /**
     * Initializes an instance of the CentralBank with empty lists of customers and accounts.
     */
    public CentralBank () {
    	
        this.Customers = new ArrayList<>();
        this.Accounts = new ArrayList<>();
    }
    
    /**
     * Adds a new customer to the bank.
     *
     * @param customer The customer to be added.
     * @return true if customer is added successfully, false otherwise (e.g., customer already exists).
     */
    public boolean addCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }
        for (Customer existingCustomer : Customers) {
            if (existingCustomer.getCustomerId() == customer.getCustomerId()) {
                return false;
            }
        }
        Customers.add(customer);
        return true;
    }

    /**
     * Removes an existing customer from the bank.
     *
     * @param customer The customer to be removed.
     * @return true if customer is removed successfully, false otherwise.
     */
    public boolean removeCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }
        Customer removingCustomer = null;
        for(Customer doesExist: Customers) {
            if(doesExist.getCustomerId() == customer.getCustomerId()) {
                removingCustomer = doesExist;
                break;
            }
        }
        if (removingCustomer != null) {
            Customers.remove(removingCustomer);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves a customer from the bank using their ID.
     *
     * @param customerID The ID of the customer.
     * @return Customer if found, null otherwise.
     */
    public Customer getCustomerByID(int customerID) {
        for (Customer c: Customers) {
            if(c.getCustomerId() == customerID) {
                return c;
            }
        }
        return null;
    }

    /**
     * Deposits a specified amount into a given account.
     *
     * @param account The account to deposit to.
     * @param amount The amount to be deposited.
     * @return true if deposit is successful, false otherwise.
     */
    public boolean deposit(Account account, double amount) {
        if (account == null || amount <= 0) {
            return false;
        }
        for(Account existingAccount: Accounts) {
            if(existingAccount.getAccountNumber() == account.getAccountNumber()) {
                existingAccount.deposit(amount);
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves an account based on its account number.
     *
     * @param accountNumber The account number.
     * @return Account if found, null otherwise.
     */
    public Account getAccountbyAccountID(int accountNumber) {
        for(Account a: Accounts) {
            if(a.getAccountNumber() == accountNumber) {
                return a;
            }
        }
        return null;
    }

    /**
     * Withdraws a specified amount from a given account.
     *
     * @param account The account to withdraw from.
     * @param amount The amount to be withdrawn.
     * @return true if withdrawal is successful, false otherwise.
     */
    public boolean withdraw(Account account, double amount) {
        if(account == null || amount <= 0) {
            return false;
        }
        for(Account existingAccount: Accounts) {
            if(existingAccount.getAccountNumber() == account.getAccountNumber()) {
                existingAccount.withdraw(amount);
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new account for a given customer and account type.
     *
     * @param customer The customer for whom the account should be created.
     * @param accountType The type of account to be created.
     * @return true if account creation is successful, false otherwise.
     */
    public boolean createAccount(Customer customer, AccountType accountType) {
        if(customer == null || accountType == null) {
            return false;
        } else {
            Account newAccount = new Account(customer, accountType);
            customer.getAccounts().add(newAccount);
            Accounts.add(newAccount);
            return true;
        }
    }

    /**
     * Closes an account associated with a given customer.
     *
     * @param customer The customer who owns the account.
     * @param account The account to be closed.
     * @return true if account closure is successful, false otherwise.
     */
    public boolean closeAccount (Customer customer, Account account) {
        if(customer == null || account == null) {
            return false;
        }
        boolean customerExists = Customers.stream().anyMatch(existingCustomer -> existingCustomer.getCustomerId() == customer.getCustomerId());
        if(customerExists) {
            Accounts.remove(account);
            customer.getAccounts().remove(account);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Transfers a specified amount from one account to another.
     *
     * @param fromAccount The account to transfer from.
     * @param toAccount The account to transfer to.
     * @param amount The amount to be transferred.
     * @return true if transfer is successful, false otherwise.
     */
    public boolean transfer (Account fromAccount, Account toAccount, double amount) {
        if(fromAccount == null || toAccount == null || amount <= 0) {
            return false;
        }
        Account actualFromAccount = null;
        Account actualToAccount = null;
        for(Account doesItExist: Accounts) {
            if(doesItExist.getAccountNumber() == fromAccount.getAccountNumber()) {
                actualFromAccount = doesItExist;
            }
            if(doesItExist.getAccountNumber() == toAccount.getAccountNumber()) {
                actualToAccount = doesItExist;
            }
            if(actualFromAccount != null && actualToAccount != null) {
                break;
            }
        }
        if(actualFromAccount == null || actualToAccount == null) {
            return false;
        }
        if(actualFromAccount.getBalance() < amount) {
            return false;
        } else {
            double balanceFrom = actualFromAccount.getBalance();
            double balanceTo = actualToAccount.getBalance();
            balanceFrom -= amount;
            actualFromAccount.setBalance(balanceFrom);
            balanceTo += amount;
            actualToAccount.setBalance(balanceTo);
            return true;
        }
    }

    /**
     * Retrieves the transaction history for a given customer across all their accounts.
     *
     * @param customer The customer whose transaction history should be retrieved.
     * @return A list of transaction logs for the customer.
     */
    public List<TransactionLog> getTransactionHistory(Customer customer) {
        if(customer == null) {
            return null;
        }
        ArrayList<TransactionLog> customerTransactionHistory = new ArrayList<>();
        for(Account findingCustomerAccount: customer.getAccounts()) {
            List<TransactionLog> accountTransactionHistory = findingCustomerAccount.getTransactionHistory();
            customerTransactionHistory.addAll(accountTransactionHistory);
        }
        return customerTransactionHistory;
    }

    /**
     * Generates a summary for a given customer including their details and account information.
     *
     * @param customer The customer whose summary should be generated.
     * @return A formatted string representation of the customer's details and accounts.
     */
    public String getCustomerSummary(Customer customer) {
        if(customer == null) {
            return null;
        }
        String summary = "Customer Summary for " + customer.getName() + "\n" + customer.getAddress() + "\n";
        List<Account> accounts = customer.getAccounts();
        for(Account account: accounts) {
            summary += "Account No: " + account.getAccountNumber() + "\n" +
                       "Account type: " + account.getAccountType() + "\n" + "Balance: " + account.getBalance() + "\n";
        }
        summary += "Total Accounts: " + accounts.size();
        return summary;
    }
}


	  
	  


