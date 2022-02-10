import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author Shidrath Rahman Haider
 **/
public class User {

	private String firstName; // the first name of the user
	private String lastName; // the last name of the user
	private String uuid; // the Id number of the user
	private byte pinHash[]; // the MD5 hash of the user's pin number
	private ArrayList<Account> accounts; // the list of accounts for this user

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @param theBank
	 */

	public User(String firstName, String lastName, String pin, Bank theBank) {
		// set the user's name
		this.firstName = firstName;
		this.lastName = lastName;

		// store the pin's MD5 hash, rather than the original value, for
		// security reasons
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO print out an error
			System.err.println("error,caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}

		// get a new, unique universal ID for the user
		this.uuid = theBank.getNewUserUUID();

		// create empty list of accounts
		this.accounts = new ArrayList<Account>();

		// print log message
		System.out.printf("new user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);
	}

	/**
	 * Add an account for the user
	 * 
	 * @param anAcct the account to add
	 */

	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}

	/**
	 * Return the user's UUid
	 * 
	 * @return the uuid
	 */
	public String getUUID() {
		return this.uuid;
	}

	/**
	 * Check whether a given pin matches the true User pin
	 * 
	 * @param aPin the pin to check
	 * @return whether the pin is valid or not
	 */
	public boolean validatePin(String aPin) {

		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(mDigest.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println("error,caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}

		return false;
	}
	/**
	 * Return the user's first name
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	public void printAccountsSummary() {
		System.out.printf("\n\n %s's account summary\n",  this.firstName);
		for( int a =0; a<this.accounts.size(); a++) {
			System.out.printf("  %d) %s \n", a+1,
					this.accounts.get(a).getSummaryLine());
		} 
		System.out.println();
	}
	/**
	 * Get the number of accounts of the user
	 * @return    the number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}
	/**
	 * print transaction history for a particular account
	 * @param acctIdx the index of the account to be used
	 */
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}
	/**
	 * Get the balance of a particular account
	 * @param acctIdx  the index of the account to use
	 * @return         the balance of the account
	 */
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/**
	 * Get the UUID of a particular account
	 * @param acctIdx  the index of the account to use
	 * @return         the UUID of the account
	 */
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	
	
	/**
	 * Add a transaction to a particular account
	 * @param acctIdx  the index of the account
	 * @param amount   the amount of the transaction
	 * @param memo     the memo of the transaction
	 */
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
	
}
