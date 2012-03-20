package DietersSandbox;

import java.util.ArrayList;
import java.util.Collection;
import DietersSandbox.domainman.DomainObject;
import DietersSandbox.domainman.DomainObjectManager;
import system.Hospital;
import users.User;
import users.UserManager;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;

/**
 * This class will be used as a login controller. There will be a 1:1 relation
 * between the amount of logged in users and the amount of controllers. Each
 * logincontroller will remember what user they logged in.
 */
public class LoginController extends HospitalController
{
	private boolean loggedIn = false;
	private User user = null;
	private DomainObjectManager<UserIN> dom = new DomainObjectManager<UserIN>();
	public LoginController(Hospital hospital) throws InvalidHospitalException {
		super(hospital);
	}

	/**
	 * @return A collection of all users currently in the system.
	 */
	private Collection<UserIN> getAllUsers() {
		UserManager um = hospital.getUserManager();
		ArrayList<UserIN> users = new ArrayList<UserIN>();
		for (UserIN u : um.getAllUsers())
			users.add(u);
		return users;
	}
	public Collection<DomainObject<UserIN>> getAllUsers2()
	{
		return dom.transform(getAllUsers());
	}
	

	/**
	 * This method will login the user of this logincontroller.
	 * 
	 * @throws IllegalArgumentException
	 *             if (!isValidUser((User) user))
	 */
	private void logIn(UserIN user) throws IllegalArgumentException {
		if (!isValidUser((User) user))
			throw new IllegalArgumentException("The given user is null!");
		this.user = (User) user;
		loggedIn = true;
	}
	public void logIn(DomainObject<UserIN> user) throws IllegalArgumentException, Exception
	{
		logIn(dom.get(user));
	}

	/**
	 * This method will check if the given user is a valid user for this
	 * logincontroller.
	 * 
	 * @param u
	 *            The user.
	 * @return True if u != null;
	 */
	private boolean isValidUser(User u) {
		return !(u == null);
	}

	/**
	 * @return True if the user of this controller is logged in.
	 */
	public boolean loggedIn() {
		return loggedIn;
	}

	/**
	 * @return The user of this logincontroller.
	 */
	User getUser() {
		return this.user;
	}

	/**
	 * @return The user whom has been granted log on access.
	 */
	public UserIN getUserIN() {
		return user;
	}

	/**
	 * Equality test
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LoginController))
			return false;
		LoginController that = (LoginController) o;
		return this.loggedIn == that.loggedIn & this.user.equals(that.user);
	}
}