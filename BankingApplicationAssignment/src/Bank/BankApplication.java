package Bank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class BankApplication extends GeneralAcoount{
	
	static Map<Integer,GeneralAcoount> details=new HashMap<>();
	static Scanner sc=new Scanner(System.in);
	static int ACCOUNTNO=1001;
	
	public static void main(String[] args) throws InsufficientFundsException  {
		
		System.out.println("Welcome to Our Bank!");
		String option="1.Create Bank Account\n2.Deposit\n3.Withdraw\n4.Display last 5 Transcation\n5.Display Account\n6.exit";
		System.out.println(option);
		
		while(sc.hasNext()) {
			int select=Integer.parseInt(sc.nextLine());
			switch(select) {
				case 1:{	
					create();					
					ACCOUNTNO++;
					break;
				}
				case 2:{					
					try {
						deposite();
					} catch (NonExistingBankAccountException e) {
						
						System.out.println(e);
					}
					break;
				}
				case 3:{
					try {
						withdraw();
					} catch (NonExistingBankAccountException e) {
						System.out.println(e);
					}					
					break;
				}
				case 4:{
					try {
						lastTranscation();
					} catch (NonExistingBankAccountException e) {
						System.out.println(e);
					}
					break;
				}
				case 5:
					try {
						display();
					} catch (NonExistingBankAccountException e) {
						System.out.println(e);
					}					
					break;
				case 6:{
					System.out.println("Thanks for Using!");
					return;
				}
				default:{
					System.out.println("Enter valid option!!!!");
				}
			}
			System.out.println("***********************************");
			System.out.println(option);
			
		}
	}
	private static void display() throws NonExistingBankAccountException{
		System.out.println("Enter your Account No: ");
		int acc_no=Integer.parseInt(sc.nextLine());
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}		
		
		else {
			GeneralAcoount ga=details.get(acc_no);
			System.out.println("Name :" +ga.getFirst_name()+" "+ga.getLast_name()+" Age:"+ga.getAge());
			System.out.println("-------------------------------------");
			System.out.println("Account No: "+ga.getAccount_num());
			System.out.println("Balance: "+ga.getBalance());
			System.out.println("Interest type: "+ga.getInterest_type());
		}
	}
	private static void lastTranscation() throws NonExistingBankAccountException {
		
		System.out.println("Enter your Accout no:");
		int acc_no=Integer.parseInt(sc.nextLine());
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		
		GeneralAcoount ga=details.get(acc_no);
		LimitedQueue q=ga.getLast_transcation();
		System.out.println("Current Acoount balance: "+ga.getBalance());
		System.out.println("----------------------");
		System.out.println("Last 5 Transcation are:\n");
		for(int i=0;i<q.size();i++) {
			float val=(float) q.get(i);
			if(val>0) {
				System.out.println("Deposited: "+val);
			}else if(val<0) {
				System.out.println("Withdrawd: "+val);
			}else {
				//System.out.println("No trancation");
			}
		}
		
	}
	private static void withdraw() throws NonExistingBankAccountException, InsufficientFundsException {
		
		System.out.println("Enter Your Account No:");
		int acc_no=Integer.parseInt(sc.nextLine());	
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		
		System.out.println("Enter withdraw amount: ");
		float amount=Float.parseFloat(sc.nextLine());
		float transcation=0;
		
		GeneralAcoount ga=details.get(acc_no);
		float bal=ga.getBalance()-amount;
		if(bal>0) {
			ga.setBalance(bal);
			System.out.println("Withdrawd succefully");
			System.out.println("Current Account Balance: "+ga.getBalance());
			transcation=-amount;
			ga.setLast_transcation(-amount);
		}
		else {
			System.out.println("withdraw failure!");
			System.out.println("Your current account balance is :"+ ga.getBalance());
			throw new InsufficientFundsException("Insufficient Funds ! ");			
		}				
	}
	
	private static void deposite() throws NonExistingBankAccountException {
		System.out.println("Enter Your Account No:");
		int acc_no=Integer.parseInt(sc.nextLine());
		if(!details.containsKey(acc_no)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		System.out.println("Enter Deposite Amount");
		float amount=Float.parseFloat(sc.nextLine());
		
		if(amount >=100) {
			
			GeneralAcoount ga=details.get(acc_no);
			float tot=ga.getBalance()+amount;
			ga.setBalance(tot);
			System.out.println("Amount added");
			System.out.println("Current Account Balance: "+ga.getBalance());
			ga.setLast_transcation(amount);
		}else {
			System.out.println("You should deposit minimum 100 rps");
			System.out.println("deposite failure!");
		}
	}
	public static void create() {
		
		System.out.println("Enter Your FirstName: ");
		String first_name=sc.nextLine();
		System.out.println("Enter Your LastName: ");
		String last_name=sc.nextLine();
		System.out.println("Enter Your Age: ");
		int age=Integer.parseInt(sc.nextLine());
		GeneralAcoount ba=new GeneralAcoount(ACCOUNTNO,age,first_name,last_name);
		details.put(ACCOUNTNO,ba);
		System.out.println("Account created Succesfully");
		System.out.println("Your Account No :"+ba.getAccount_num());
		
		System.out.println("Select Interest Type: \n1.Simple Intereset\n2.Compond Interest");
		int type=Integer.parseInt(sc.nextLine());
		while(type>2) {
			System.out.println("Enter valid Interest type !");
			System.out.println("Select Interest Type: \n1.Simple Intereset\n2.Compond Interest");
			type=Integer.parseInt(sc.nextLine());
		}
		if(type==1)
		ba.setInterest_type("Simple Interest");
		else
		ba.setInterest_type("Compond Interest");
		System.out.println("Thanks for Registerd in this Bank");
	}
}
