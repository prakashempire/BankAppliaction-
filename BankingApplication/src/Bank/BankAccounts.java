package Bank;

public abstract class BankAccounts{
	float balance;	
	LimitedQueue limited_queue;	
	InterestType.interest_type interest_typeVal = null;
	float fixed_deposit=0;
	int interest_year=0;
	BankAccounts(){
		float f=0;
		limited_queue=new LimitedQueue(5);
		for(int i=0;i<5;i++) {
			limited_queue.add(f);
		}
	}		

	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public LimitedQueue getLast_transcation() {
		return limited_queue;
	}
	public void setLast_transcation(float last_transcation) {
		limited_queue.add(last_transcation);
	}

	public InterestType.interest_type getInterest_typeVal() {
		return interest_typeVal;
	}

	public void setInterest_typeVal(InterestType.interest_type interest_typeVal) {
		this.interest_typeVal = interest_typeVal;
	}
	public float getFixed_deposit() {
		return fixed_deposit;
	}

	public void setFixed_deposit(float fixed_deposit) {
		this.fixed_deposit = fixed_deposit;
	}

	public int getInterest_year() {
		return interest_year;
	}

	public void setInterest_year(int interest_year) {
		this.interest_year = interest_year;
	}
	
}
