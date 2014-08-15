package gui;

import java.io.IOException;

import bankEntities.User;


public class Core {

	private static BFrame frame;
	
	protected static User current;
	
	private static void init()
    {
    	frame 		= new BFrame();
    	// panel	= new BPanel();
    }
    
    public static void main(String[] args) throws IOException {
    	init();
    	frame.setVisible(true);
    	frame.setResizable(false);
		//frame.add(screen.master);
		
		
		// Thread GUI_Thread runs the Router GUI
		//Thread GUI_Thread = new Thread(rtScreen);
		//GUI_Thread.start();
    }
    
    public static boolean isValid()
    {
    	if(current != null)
    		return true;
    	return false;
    }

	public static void signIn(String acc, String pass) {
		// SQL Call here to validate the user and pass
		boolean validAcc = true;
		if(validAcc)
			;// request Data for user
		current = new User();
		
	}

	public static void signOut() {
		// write back to server
		current = null;
		
	}
}
