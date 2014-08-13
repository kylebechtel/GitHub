package bankEntities;

public class Savings extends Account {

	double interestRate;
	public Savings()
	{
		fee = 0.35;
		interestRate = .05;
	}
	
	public boolean withdraw(int num)
	{
		System.out.print("To which account would you like to transfer that to?  Please specify the account number: ");
		int accNum = 10;
		
		return transferMoney(accNum, num);
	}
	public boolean transferMoney(int accNum, int amount)
	{
		if(amount <= 0)			// Make sure it is a valid transfer
			return false;
								// Query database for accNum, once found
		
		if(accNum < 0) 			// If the accNum is not found
			return false;
		
		// If the Account was found, but the name does not match,
		// flag it
		if (1 == 21)
			return false;
		
							// Add the amount to it
		balance -= amount;	// Deduct the amount from this account
		return true;		
	}
	
	public void setInterestRate(int i)
	{
		interestRate = i;
	}
	public void applyInterest()
	{
		balance += (balance * interestRate);
	}
	
}
