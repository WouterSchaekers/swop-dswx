package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import system.Hospital;
import ui.UserFilter;
import users.User;
import users.UserManager;
import controllers.interfaces.CampusIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.HospitalIN;
import controllers.interfaces.NurseIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;

/**
 * This class will be used as a login controller. Each login controller will
 * remember what user they have logged in.
 */
@controllers.PUBLICAPI
public class LoginController extends HospitalController
{
	private boolean loggedIn = false;
	private User user = null;
	private CampusIN campus;

	/**
	 * Default constructor. Initialises a new login controller.
	 * 
	 * @param hospital
	 *            The hospital where this login controller is for.
	 * @throws InvalidHospitalException
	 *             If the given hospital is invalid for any reason.
	 * @see HospitalController
	 */
	@controllers.PUBLICAPI
	public LoginController(HospitalIN hospital) throws InvalidHospitalException {
		super((Hospital) hospital);
	}

	/**
	 * @return A collection of all users that are currently registered in the
	 *         system.
	 */
	@controllers.PUBLICAPI
	public Collection<UserIN> getAllUsers() {
		UserManager um = hospital.getUserManager();
		LinkedList<UserIN> users = new LinkedList<UserIN>();
		for (UserIN u : um.getAllUsers())
			users.add(u);
		return users;
	}

	/**
	 * Gets a specific nurse that works in this hospital.
	 * 
	 * @param name
	 *            The name of the nurse you're trying to find.
	 * @return The given nurse, or null if there is no such user.
	 */
	@controllers.PUBLICAPI
	public NurseIN getSpecificNurse(String name) {
		return UserFilter.SpecificNurseFilter(new LinkedList<UserIN>(hospital.getUserManager().getAllUsers()), name);
	}

	/**
	 * Gets a specific doctor that works in this hospital.
	 * 
	 * @param name
	 *            The name of the doctor you're trying to find.
	 * @return The given doctor, or null if there is no such user.
	 */
	@controllers.PUBLICAPI
	public DoctorIN getSpecificDoctor(String name) {
		return UserFilter.SpecificDoctorFilter(new LinkedList<UserIN>(hospital.getUserManager().getAllUsers()), name);
	}

	/**
	 * This method will log in the given user at the given location.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given user or campus are null.
	 */
	@controllers.PUBLICAPI
	public void logIn(UserIN user, CampusIN at) throws IllegalArgumentException {
		if (!isValidUser((User) user))
			throw new IllegalArgumentException("The given user is null!");
		if (!isValidCampus(at))
			throw new IllegalArgumentException("The given campus is null");
		this.user = (User) user;
		loggedIn = true;
		this.campus = at;
		return;
	}

	/**
	 * @return True if the given campus is not null.
	 */
	private boolean isValidCampus(CampusIN at) {
		return at != null;
	}

	/**
	 * @return True if the given user is not null.
	 */
	private boolean isValidUser(User u) {
		return !(u == null);
	}

	/**
	 * @return True if the user of this controller is logged in.
	 */
	@controllers.PUBLICAPI
	public boolean loggedIn() {
		return loggedIn;
	}

	/**
	 * !! ONLY FOR OTHER CONTROLLERS !!
	 * 
	 * @return The user of to whom this login controller belongs to.
	 */
	User getUser() {
		return this.user;
	}

	/**
	 * @return The user who has been granted access to log on.
	 */
	@controllers.PUBLICAPI
	public UserIN getUserIN() {
		return user;
	}

	/**
	 * Equality test.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LoginController))
			return false;
		LoginController that = (LoginController) o;
		return this.loggedIn == that.loggedIn & this.user.equals(that.user);
	}

	/**
	 * @return All locations in this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<CampusIN> getLocations() {
		return new ArrayList<CampusIN>(hospital.getAllCampuses());
	}

	/**
	 * @return The place where the user of this login controller is at.
	 */
	@controllers.PUBLICAPI
	public CampusIN getLocation() {
		return campus;
	}
}