/**
 * This class creates a user profile object which stores all of the necessary variables for a user profile.
 * 
 * @author Jorah Hinman
 *
 */
public class UserProfile {
	//Instance variables!
	private String name;
	private String email;
	private String password;
	
	/**
	 * This is the basic constructor.
	 */
	public UserProfile() {
		initProfile();
	}

	/**
	 * This method initializes all of our instance variables
	 */
	private void initProfile() {
		name = "";
		email = "";
		password = "";
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public void setPassword(String pswrd){
		this.password = pswrd;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getPassword(){
		return password;
	}
}
