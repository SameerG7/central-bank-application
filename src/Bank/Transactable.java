package Bank;
/**
 * Interface for financial transactions.
 */
public interface Transactable {
   
    /** Deposits amount into an account. */
   boolean deposit(Account account, double amount);

   /** Withdraws amount from an account. */
   boolean withdraw(Account account, double amount);

   /** Transfers amount between accounts. */
   boolean transfer(Account fromAccount, Account toAccount, double amount);
}

