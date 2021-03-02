package Bank;

public class BankApplication extends UserDetails{	
	
	public static void main(String[] args)  {
		Accounts account =new Accounts();
		account.process();		
	}
}