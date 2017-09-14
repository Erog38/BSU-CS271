import java.util.Hashtable;

/**
 * This class stores all of the UserProfile objects, and checks for validity.
 * 
 * @author Jorah Hinman
 *
 */
public class AccountManager {
	
    private Hashtable<String, UserProfile> userAccount;
    
    /**
     * creates new AccountManager object
     */
    public AccountManager() {
        userAccount = new Hashtable<String, UserProfile>();
    }
    
    /**
     * Returns 'true' if username is found in AccountManager
     * @param username - String
     * @return found - boolean
     */
    public boolean containsUsername(String userName){
    	boolean found = false;
    	if(userName != null){
    		found = userName.equals(userAccount.get(userName));
    	}
    	return found;
    }
    
    /**
     * Returns 'true' if username is a valid username. Returns 'false' is username is
     * invalid.
     * 
     * @param userName - String
     * @return valid - boolean
     */
    public boolean verifyUserName(String userName){
    	boolean valid = true;
    	if(containsUsername(userName)){
    		valid = false;
    	}
    	if(userName.length() > 12){
    		valid = false;
    	}
    	return valid;
    }
    
    /**
     * Retuns true if email is valid. Returns false if email is invalid.
     * @param email - String
     * @return valid - boolean
     */
    public boolean verifyEmail(String email){
    	boolean valid = true;
    	return valid;
    }
    /**
     * Return true is password entered matches password associated with the
     * provided username
     * @param userName - String
     * @param password - String
     * @return
     */
    public boolean verifyPassword(String userName, String password) {
       if(userName == null || password ==null){
    	   return false; 
       }  
       String storedPassword = userAccount.get(userName).getPassword();
       return storedPassword != null && storedPassword.equals(password);
    }
    
    /**
     * Returns true if profile can be created. Returns false if profile cannot be
     * created.
     * 
     * @param user - UserProfile
     * @return canCreate - boolean
     */
    public boolean verifyCreateProfile(UserProfile user){
    	boolean canCreate = true;
    	if(!verifyUserName(user.getName())){
    		canCreate = false;
    	}
    	if(!verifyEmail(user.getEmail())){
    		
    	}
    	if(!verifyPassword(user.getName(), user.getPassword())){
    		
    	}
    	return canCreate;
    }
    
    /**
     * Returns true if username exists in account manager and password matches the
     * password saved to that username. Returns false if username doesn't exist in
     * account manager or passowrd doesn't match.
     * @param userName
     * @param password
     * @return
     */
    public boolean verifyLogin(String userName, String password){
    	return false;
    }
}
