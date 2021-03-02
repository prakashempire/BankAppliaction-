package Bank;

public class CustumMessage {
	void getChoiceMessage() {
		System.out.println("Welcome back, ");
		System.out.println("-------------------------------------");
		System.out.println("1-> Create Bank Account (press 1)");
		System.out.println("2-> Deposit to Account (press 2)");
		System.out.println("3-> Withdraw from Account (press 3)");		
		System.out.println("4-> Last 5 transactions (press 4)");
		System.out.println("5-> Display Account (press 5)");
		System.out.println("6-> Interest Calculate (press 6)");
		System.out.println("7-> Settings (press 7)");
		System.out.println("8-> Available BankAccounts (press 8)");
		System.out.println("9-> Transfer Amount (press 9)");
		System.out.println("10-> Exit Bank (press 10)");
		System.out.println("*************************************");
	}
	void getSettingMessege() {
		System.out.println("Settings");
		System.out.println("1-> ChangePassword (press 1)");
		System.out.println("2-> Exit (press 2)");
		System.out.println("-------------------------------------");
	}
	void getInterestMessege() {
		System.out.println("Welcome ,");
		System.out.println("1-> Simple Interest (SI) (press 1)");
		System.out.println("2-> Compound Interest (CI) (press 2)");
		System.out.println("3-> Exit (press 3)");
	}
}