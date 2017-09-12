import java.util.Hashtable;


public class AccountManager {
	
    private Hashtable<String, UserProfile> userAccount;
    
    public AccountManager() {
        userAccount = new Hashtable<String, UserProfile>();
    }
    
    public boolean verify(String userName, String password) {
       if(userName == null || password ==null)    
    	   return false;   
       String storedPassword = userAccount.get(userName).getPassword();
       return storedPassword != null && storedPassword.equals(password);
    }
}
