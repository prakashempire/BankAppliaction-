package Bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Accounts {
	static Map<Integer,UserDetails> details=new HashMap<>();
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int ACCOUNTNO=1001;
	
	void process() {	
		//Welcome message
		new CustumMessage().getChoiceMessage();

		int select_option;
		while(true) {
			
			while(true) {
				try {
					System.out.println("select,,,");
					select_option=Integer.parseInt(br.readLine());
					break;
				}catch(NumberFormatException | IOException  exception) {
					System.out.println(exception);
				}
			}
			
			switch(select_option) {
				case 1:{	
					create();					
					ACCOUNTNO++;
					break;
				}
				case 2:{					
					try {
						deposite();
					} catch (NonExistingBankAccountException exception) {						
						System.out.println(exception);
					}
					break;
				}
				case 3:{
					try {
						withdraw();
					} catch (NonExistingBankAccountException exception) {
						System.out.println(exception);
					} catch (InsufficientFundsException exception) {
						System.out.println(exception);
					}				
					break;
				}
				case 4:{
					try {
						lastTranscation();
					} catch (NonExistingBankAccountException exception) {
						System.out.println(exception);
					}
					break;
				}
				case 5:
					try {
						display();
					} catch (NonExistingBankAccountException exception) {
						System.out.println(exception);
					}					
					break;
				case 6:
					InterestCalculate();					
					break;
				case 7:
					try {
						settings();
					} catch (NonExistingBankAccountException exception) {
						System.out.println(exception);
					}					
					break;
				case 8:
					availableAccounts();				
					break;
					
				case 9:
					TransferAmount tranfer_amount=new TransferAmount();
					try {
						tranfer_amount.transferAmount(details);
					} catch (NonExistingBankAccountException exception) {
						System.out.println(exception);
					}
					break;
				case 10:{
					System.out.println("Thanks for Using this Bank!");
					return;
				}
				default:{
					System.out.println("Enter valid option!!!!");
				}
			}
			new CustumMessage().getChoiceMessage();			
		}
	}
	private void availableAccounts() {
		int count=1;
		System.out.println("Available Accounts: ");
		System.out.println("-------------------------------------");
		if(details.size()==0) {
			System.out.println("Accounts not available");
			System.out.println("*************************************");
			return;
		}
		for(Map.Entry<Integer,UserDetails> itr : details.entrySet()) {
			UserDetails user_details=itr.getValue();
			int acc_no=user_details.getAccount_num();
			String name=user_details.getFirst_name()+" "+user_details.getLast_name();
			int age=user_details.getAge();
			System.out.println(count+"-> Account Number: "+acc_no+" \nName: "+name+" \nAge: "+age);
			count++;
			System.out.println("-------------------------------------");
		}
		System.out.println("*************************************");
	}
	private void InterestCalculate() {
		final double RATE=0.5;	
		int acc_no=0,type=0,year=0;
		int amount=0;
		//enum interest_type {SimpleInterest , CompoundInterest}
		while(true) {
			System.out.println("Enter Account Number: ");
			try {
				acc_no=Integer.parseInt(br.readLine());
				break;
			}
			catch(Exception exception) {
				System.out.println(exception);
			}
		}
		
		new CustumMessage().getInterestMessege();
		UserDetails user_details=details.get(acc_no);
		String SimpleInterest="SimpleInterest",CompoundInterest="CompoundInterest";
		System.out.println("select type...");
		while(true) {
			try {
				type=Integer.parseInt(br.readLine());
				if(type==1) {
					user_details.setInterest_typeVal(InterestType.interest_type.SimpleInterest);
					while(true) {
						try {
							System.out.println("Enter Amount: ");
							amount=Integer.parseInt(br.readLine());
							System.out.println("Year: ");
							year=Integer.parseInt(br.readLine());
							float balance=user_details.getBalance();
							float remaining_balance=balance-amount;
							if(remaining_balance>=500) {
								user_details.setBalance(remaining_balance);
								float simple_interest_amount=simpleInterestCalculation(amount,year,RATE);
								user_details.setFixed_deposit(amount+simple_interest_amount);
								user_details.setInterest_year(year);
								user_details.setLast_transcation(amount);
								System.out.println("successfull....");
								System.out.println("Current account balance: "+user_details.getBalance());
								System.out.println("Fixed deposite amount for "+year+" year: "+user_details.getFixed_deposit());
								System.out.println("successfull....");
								break;
							}else {
								System.out.println("Insufficient amount!");
								System.out.println("* Maintain minimum 500 INR!");
								System.out.println("* try less amount!");

							}
						}catch(Exception exception) {
							System.out.println(exception);
						}
					}
						
					
				}else if(type==2) {
					while(true) {
						try {
							System.out.println("Enter Amount: ");
							amount=Integer.parseInt(br.readLine());
							System.out.println("Year: ");
							year=Integer.parseInt(br.readLine());
							float balance=user_details.getBalance();
							float remaining_balance=balance-amount;
							if(remaining_balance>=500) {
								user_details.setBalance(remaining_balance);
								float compound_interest_amount=compoundInterestCalculation(amount,year,RATE);
								user_details.setFixed_deposit(amount+compound_interest_amount);
								user_details.setInterest_year(year);
								user_details.setLast_transcation(amount);
								System.out.println("successfull....");
								System.out.println("Current account balance: "+user_details.getBalance());
								System.out.println("Fixed deposite amount for "+year+" year: "+user_details.getFixed_deposit());
								System.out.println("successfull....");
								break;
							}else {
								System.out.println("Insufficient amount!");
								System.out.println("* Maintain minimum 500 INR!");
								System.out.println("* try less amount!");

							}
						}catch(Exception exception) {
							System.out.println(exception);
						}
					}		

				}else if(type==3){
					System.out.println("Thanks for using!");
					return ;
				}else {
					System.out.println("Enter valid input!");
					continue;
				}
				break;
			}catch(Exception exception ) {
				System.out.println(exception);
			}
		}
	}
	private void settings() throws NonExistingBankAccountException {
		int acc_no=0;
		String user_password=null;
			
		while(true) {
			System.out.println("Enter your Account No: ");	
			try {
				acc_no = Integer.parseInt(br.readLine());
				break;
			} catch (NumberFormatException | IOException exception) {
				System.out.println(exception);
			}
		}
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		UserDetails user_details=details.get(acc_no);
		String get_user_password=user_details.getPassword();
		while(true) {			
			System.out.println("Enter user password");			
			try {
				user_password=br.readLine();
				if(!get_user_password.equals(user_password)) {
					System.out.println("Password wrong!");
				}else {
					System.out.println("login successful.\n");
					new CustumMessage().getSettingMessege();
					int select=0;
					while(true) {
						
						try{
							select=Integer.parseInt(br.readLine());
							break;
						}catch(Exception exception) {
							System.out.println();
						}						
					}
					switch(select) {
						case 1:{
							System.out.println("Enter Mobile number to chane your password: ");
							while(true) {
								try {
									String mobile_no=br.readLine();
									String get_mobile_no=user_details.getMobile_no();
									if(mobile_no.equals(get_mobile_no)) {
										while(true) {
											System.out.println("Enter new password:");
											String new_password=br.readLine();
											if(isValidPassword(new_password)) {
												user_details.setPassword(new_password);
												System.out.println("Password reset successful");
												break;
											}else {
												System.out.println("Enter Valid password!");
												System.out.println("Password should contains\n* One Captital and Small letter\n* One numeric value\n* Minimum length more than 3");

											}
										}										
										break;
									}else {
										System.out.println("Enter your correct old mobile number");
									}
									
								}
								catch(Exception exception) {
									System.out.println();
								}
							}						
							
						}
						case 2:{
							System.out.println("Thanks for Using!");
							return ;
						}
						default:{
							System.out.println("Enter valid choice!");
							settings();
						}
					}					
					break;
				}
			}catch(Exception exception) {
				System.out.println(exception);
			}
		}
	}
	private static void display() throws NonExistingBankAccountException{
		int acc_no=0;
		String user_password=null;
			
		while(true) {
			System.out.println("Enter your Account No: ");	
			try {
				acc_no = Integer.parseInt(br.readLine());
				break;
			} catch (NumberFormatException | IOException exception) {
				System.out.println(exception);
			}
		}
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		UserDetails user_details=details.get(acc_no);
		String get_user_password=user_details.getPassword();
		while(true) {
			
			System.out.println("Enter user password");			
			try {
				user_password=br.readLine();
				if(!get_user_password.equals(user_password)) {
					System.out.println("Password wrong!");
				}else {
					System.out.println("login successful.\n");
					user_details=details.get(acc_no);
					System.out.println("Name: " +user_details.getFirst_name()+" "+user_details.getLast_name()+" Age: "+user_details.getAge());
					System.out.println("Mobile Number: "+user_details.getMobile_no());
					System.out.println("--------------------------------------");
					System.out.println("Account No: "+user_details.getAccount_num());
					System.out.println("Password: "+user_details.getPassword());
					System.out.println("Balance: "+user_details.getBalance());
					System.out.println("Interest type: "+user_details.getInterest_typeVal());
					System.out.println("Fixed amount: "+user_details.getFixed_deposit()+"  Interest Period: "+user_details.getInterest_year());
					break;
				}
			}catch(Exception exception) {
				System.out.println(exception);
			}
		}
		System.out.println("*************************************");
	}
	private static void lastTranscation() throws NonExistingBankAccountException {
		int acc_no=0;
		String user_password=null;
		System.out.println("Enter your Accout no:");
		
		try {
			acc_no = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException exception) {
			System.out.println(exception);
		}
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		UserDetails user_details=details.get(acc_no);
		String get_user_password=user_details.getPassword();
		while(true) {
			
			System.out.println("Enter user password");			
			try {
				user_password=br.readLine();
				if(!get_user_password.equals(user_password)) {
					System.out.println("Password wrong!");
				}else {
					System.out.println("login successful.");
					System.out.println();
					System.out.println("welcome back, "+user_details.getFirst_name()+" "+user_details.getLast_name());
					break;
				}
			}catch(Exception exception) {
				System.out.println(exception);
			}
		}
		int serial_no=1;
		LimitedQueue last_transcations=user_details.getLast_transcation();
		System.out.println("Current Acoount balance: "+user_details.getBalance());
		System.out.println("-----------------------------------");
		System.out.println("Last 5 Transcation are:\n");
		for(int i=last_transcations.size()-1;i>=0;i--) {
			float last_transcation_value=(float) last_transcations.get(i);
			if(last_transcation_value>0) {
				System.out.println(serial_no+" Deposited: "+last_transcation_value);
			}else if(last_transcation_value<0) {
				System.out.println(serial_no+ " Withdrawd: "+last_transcation_value);
			}
			serial_no++;
		}
		System.out.println("*************************************");
	}
	private static void withdraw() throws NonExistingBankAccountException, InsufficientFundsException {
		final int MINIMUM_AMOUNT=500,MINIMUM_WITHDRAW_AMOUNT=100;
		int acc_no=0;
		float amount=0;
		String user_password=null;
		while(true) {
			try{
				System.out.println("Enter Your Account No:");
				acc_no=Integer.parseInt(br.readLine());
				break;
			}catch(Exception exception) {
				throw new NonExistingBankAccountException("Enter Proper Account Number!");
			}
		}
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		UserDetails user_details=details.get(acc_no);
		String get_user_password=user_details.getPassword();
		while(true) {
			
			System.out.println("Enter user password");			
			try {
				user_password=br.readLine();
				if(!get_user_password.equals(user_password)) {
					System.out.println("Password wrong!");
				}else {
					System.out.println("login successful.");
					System.out.println();
					System.out.println("welcome back, "+user_details.getFirst_name()+" "+user_details.getLast_name());
					while(true) {
						System.out.println("Enter withdraw amount: ");
						try {
							amount = Float.parseFloat(br.readLine());
							break;
						} catch (NumberFormatException | IOException exception) {
							System.out.println(exception);
						}
					}
					float balance=user_details.getBalance()-amount;		
					if(balance>=MINIMUM_AMOUNT && amount >=MINIMUM_WITHDRAW_AMOUNT) {
						user_details.setBalance(balance);
						System.out.println("Withdrawd successfull.");
						System.out.println("Current Account Balance: "+user_details.getBalance());
						user_details.setLast_transcation(-amount);
						break;
					}
					else if(amount<MINIMUM_WITHDRAW_AMOUNT) {
						System.out.println("Minimum Withdrwal amount should be "+MINIMUM_WITHDRAW_AMOUNT+" INR!");
					}
					else {
						System.out.println("withdraw failure!");
						System.out.println("Your current account balance is :"+ user_details.getBalance());
						throw new InsufficientFundsException("Insufficient Funds ! ");			
					}				
				}
				break;
			}
			catch (IOException exception) {
				System.out.println(exception);
			}		
		}
		System.out.println("*************************************");
	}
		
	private static void deposite() throws NonExistingBankAccountException {
		String  user_password=null;
		int acc_no=0;
		float amount=0;
		final int MIN_DEPOSIT_AMOUNT=100;
		while(true) {
			try{
				System.out.println("Enter Your Account No:");
				acc_no=Integer.parseInt(br.readLine());
				break;
			}catch( NumberFormatException | IOException exception) {
				System.out.println(exception);
				//throw new NonExistingBankAccountException("Enter Proper Account Number!");
			}
		}
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		while(true) {
			System.out.println("Enter Password");
			try {
				user_password=br.readLine();
				UserDetails user_details=details.get(acc_no);
				String get_user_password=user_details.getPassword();
				
				if(!get_user_password.equals(user_password)) {
					System.out.println("Password wrong!");
					
				}else {
					System.out.println("login successful...");
					System.out.println();
					System.out.println("Welcome, "+user_details.getFirst_name()+" "+user_details.getLast_name());
					System.out.println("------------------------------------");
					while(true) {
						try{
							System.out.println("Enter Deposit Amount");
							amount=Float.parseFloat(br.readLine());
							if(amount >=MIN_DEPOSIT_AMOUNT) {								
								user_details=details.get(acc_no);
								float tot=user_details.getBalance()+amount;
								user_details.setBalance(tot);
								System.out.println(amount+" rupees added succesfull.");
								System.out.println("Current Account Balance: "+user_details.getBalance());
								user_details.setLast_transcation(amount);
								break;
							}else {
								System.out.println("Minimum Deposit Amount 100 INR");
								System.out.println("Deposit failure!");
							}							
							
						}catch(Exception e) {
							System.out.println(e);
							//throw new NonExistingBankAccountException("Enter Proper Amount!");
						}
					}		
				}				
				break;
			}catch(Exception exception) {
				System.out.println(exception);
			}
		}
		System.out.println("*************************************");
	}
	public static void create() {
		
		String first_name=null,last_name=null,mobile_no=null,create_password=null;
		int age=0;
		while(true) {
			System.out.println("Enter Your FirstName: ");
			try {
				first_name=br.readLine();
			} catch (IOException exception) {
				System.out.println(exception);
			}
			if(isValidName(first_name)) {
				break;
			}else {
				System.out.println("Name Should contains only alphapet! ");				
			}
		}
		while(true) {
			System.out.println("Enter Your LastName: ");
			try {
				last_name=br.readLine();
			} catch (IOException exception) {
				System.out.println(exception);
			}
			if(isValidName(last_name)) {
				break;
			}else {
				System.out.println("Name Should contains only alphapet! ");							
			}
		}
		while(true) {
			System.out.println("Enter Your Age: ");
			try {
				age=Integer.parseInt(br.readLine());
				if(isValidAge(age)) {
					break;
				}else {
					System.out.println("Age Restriction!");
					System.out.println("Age between 14 to 80 Only applicable! ");				
				}
			}catch(Exception exception) {
				System.out.println(exception);
			}			
		}		
		while(true) {
			System.out.println("Enter Your MobileNo: ");
			try {
				mobile_no=br.readLine();
				if(isValidMobileNo(mobile_no)) {
					break;
				}else {
					System.out.println("Enter valid Mobile No!");
				}
				
			} catch (Exception exception) {
				System.out.println(exception);
			}
		}		
		while(true) {
			try {
				System.out.println("Create Your User Password: ");
				create_password=br.readLine();
				if(isValidPassword(create_password)) {
					break;
				}else {
					System.out.println("Enter valid Valid password!");
					System.out.println("Password should contains\n* One Captital and Small letter\n* One numeric value\n* Minimum length more than 3");

				}
				
			} catch (Exception exception) {
				System.out.println(exception);
			}
		}
		
		UserDetails user_details=new UserDetails(ACCOUNTNO,first_name,last_name,age,mobile_no,create_password);
		details.put(ACCOUNTNO,user_details);
		System.out.println("Account created Succesfully");
		System.out.println("Your Account No :"+user_details.getAccount_num());
		
//		while(true) {
//			try {
//				System.out.println("Select Interest Type: \n1.Simple Intereset\n2.Compond Interest");
//				type=Integer.parseInt(br.readLine());
//				while(type>2) {
//					System.out.println("Enter valid Interest type !");
//					System.out.println("Select Interest Type: \n1.Simple Intereset\n2.Compond Interest");
//					type=Integer.parseInt(br.readLine());
//				}
//				if(type==1)
//					user_details.setInterest_type("Simple Interest");
//				else
//					user_details.setInterest_type("Compond Interest");				
//				break;
//			}catch(Exception e) {
//				System.out.println(e);
//			}	
//		}
		System.out.println("Thanks for Registerd in this Bank");
		System.out.println("*************************************");
		
	}
	private static boolean isValidPassword(String create_password) {
		int smallalpha_flag=0,number_flag=0,capitalalphaflag=0;
		if(create_password.length()<4) {
			return false;
		}
		for(int i=0;i<create_password.length();i++) {
			char ch=create_password.charAt(i);
			if(ch>='a' & ch<='z') {
				smallalpha_flag=1;
			}else if(ch>='A' && ch<='Z') {
				capitalalphaflag=1;
			}else if(ch>='0' && ch<='9') {
				number_flag=1;
			}
		}
		if(smallalpha_flag==1 && capitalalphaflag==1 && number_flag==1) {
			return true;
		}else return false;
	}
	private static boolean isValidAge(int age) {
		if(age>=14 && age<=80) {
			return true;
		}else {
			return false;
		}		
	}
	private static boolean isValidName(String name) {
		if(name.length()==0) {
			System.out.println("Name field should not be empty!");
			return false;
		}
		for(int i=0;i<name.length();i++) {
			if(name.charAt(i) >='a' && name.charAt(i) <='z') {
				continue;
			}else if(name.charAt(i) >='A' && name.charAt(i) <='Z') {
				continue;
			}
			else {
				return false;
			}
		}
		return true;	
	}
	private static boolean isValidMobileNo(String mobile_no) {


		if(mobile_no.length()==10) {
			for(int i=0;i<mobile_no.length();i++) {
				char ch=mobile_no.charAt(i);
				if(ch>='0' && ch<='9') {
					continue;
				}else {
					return false;
				}
			}
		}else {
			return false;
		}
		return true;
	}
	private float simpleInterestCalculation(int amount, int year, double RATE) {
		float interest=0;
		interest=(float) ((amount*year*RATE)/100);
		return interest;
	}
	private float compoundInterestCalculation(int amount, int year, double RATE) {
		float interest=0;
		interest = (float) (amount*Math.pow(1+(RATE/100),year))-amount;
		return interest;
	}
}


