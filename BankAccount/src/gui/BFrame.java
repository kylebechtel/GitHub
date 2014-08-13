package gui;

import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BFrame extends JFrame
{	
	private static final long serialVersionUID = 1L;

	private MenuBar menu;
	
	private Menu help;

	private Menu account;

	private Menu user;
	
	public BFrame()
	{
		menu = new MenuBar();
		
		init();
		
		menu.add(account);
		menu.add(user);
		menu.add(help);
		
		setTitle("D.C. Systems - Banksoft V 3.0");
        setMenuBar(menu);
        setSize(new Dimension(500, 400));
        
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    BFrame.this.windowClosed();
                }
            }
        ); 
	}
	private void init()
	{
		account = new Menu();
		account.setLabel("Account");
		
		setupDepositMenu();
        setupWithdrawMenu();
        
        user = new Menu();
        user.setLabel("User");
        
        setupSignIn();
        setupSignOut();
        
		help = new Menu();
		help.setLabel("Help");
	}
    
	private void signOut()
	{
		if(!Core.isValid())
    	{
    		JOptionPane.showMessageDialog(null, "Sorry, but there is no user currently logged in.  Therefore, you can not sign out of a program you did not sign into.",
    				"Login Warning",JOptionPane.WARNING_MESSAGE);
    		return;
    	}
    	
    	
    	Core.signOut();
    	JOptionPane.showMessageDialog(null, "Current User Signed out.", "Sign Out Complete.",JOptionPane.DEFAULT_OPTION);
	}
	private void setupSignOut()
	{
		MenuItem userSignOut = new MenuItem();
		userSignOut.setLabel("Sign Out");
		
		// This listener Should display the Deposit Menu
		userSignOut.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	signOut();
                	
                }
            }
        );
		
        user.add(userSignOut);
	}
	private void setupSignIn()
	{
		MenuItem userSignIn = new MenuItem();
		userSignIn.setLabel("Sign In");
		
		// This listener Should display the Deposit Menu
		userSignIn.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if(Core.isValid())
                	{
                		JOptionPane.showMessageDialog(null, "Sorry, another user is still logged in.  Preparing to sign them out.",
                				"Login Warning",JOptionPane.WARNING_MESSAGE);
                		signOut();
                	}
                	String acc = JOptionPane.showInputDialog(null, "Please input your Username",
                			"Sign In - Username",JOptionPane.QUESTION_MESSAGE);

                	if(acc == null || acc.equals(""))
                	{
                		JOptionPane.showMessageDialog(null, "The Username you entered was not valid!","Invalid Username",JOptionPane.ERROR_MESSAGE);
                		return;
                	}
                	
                	String pass = JOptionPane.showInputDialog(null, "Please input your passsword.","Sign in - Password",JOptionPane.QUESTION_MESSAGE);

                	if(pass == null || pass.equals(""))
                	{
                		JOptionPane.showMessageDialog(null, "The Ammount you entered was not valid!","Invalid Selection",JOptionPane.ERROR_MESSAGE);
                		return;
                	}
                	
                	Core.signIn(acc, pass);
                	JOptionPane.showMessageDialog(null, "You have signed into user \""+acc+"\"","Sign in Complete",JOptionPane.DEFAULT_OPTION);
                }
            }
        );
		
        user.add(userSignIn);
	}
	/**************************************************
	 * Account Button Functions						  *
	 **************************************************/
	private void setupWithdrawMenu()
	{
		MenuItem accountWithdraw = new MenuItem();
		accountWithdraw.setLabel("Withdraw");
		
		// This listener Should display the Deposit Menu
		accountWithdraw.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if(!Core.isValid())
                	{
                		JOptionPane.showMessageDialog(null, "Sorry, Withdraws are disabled. Please sign in.",
                				"Account Error",JOptionPane.WARNING_MESSAGE);
                		return;
                	}
                	String acc = JOptionPane.showInputDialog(null, "Please input the Account Number you wish to"
                			+ " make a deposit to.","Select Account",JOptionPane.QUESTION_MESSAGE);
                	int accNum =-1;
                	try
                	{
                		accNum = Integer.parseInt(acc);
                	}
                	catch(Exception e2)
                	{
                		JOptionPane.showMessageDialog(null, "The Account Number was not valid!","Invalid Selection",JOptionPane.ERROR_MESSAGE);
                		return;
                	}
                	
                	int amm = -1;
                	String inS = JOptionPane.showInputDialog(null, "Please enter the ammount you wish to deposit. In the form: 100.43", "Enter Deposit",JOptionPane.QUESTION_MESSAGE);
                	try
                	{
                		amm = Integer.parseInt(acc);
                	}
                	catch(Exception e2)
                	{
                		JOptionPane.showMessageDialog(null, "The Ammount you entered was not valid!","Invalid Selection",JOptionPane.ERROR_MESSAGE);
                		return;
                	}
                	
                	Core.current.deposit(accNum, amm);
                	
                }
            }
        );
		
        account.add(accountWithdraw);
	}
	private void setupDepositMenu()
	{
		MenuItem accountDeposit = new MenuItem();
		accountDeposit.setLabel("Deposit");
		
		// This listener Should display the Deposit Menu
        accountDeposit.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if(!Core.isValid())
                	{
                		JOptionPane.showMessageDialog(null, "Sorry, Deposits disabled. Please sign in.",
                				"Account Error",JOptionPane.WARNING_MESSAGE);
                		return;
                	}
                	String acc = JOptionPane.showInputDialog(null, "Please input the Account Number you wish to"
                			+ " make a deposit to.","Select Account",JOptionPane.QUESTION_MESSAGE);
                	int accNum =-1;
                	try
                	{
                		accNum = Integer.parseInt(acc);
                	}
                	catch(Exception e2)
                	{
                		JOptionPane.showMessageDialog(null, "The Account Number was not valid!","Invalid Selection",JOptionPane.ERROR_MESSAGE);
                		return;
                	}
                	
                	int amm = -1;
                	String inS = JOptionPane.showInputDialog(null, "Please enter the ammount you wish to deposit. In the form: 100.43", "Enter Deposit",JOptionPane.QUESTION_MESSAGE);
                	try
                	{
                		amm = Integer.parseInt(inS);
                	}
                	catch(Exception e2)
                	{
                		JOptionPane.showMessageDialog(null, "The Ammount you entered was not valid!","Invalid Selection",JOptionPane.ERROR_MESSAGE);
                		return;
                	}
                	
                	try
                	{
                		Core.current.deposit(accNum, amm);
                	}
                	catch(Exception e3)
                	{
                		JOptionPane.showMessageDialog(null, "You did not have access to the account "
                				+accNum+".", "Invalid Account Error",JOptionPane.ERROR_MESSAGE);
                	}
                }
            }
        );
		
        account.add(accountDeposit);
	}

	
	/*************************************************
     * Shutdown										 *
     * 												 *
     * Shutdown procedure when run as an application *
     *************************************************/
	protected void windowClosed() {
        // Exit application.
        System.exit(0);
    }
}
