package Bank;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

/**
 * Test class for the CentralBankApp application. It contains a series of unit tests
 * designed to validate the behavior of the main functionalities provided by the CentralBank class.
 * 
 * <p>The following operations are tested:</p>
 * <ul>
 *     <li>Adding a customer</li>
 *     <li>Removing a customer</li>
 *     <li>Deposit</li>
 *     <li>Withdraw</li>
 *     <li>Transfer between accounts</li>
 * </ul>
 */
public class CentralBankAppTest {

    private CentralBank centralBank;
    private Customer customer;
    private Account account;
    private Date dob;  // Date of birth

    /**
     * Set up common test data and configurations. This method is executed before each test.
     * It initializes a CentralBank instance, a Customer instance with a predefined 
     * address and date of birth, and other necessary setups for the tests.
     */
    @BeforeEach
    public void setUp() {
        centralBank = new CentralBank();

        // Initialize the Date of birth
        dob = new Date();  // for simplicity, set current date as DOB

        // Created a Customer object using the provided constructor.
        customer = new Customer("John Doe", "123 Main St", dob);
    }

    /**
     * Test the behavior of adding customers to the CentralBank. 
     * Ensures that a customer can be added once and not twice.
     */
    @Test
    public void testAddCustomer() {
        assertTrue(centralBank.addCustomer(customer));
        assertFalse(centralBank.addCustomer(customer)); // Should be false since the customer is already added
    }

    /**
     * Test the behavior of removing customers from the CentralBank.
     * Ensures that a customer can be removed once and not twice.
     */
    @Test
    public void testRemoveCustomer() {
        centralBank.addCustomer(customer);
        assertTrue(centralBank.removeCustomer(customer));
        assertFalse(centralBank.removeCustomer(customer)); // Should be false since the customer is already removed
    }

    /**
     * Test the deposit operation of the CentralBank.
     * Validates successful deposits and ensures negative deposits are disallowed.
     */
    @Test
    public void testDeposit() {
        centralBank.addCustomer(customer);
        centralBank.createAccount(customer, AccountType.SAVINGS);  
        account = centralBank.getAccountbyAccountID(1);  
        assertTrue(centralBank.deposit(account, 500));
        assertFalse(centralBank.deposit(account, -500)); // Negative deposit should fail
    }

    /**
     * Test the withdraw operation of the CentralBank.
     * Validates that withdrawing an amount greater than the available balance fails.
     */
    @Test
    public void testWithdraw() {
        centralBank.addCustomer(customer);
        centralBank.createAccount(customer, AccountType.SAVINGS);
        account = centralBank.getAccountbyAccountID(1);
        assertFalse(centralBank.withdraw(account, 600)); // Assuming initial balance is less than 600
    }

    /**
     * Test the transfer operation between two accounts in the CentralBank.
     * Validates successful transfers and ensures transfers of amounts greater than the available balance fail.
     */
    @Test
    public void testTransfer() {
        Date dob2 = new Date();
        Customer customer2 = new Customer("Jane Doe", "456 Elm St", dob2); // Another customer
        centralBank.addCustomer(customer);
        centralBank.addCustomer(customer2);
        centralBank.createAccount(customer, AccountType.SAVINGS);
        centralBank.createAccount(customer2, AccountType.SAVINGS);
        Account fromAccount = centralBank.getAccountbyAccountID(1);
        Account toAccount = centralBank.getAccountbyAccountID(2);
        centralBank.deposit(fromAccount, 1000); // Deposit to the account to have enough balance
        assertTrue(centralBank.transfer(fromAccount, toAccount, 500));
        assertFalse(centralBank.transfer(fromAccount, toAccount, 1500)); // Balance after previous transaction is 500
    }

    
}
