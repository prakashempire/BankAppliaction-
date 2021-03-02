package Bank;

public interface InterestType {
	enum interest_type {SimpleInterest , CompoundInterest}
	public void setInterestType(InterestType.interest_type val);
}
