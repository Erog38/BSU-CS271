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
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
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
		userName = "";
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public void setUserName(String usrName){
		this.userName = usrName;
	}
	
	public String getUserName(){
		return userName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
