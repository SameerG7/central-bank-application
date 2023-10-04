package Bank;

/**
 * Represents a record of a single transaction made on an account.
 * Each transaction log captures important details such as the amount involved, the type of transaction,
 * and the account on which the transaction was made.
 */
public class TransactionLog {
    
    private static int idCount = 0;
    private int transactionId;
    private Account Account; 
    private double Amount;
    private TransactionType type;
    
    /**
     * Constructs a new TransactionLog with the given account, amount, and type of transaction.
     *
     * @param Account The account associated with this transaction.
     * @param Amount The amount involved in this transaction.
     * @param type The nature of the transaction (e.g., DEPOSIT, WITHDRAW).
     */
    public TransactionLog(Account Account, double Amount, TransactionType type) {
        this.transactionId = idCount++;
        this.Account = Account;
        this.Amount = Amount;
        this.type = type;
    }
    
    /**
     * Retrieves the amount involved in the transaction.
     *
     * @return The transaction amount.
     */
    public double getAmount() {
        return this.Amount;
    }
    
    /**
     * Retrieves the type of the transaction.
     *
     * @return The transaction type.
     */
    public TransactionType getTransactionType() {
        return this.type;
    }

    /**
     * Retrieves the unique identifier for this transaction.
     *
     * @return The transaction ID.
     */
    public int getTransactionId() {
        return this.transactionId;
    }

    /**
     * Retrieves the account number associated with this transaction.
     *
     * @return The account number.
     */
    public int getAccountNumber() {
        return this.Account.getAccountNumber();
    }

    /**
     * Retrieves the name of the owner of the account associated with this transaction.
     *
     * @return The name of the account owner.
     */
    public String getName() {
        return this.Account.getOwner().getName();
    }

    /**
     * Provides a textual representation of this transaction, suitable for reporting purposes.
     *
     * @return A string summary of this transaction.
     */
    @Override
    public String toString() {
        String info = "";
        info += "Generating report..." + "\n";
        info += "Transaction Id: " + getTransactionId() + "\n";
        info += "Name: " + getName() + "\n";
        info += "Account Number: " + getAccountNumber() + "\n";
        info += "Amount: " + getAmount() + "\n";
        info += "Transaction type: " + getTransactionType() + "\n";
        info += "Report complete." + "\n";
        return info;
    }
}
