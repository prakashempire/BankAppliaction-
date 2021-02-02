package Bank;

public class GeneralAcoount extends BankAcoount{
	int account_num,age;
	String first_name,last_name;
	
	GeneralAcoount(){
	}
	
	public GeneralAcoount(int account_num, int age, String first_name, String last_name) {
		super();
		this.account_num = account_num;
		this.age = age;
		this.first_name = first_name;
		this.last_name = last_name;
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
