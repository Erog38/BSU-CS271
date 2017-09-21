import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * This class stores all of the UserProfile objects, and checks for validity.
 * 
 * @author Jorah Hinman, Phil Gore
 *
 */
public class AccountManager {
	
    private Hashtable<String, UserProfile> userAccounts;
    
    /**
     * creates new AccountManager object
     */
    public AccountManager() {
        userAccounts = new Hashtable<String, UserProfile>();
    }
    
    public void put(UserProfile account) {
    	userAccounts.put(account.getUserName(), account);
    }
    public UserProfile get(String username) {
    	return userAccounts.get(username);
    }
    
    /**
     * Returns 'true' if username is found in AccountManager
     * @param username - String
     * @return found - boolean
     */
    public boolean containsUsername(String userName){
    	if(userName == null){
    		return false;
    	}
    	return userAccounts.containsKey(userName);
    }
    
    /**
     * Returns 'true' if username is a valid username. Returns 'false' is username is
     * invalid.
     * 
     * @param userName - String
     * @return valid - boolean
     */
    public boolean verifyUserName(String userName){
    	System.out.println("Verifying username");
    	if(!containsUsername(userName)){
    		return false;
    	}
    	if(userName.length() > 12){
    		return false;
    	}
    	if(userName.trim() == "") {
    		return false;
    	}
    	return true;
    }
    
    /**
     * Returns 'true' if username is a valid new username. Returns 'false' is username is
     * invalid.
     * 
     * @param userName - String
     * @return valid - boolean
     */
    private boolean validNewUserName(String userName){
    	if(containsUsername(userName)){
    		return false;
    	}
    	if(userName.length() > 12){
    		return false;
    	}
    	if(userName.trim() == "") {
    		return false;
    	}
    	return true;
    }
    
    /**
     * Retuns true if email is valid. Returns false if email is invalid.
     * @param email - String
     * @return valid - boolean
     */
    public boolean verifyEmailFormat(String email){
    	if (email == null) {
    		return false;
    	} else if (email.trim() == "") {
    		return false;
    	} 
    	 Pattern p = Pattern.compile("^(.+)@(.+)$");
    	 Matcher m = p.matcher(email);
    	if (!m.find()) {
    		return false;
    	}
    	return true;
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
       if (!validPassword(password)) {
    	   return false;
       }
       if (!this.containsUsername(userName)) {
    	   return false;
       }
       String storedPassword = userAccounts.get(userName).getPassword();
       return storedPassword != null && storedPassword.equals(password);
    }
    
    /**
     * Return true if the password passes all the necessary tests.
     * 
     * @param password - String
     * 
     * @return
     */
    public boolean validPassword(String password) {
    	if (password.trim() == "") {
     	   return false;
        }
        if (password.length() < 6) {
     	   return false;
        }
        if (!password.matches(".*\\d+.*")) {
     	   System.out.println("Returning False, no digit.");
     	   System.out.println(password);
     	   return false;
        }
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        if (!m.find()) {
     	   System.out.println("Returning False.");
     	   return false;
        }	
        return true;
    }
   
    
    /**
     * Returns true if profile can be created. Returns false if profile cannot be
     * created.
     * 
     * @param user - UserProfile
     * @return canCreate - boolean
     */
    public boolean verifyCreateProfile(UserProfile user){
    	if(!validNewUserName(user.getUserName())){
    		JOptionPane.showMessageDialog(null, "Username Taken", null, JOptionPane.PLAIN_MESSAGE);
    		return false;
    	}
    	if(!verifyEmailFormat(user.getEmail())){
    		JOptionPane.showMessageDialog(null, "Invalid Email", null, JOptionPane.PLAIN_MESSAGE);
    		return false;
    	}
    	if(!validPassword(user.getPassword())){
    		JOptionPane.showMessageDialog(null, "Invalid Password must be at least 6 characters long and contain a number"
    				+ " and a special character", null, JOptionPane.PLAIN_MESSAGE);
    		return false;
    	}
    	return true;
    }
    
    /**
     * Returns true if username exists in account manager and password matches the
     * password saved to that username. Returns false if username doesn't exist in
     * account manager or password doesn't match.
     * @param userName
     * @param password
     * @return
     */
    public boolean verifyLogin(String userName, String password){
    	if(!verifyUserName(userName)){
    		return false;
    	}
 
    	if(!verifyPassword(userName, password)){
    		return false;
    	}
    	if (userAccounts.contains(userName)) {
    		return true;
    	}
    	return false;
    }
}
