package Bank;

import java.util.List;

/**
 * Interface for retrieving customer and transaction details.
 */
public interface Searchable {
   
    /** Returns a summary for the given customer. */
   String getCustomerSummary(Customer customer);

   /** Fetches transaction history for the given customer. */
   List<TransactionLog> getTransactionHistory(Customer customer);
}

