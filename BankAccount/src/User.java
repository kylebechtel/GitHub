import java.util.ArrayList;

public class User {
	
	private String accName;
	private String accPass;
	
	private String securityQuestion;
	private String securityAnswer;
	
	private String fName;
	private String lName;
	private int age;
	
	// Address fields
	Address myAddress;

	ArrayList<Account> myAccounts;
	
	
	/******************************
	 * Constructors               *
	 ******************************/
	public User()
	{
		accName = "Invalid";
		accPass = "";
		
		securityQuestion = "Undefined Question";
		securityAnswer = "";
		fName = "John";
		lName = "Doe";
		
		age = 0;
		
		myAddress = null;
		myAccounts = null;	
	}
	
	public User(String uName, String pass, String q, String a, String f, String l, int ag)
	{
		accName = uName;
		accPass = pass;
		securityQuestion = q;
		securityAnswer = a;
		fName = f;
		lName = l;
		age = ag;
		
		myAccounts = new ArrayList<Account>();
	}

	public boolean addAccount(int num, int bal)
	{
		if (num >=0 && bal >=0)
			return myAccounts.add(new Account(num,bal));
		else 
			return false;
	}
	public boolean removeAccount(int num)
	{
		for(int i = 0; i < myAccounts.size(); i++)
			if(myAccounts.get(i).getAccNum() == num)
			{
				myAccounts.remove(i);
				return true;
			}
		return false;
	}

	/******************************
	 * Accessor and Mutators      *
	 ******************************/
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccPass() {
		return accPass;
	}
	public void setAccPass(String accPass) {
		this.accPass = accPass;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}





	
	public int getAge() {
		return age;
	}





	public void setAge(int age) {
		this.age = age;
	}

	
}
