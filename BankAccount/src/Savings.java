
public class Savings extends Account {

	fee = 0.35;
	
	public void applyInterest()
	{
		balance += (balance * interestRate);
	}
	
}
