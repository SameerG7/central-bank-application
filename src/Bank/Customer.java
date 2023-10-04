package Bank;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;


/**
 * Represents a customer in a banking system. 
 * A customer is identified by a unique customer ID, name, address, and date of birth.
 * Each customer can have multiple associated bank accounts.
 */
public class Customer {
    
    private static int customerIdCounter = 0;
    private int customerID;
    private String Name;
    private String Address;
    private Date DateOfBirth;
    private List<Account> Accounts;
    
    /**
     * Constructs a new Customer with the specified name, address, and date of birth.
     * Initializes the customer's accounts list and assigns a unique ID.
     * 
     * @param Name        The name of the customer.
     * @param Address     The address of the customer.
     * @param DateOfBirth The date of birth of the customer.
     */
    public Customer(String Name, String Address, Date DateOfBirth) {
        this.Name = Name;
        this.Address = Address;
        this.DateOfBirth = DateOfBirth;
        this.Accounts = new ArrayList<>();
        this.customerID = customerIdCounter++;
    }

    /**
     * Sets the name of the customer.
     * 
     * @param Name The new name for the customer.
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * Returns the name of the customer.
     * 
     * @return The name of the customer.
     */
    public String getName() {
        return this.Name;
    }

    /**
     * Sets the address of the customer.
     * 
     * @param Address The new address for the customer.
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * Returns the address of the customer.
     * 
     * @return The address of the customer.
     */
    public String getAddress() {
        return this.Address;
    }

    /**
     * Sets the date of birth of the customer.
     * 
     * @param DateOfBirth The new date of birth for the customer.
     */
    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    /**
     * Returns the date of birth of the customer.
     * 
     * @return The date of birth of the customer.
     */
    public Date getDateOfBirth() {
        return this.DateOfBirth;
    }

    /**
     * Returns the unique ID of the customer.
     * 
     * @return The customer ID.
     */
    public int getCustomerId() {
        return this.customerID;
    }

    /**
     * Returns the list of bank accounts associated with the customer.
     * 
     * @return A list of accounts.
     */
    public List<Account> getAccounts() {
        return this.Accounts;
    }

    /**
     * Provides a string representation of the customer, 
     * including their ID, name, address, date of birth, and associated accounts.
     * 
     * @return A string detailing the customer's information.
     */
    @Override
    public String toString() {
        String details = "";
        details += "Customer ID: " + getCustomerId() + "\n";
        details += "Name: "+ getName() + "\n";
        details += "Address: "+getAddress() + "\n";
        details += "Date of birth: "+getDateOfBirth() + "\n";
        details += "Accounts: "+getAccounts() + "\n";
        return details;
    }
}
