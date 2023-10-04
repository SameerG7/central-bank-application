package Bank;

/**
 * Enumerates the different types of financial transactions that can occur in the banking system.
 * <p>
 * Currently supported transaction types include:
 * <ul>
 *     <li>{@link #DEPOSIT}</li>
 *     <li>{@link #WITHDRAW}</li>
 * </ul>
 * </p>
 */
public enum TransactionType {

    /**
     * Represents a transaction where money is added to an account.
     */
    DEPOSIT,

    /**
     * Represents a transaction where money is taken out of an account.
     */
    WITHDRAW
}
