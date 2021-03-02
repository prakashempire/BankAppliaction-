package Bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class TransferAmount {
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public TransferAmount() {}
	void transferAmount(Map<Integer, UserDetails> details) throws NonExistingBankAccountException {
		
		System.out.println("Welcome, ");
		int acc_no1=0,acc_no2=0,transfer_amount=0;
		String user_password=null;
			
		while(true) {
			System.out.println("Enter your Account No: ");	
			try {
				acc_no1 = Integer.parseInt(br.readLine());
				break;
			} catch (NumberFormatException | IOException exception) {
				System.out.println(exception);
			}
		}
		if(!details.containsKey(acc_no1)) {
			throw new NonExistingBankAccountException("Account Not Registered Create Bank Account !");
		}
		UserDetails user_details=details.get(acc_no1);
		String get_user_password=user_details.getPassword();
		while(true) {			
			System.out.println("Enter user password");			
			try {
				user_password=br.readLine();
				if(!get_user_password.equals(user_password)) {
					System.out.println("Password wrong!");
				}else {
					System.out.println("Login successfull!");
					System.out.println("Welcome, "+user_details.getFirst_name()+" "+user_details.getLast_name());
					System.out.println("Your Current balance: "+user_details.getBalance());
					break;
				}
			}catch(Exception exception) {
				System.out.println(exception);
			}
		}
		int option=0;
		System.out.println("1-> Transfer amount (press 1)");
		System.out.println("2-> Exit (press 2)");
		while(true) {
			try {
				option=Integer.parseInt(br.readLine());
				break;
			}catch(Exception exception) {
				System.out.println(exception);
			}
		}		
		while(true) {
			if(option==1) {
				while(true) {
					System.out.println("Enter Receiver Account Number: ");
					try {
						acc_no2 = Integer.parseInt(br.readLine());
						break;
					} catch (NumberFormatException | IOException exception) {
						System.out.println(exception);
					}
				}
				if(!details.containsKey(acc_no2)) {
					throw new NonExistingBankAccountException("Unavailable Account Enter Valid Account Number!");
				}else {
					System.out.println("Account Exist...\nContinue..");
				}
				while(true) {
					System.out.println("Enter Amount to Transfer: ");
					try {
						transfer_amount = Integer.parseInt(br.readLine());				
						break;
					} catch (NumberFormatException | IOException exception) {
						System.out.println(exception);
					}
				}	
				transfer(acc_no1,acc_no2,transfer_amount,details);
				break;
			}else if(option==2) {
				new Accounts().process();
			}else {
				System.out.println("Enter valid option!");
			}
		}		
		System.out.println("*************************************");
	}
	public static void transfer(int acc_no1,int acc_no2,int transfer_amount,Map<Integer, UserDetails> details) {
		UserDetails sender_userDetails=details.get(acc_no1);
		UserDetails receiver_userDetails=details.get(acc_no2);
		float sender_balance=sender_userDetails.getBalance();
		float receiver_balance=receiver_userDetails.getBalance();
		while(true) {
			if((sender_balance-transfer_amount) >=500) {
				sender_userDetails.setBalance(sender_balance-transfer_amount);
				receiver_userDetails.setBalance(receiver_balance+transfer_amount);
				sender_userDetails.setLast_transcation(-transfer_amount);
				receiver_userDetails.setLast_transcation(transfer_amount);
				System.out.println("Current Balance: "+sender_userDetails.getBalance());
				System.out.println("Transfered Successfull!");
				break;
			}else {
				System.out.println("Pls Maintain 500 INR !");
			}
		}
	}

}
