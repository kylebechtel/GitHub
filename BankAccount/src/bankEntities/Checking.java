package bankEntities;

public class Checking extends Account{

	protected final int SURCHARGE = 35;
	private final int MINIMUM = 150; // Just For Checking
	
	public boolean withdraw(int num)
	{
		if(balance >= num)
			balance -= num;
		else 
		{
			balance -= SURCHARGE;
			return false;
		}
		
		if (balance < MINIMUM)
			balance -= SURCHARGE;
		return true;
	}
}
