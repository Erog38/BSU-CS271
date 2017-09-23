import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Chandra Adhikari
 *
 */
public class AccountManagerTest {

	private AccountManager accountManager = null;
	private UserProfile userProfile = null;
	
	//User info to check for assertFalse()
	private String badUserName = "thomasrice";
	private String badPassword = "TheGre@tPass1";
	
	// Good username to setup the initial profile (for assertTrue())
	private String goodFirstName = "John";
	private String goodLastName = "Green";
	private String goodEmail = "johngreen@test.com";
	private String goodPassword = "TestP@ss2";
	private String goodUserName = "johngreen";
	
	//Invalid input structures
	private String invalidEmail1 = "myemail@", invalidEmail2 = "@email@email";
	private String invalidUserName1 = "testU", invalidUserName2 = "thisismyusername";
	private String invalidPassword1 = "pass", invalidPassword2 = "Thisismyp@sswrd";
	
	
	/**
	 * Constructor for the test class.
	 */
	public AccountManagerTest() {
		try {
			setUp();
		} catch (Exception e) {
			System.out.println("Exception occured " + e);
			e.printStackTrace();
		}
	}
	
	protected void setUp() throws Exception {
		userProfile = new UserProfile();
		createProfile(userProfile, goodFirstName, goodLastName, goodEmail, goodPassword, goodUserName);
		setUpAccountManager();
	}

	private void setUpAccountManager() {
		accountManager = new AccountManager();
		accountManager.put(userProfile);
	}

	/**
	 * Simply uses the user information to create a profile to test
	 */
	private void createProfile(UserProfile tmpUser, String first, String last, String email, String pass, String usrName) {
		tmpUser.setFirstName(first);
		tmpUser.setLastName(last);
		tmpUser.setEmail(email);
		tmpUser.setEmail(pass);
		tmpUser.setUserName(usrName);
		tmpUser.setPassword(pass);
	}

	protected void tearDown() throws Exception {
		accountManager = null;
		userProfile = null;
	}
	
	@Test
	public void testContainsUsername() {
		boolean tmp = accountManager.containsUsername(goodUserName);
		assertTrue("", tmp);
		tmp = accountManager.containsUsername(badUserName);
		assertFalse( "Specified username (" + badUserName + ") doesn't exist." ,tmp);
	}

	@Test
	public void testVerifyUserName() {
		boolean tmp = accountManager.verifyUserName(goodUserName);
		assertTrue("", tmp);
		tmp = accountManager.verifyUserName(invalidUserName1);
		assertFalse( "Specified username (" + invalidUserName1 + ") is invalid." ,tmp);
		tmp = accountManager.verifyUserName(invalidUserName2);
		assertFalse( "Specified username (" + invalidUserName2 + ") is invalid." ,tmp);
	}

	@Test
	public void testVerifyEmailFormat() {
		boolean tmp = accountManager.verifyEmailFormat(goodEmail);
		assertTrue("", tmp);
		tmp = accountManager.verifyEmailFormat(invalidEmail1);
		assertFalse( "Specified email (" + invalidEmail1 + ") is invalid.", tmp);
		tmp = accountManager.verifyEmailFormat(invalidEmail2);
		assertFalse( "Specified email (" + invalidEmail2 + ") is invalid.", tmp);
	}

	@Test
	public void testVerifyPassword() {
		boolean tmp = accountManager.verifyPassword(goodUserName, goodPassword);
		assertTrue("", tmp);
		tmp = accountManager.verifyPassword(badUserName, invalidPassword1);
		assertFalse( "Specified username (" + badUserName + " and " + invalidPassword1 + ") are invalid." ,tmp);
		tmp = accountManager.verifyPassword(badUserName, invalidPassword2);
		assertFalse( "Specified username (" + badUserName + " and " + invalidPassword2 + ") are invalid." ,tmp);
	}

	@Test
	public void testVerifyCreateProfile() {
		//Below line should return username taken since a profile has already been created
		boolean tmp = accountManager.verifyCreateProfile(userProfile, "");
		assertFalse("", tmp); // False because username already exist
		UserProfile nullProfile = null;
		tmp = accountManager.verifyCreateProfile(nullProfile, "");
		assertFalse( "Specified profile is invalid." ,tmp);
		UserProfile invalidProfile = new UserProfile();
		createProfile(invalidProfile, "", "", "", "", "");
		assertFalse( "Specified profile is invalid." ,tmp);
	}

	@Test
	public void testVerifyLogin() {
		boolean tmp = accountManager.verifyLogin(goodUserName, goodPassword);
		assertTrue("", tmp);
		tmp = accountManager.verifyLogin(goodUserName, badPassword);
		assertFalse( "Specified username (" + goodUserName + " and " + badPassword + ") are invalid." ,tmp);
		tmp = accountManager.verifyLogin(goodUserName, "");
		assertFalse( "The password is bank." ,tmp);
		tmp = accountManager.verifyLogin("", goodPassword);
		assertFalse( "The password is bank." ,tmp);
	}

}
