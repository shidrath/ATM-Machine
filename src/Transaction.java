import java.util.Date;

/**
 * @author Shidrath Rahman Haider
 */
public class Transaction {

	private double amount; // the amount of transaction
	private Date timestampDate; // the date and time of transaction
	private String memo; // A memo for this transaction
	private Account inAccount; // the account in which transaction was performed

	/**
	 * Create new Transaction
	 * 
	 * @param amount    the amount transacted
	 * @param inAccount the account the transaction belongs to
	 */
	public Transaction(double amount, Account inAccount) {
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestampDate = new Date();
		this.memo = "";
	}

	/**
	 * Create a new transaction
	 * 
	 * @param amount    the amount transacted
	 * @param memo      the memo for the transaction
	 * @param inAccount the account the transaction belongs to
	 */
	public Transaction(double amount, String memo, Account inAccount) {

		// call the two-arg constructor first
		this(amount, inAccount);

		// set the memo
		this.memo = memo;
	}
	/**
	 * Get the amount of transaction
	 * @return the amount
	 */
	public double getAmount() {
		// TODO Auto-generated method stub
		return this.amount;
	}

	
	/**
	 * Get a String summarizing the transaction
	 * @return  the summary string
	 */
	public String getSummaryLine() {
		
		if(this.amount >=0) {
			return String.format("%s: $%.02f : %s", this.timestampDate.toString(), this.amount, this.memo);
		}else {
			return String.format("%s: $(%.02f) : %s", this.timestampDate.toString(), this.amount, this.memo);
		}
	}
	
	

	
}
