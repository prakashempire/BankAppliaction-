package Bank;

import java.io.IOException;
import java.util.Map;

public class CreateAccount extends Accounts{

	public CreateAccount() {}
	
	public Map<Integer,UserDetails> createAccount() {
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
		System.out.println("Thanks for Registerd in this Bank");
		System.out.println("*************************************");
		return details;
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
}
