package Bank;

public class UserDetails extends BankAccounts{
	int account_num,age;
	String first_name,last_name,mobile_no,password;
	
	UserDetails(){
	}
	
	public UserDetails(int account_num,  String first_name, String last_name,int age,String mobile_no,String password) {
		this.account_num = account_num;
		this.age = age;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_no=mobile_no;
		this.password=password;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public int getAccount_num() {
		return account_num;
	}
	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
}
