package Bank;



public abstract class BankAcoount  {
	float balance,interest;	
	String interest_type;
	public enum interest_type { SimpleInterest,CompondInterest } 
	LimitedQueue lq;	
	
	BankAcoount(){
		float f=0;
		
		lq=new LimitedQueue(5);
		for(int i=0;i<5;i++) {
			lq.add(f);
		}
	}		

	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	public String getInterest_type() {
		return interest_type;
	}
	public void setInterest_type(String interest_type) {
		this.interest_type = interest_type;
	}
	
	public LimitedQueue getLast_transcation() {
		return lq;
	}
	public void setLast_transcation(float last_transcation) {
		lq.add(last_transcation);
	}
}
