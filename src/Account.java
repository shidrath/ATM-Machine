import java.util.ArrayList;

/**
 * @author Shidrath Rahman Haider
 **/
public class Account {

	private String name; // the name of the account

	private String uuid; // the account ID number

	private User holder; // the User object that owns this account

	private ArrayList<Transaction> transactions; // the list of transactions for this account

	/**
	 * create a new account
	 * 
	 * @param name
	 * @param holder
	 * @param theBank
	 */
	public Account(String name, User holder, Bank theBank) {

		// set the account name and holder
		this.name = name;
		this.holder = holder;

		// get new account UUID
		this.uuid = theBank.getNewAccountUUID();

		// init transactions
		this.transactions = new ArrayList<Transaction>();

	}

	/**
	 * Get the account ID
	 * 
	 * @return the uuid
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	public String getSummaryLine() {
		
		//get the account's balance
		double balance = this.getBalance();
		
		// format the summary line, depending on whether the balance is negative
		if(balance >=0) {
			return String.format("%s: $%.02f: %s", this.uuid, balance, this.name);
		}else {
			return String.format("%s : $(%.02f) : %s", this.uuid,balance,this.name);
		}
	}
	/**
	 * get the balance of this account by adding the amount of the transaction
	 * @return the balance value
	 */
	public double getBalance() {
		
		double balance =0;
		for(Transaction t: this.transactions) {
			balance +=  t.getAmount();
			
		}
		return balance;
	}
	/**
	 * print the transaction history of the account
	 */
	public void printTransHistory() {
		
		System.out.printf("\nTransaction history for account %s \n", this.uuid);
		for( int t= this.transactions.size()-1; t>=0; t--) {
			System.out.printf(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}
	/**
	 * Add a new Transaction in this account
	 * @param amount     the amount transacted
	 * @param memo       the transaction memo
	 */
	public void addTransaction(double amount, String memo) {
		
		
		//create a new transaction object
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
	}
}


