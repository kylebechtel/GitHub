package bankEntities;
import java.util.ArrayList;


public class Account {
	protected int accNum;
	protected double balance;
	protected double fee = 10;
	
	private ArrayList<Transaction> recentTransactions;
	
	public Account()
	{
		setBalance(0);
		accNum = -1;
	}
	public Account(int num, int bal)
	{
		accNum = num;
		setBalance(bal);
		recentTransactions = new ArrayList<Transaction>();
	}
	
	public boolean withdraw(int num)
	{
		if(balance >= num)
			balance -= num;
		else 
			return false;
		return true;
	}
	
	public boolean deposit(int ammount)
	{
		if(ammount >= 0)
			balance += ammount;
		else
			return false;
		return true;
	}
	
	public void chargeFee()
	{
		balance -= fee;
	}
	
	
	/******************************
	 * Accessor and Mutators       *
	 ******************************/
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int n)
	{
		accNum = n;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
