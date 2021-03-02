package Bank;

public class InsufficientFundsException extends Exception{
	InsufficientFundsException(String s){
		super(s);  
	}
}
