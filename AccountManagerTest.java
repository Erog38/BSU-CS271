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
	private String badFirstName = "Thomas";
	private String badLastName = "Rice";
	private String badEmail = "thomasrice";
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
	private String invalidPassword1 = "pass", invalidPassword2 = "Thisismyp@ssw0rd1";
	
	
	/**
	 * Constructor for the test class.
	 */
	public AccountManagerTest() {
		super(); // calling AccountManager.java constructor
		try {
			setUp();
		} catch (Exception e) {
			System.out.println("Exception occured" + e);
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

	private void createProfile(UserProfile tmpUser, String first, String last, String email, String pass, String usrName) {
		tmpUser.setFirstName(first);
		tmpUser.setLastName(last);
		tmpUser.setEmail(email);
		tmpUser.setEmail(pass);
		tmpUser.setUserName(usrName);
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
		assertFalse( "Specified email (" + invalidEmail1 + ") is invalid." ,tmp);
		tmp = accountManager.verifyEmailFormat(invalidEmail2);
		assertFalse( "Specified email (" + invalidEmail2 + ") is invalid." ,tmp);
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
//
//	@Test
//	public void testVerifyCreateProfile() {
//		boolean tmp = accountManager.verifyCreateProfile(userProfile);
//		assertTrue("", tmp);
//		UserProfile up = null;
//		tmp = accountManager.verifyCreateProfile(up);
//		assertFalse( "Specified profile (" + up + ") is invalid." ,tmp);
//		UserProfile badProfile = new UserProfile();
//		createProfile(badProfile, "TestFirst", "TestLast", "Test@email*email", "Thisisalongpassword", "Thisisalongusername");
//	}
//
//	@Test
//	public void testVerifyLogin() {
//		boolean tmp = accountManager.verifyLogin(goodUserName, goodPassword);
//		assertTrue("", tmp);
//		tmp = accountManager.verifyLogin(goodUserName, badPassword);
//		assertFalse( "Specified username (" + goodUserName + " and " + badPassword + ") are invalid." ,tmp);
//	}

}
