import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Chandra Adhikari
 *
 */
public class AccountManagerTest {

	private AccountManager accountManager = null;
	
	/**
	 * Constructor for the test class.
	 */
	public AccountManagerTest() {
		super(); // calling AccountManager.java constructor
	}
	
	protected void setUp() throws Exception {
//		super.setUp();
		accountManager = new AccountManager();
	}

	protected void tearDown() throws Exception {
		accountManager = null;
//		super.tearDown();
	}
	
	@Test
	public void testContainsUsername() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifyUserName() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testVerifyEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifyPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifyCreateProfile() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifyLogin() {
		fail("Not yet implemented");
	}

}
