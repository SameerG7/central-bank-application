package Bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account with features like deposit, withdraw, and transaction history.
 */
public class Account {
	
    /**
     * A static counter to generate unique account numbers.
     */
	private static int AccountNumberCounter = 0;

    /**
     * The unique account number for this account.
     */
	private int AccountNumber;

    /**
     * The owner of the account.
     */
	private Customer Owner;

    /**
     * The current balance of the account.
     */
	private double Balance;

    /**
     * The type of the account (e.g., SAVINGS, CURRENT).
     */
	private AccountType type;

    /**
     * A list of transaction logs associated with this account.
     */
	private List<TransactionLog> transactionHistory;
	
    /**
     * Constructs an Account with a specified owner and type.
     * Initializes balance to 0 and increments account number counter.
     * 
     * @param Owner The customer who owns this account.
     * @param type The type of this account.
     */
	public Account (Customer Owner, AccountType type) {
		this.AccountNumber = AccountNumberCounter++;
		this.Owner = Owner;
		this.Balance = 0;
		this.type = type;
		this.transactionHistory = new ArrayList<>();
	}

    /**
     * Returns the account number of this account.
     * 
     * @return The account number.
     */
	public int getAccountNumber () {
		return this.AccountNumber;
	}

    /**
     * Sets the balance for this account.
     * 
     * @param balance The new balance for the account.
     */
	public void setBalance(double balance) {
		this.Balance = balance;
	}

    /**
     * Returns the balance of this account.
     * 
     * @return The balance.
     */
	public double getBalance () {
		return this.Balance;
	}

    /**
     * Returns the type of this account.
     * 
     * @return The account type.
     */
	public AccountType getAccountType () {
		return this.type;
	}

    /**
     * Returns the owner of this account.
     * 
     * @return The account owner.
     */
	public Customer getOwner () {
		return this.Owner;
	}

    /**
     * Returns the address of the account owner.
     * 
     * @return The owner's address.
     */
	public String getAddress () {
		return getOwner().getAddress();
	}

    /**
     * Attempts to deposit a specified amount to the account. 
     * Adds a transaction log if successful.
     * 
     * @param deposit The amount to deposit.
     * @return true if deposit was successful, false otherwise.
     */
	public boolean deposit(double deposit) {
        if (deposit <= 0) {
            return false;
        }
        Balance += deposit;
        transactionHistory.add(new TransactionLog(this, deposit, TransactionType.DEPOSIT));
        return true;
    }

    /**
     * Attempts to withdraw a specified amount from the account. 
     * Adds a transaction log if successful.
     * 
     * @param withdrawAmount The amount to withdraw.
     * @return true if withdrawal was successful, false otherwise.
     */
	public boolean withdraw(double withdrawAmount) {
        if (withdrawAmount < 0 || this.Balance < withdrawAmount) {
            return false;
        }
        Balance -= withdrawAmount;
        transactionHistory.add(new TransactionLog(this, withdrawAmount, TransactionType.WITHDRAW));
        return true;
    }

    /**
     * Returns the transaction history of this account.
     * 
     * @return A list of transaction logs.
     */
	public List<TransactionLog> getTransactionHistory() {
		return this.transactionHistory;
	}

    /**
     * Returns a string representation of this account's details.
     * 
     * @return Account details as a string.
     */
	public String toString () {
		String info = "";
		info += "Account details: " + "\n";
		info += "Account No: " + getAccountNumber()+ "\n";
		info += "Name: " + getOwner().getName()+ "\n";
		info += "Address: " + getAddress() + "\n";
		info += "Balance: " + getBalance ()+ "\n";
		info += "Account Type: " + getAccountType()+ "\n";
		return info;
	}
}
