package Bank;

public class NonExistingBankAccountException extends Exception{

	public NonExistingBankAccountException(String s) {
		super(s);
	}
}
