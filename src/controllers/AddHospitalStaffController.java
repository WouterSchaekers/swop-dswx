package controllers;

import java.util.Collection;
import java.util.LinkedList;
import users.HospitalAdmin;
import users.User;
import users.UserFactory;
import controllers.interfaces.CampusIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

/**
 * Use this controller to add new staff to the hospital.
 */
@controllers.PUBLICAPI
public class AddHospitalStaffController extends NeedsLoginController
{
	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user trying to add staff to the
	 *            hospital.
	 * @throws InvalidLoginControllerException
	 *             If the user who owns the given login controller is not a
	 *             hospital administrator or if the login controller is invalid
	 *             in another way.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginController
	 */
	@controllers.PUBLICAPI
	public AddHospitalStaffController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * Adds a new user to the hospital from the given user factory.
	 * 
	 * @param userFactory
	 *            The factory you used to initialise the settings of the user
	 *            you want to be added to this hospital.
	 * @throws UserAlreadyExistsException
	 *             If the user you're trying to create already exists (if their
	 *             name is already registered to another user).
	 * @throws InvalidNameException
	 *             If the name you've set in the factory is an invalid one.
	 * @throws InvalidLocationException
	 *             If the location you've set in the factory is an invalid one.
	 */
	@controllers.PUBLICAPI
	public void addStaff(UserFactory userFactory) throws UserAlreadyExistsException, InvalidNameException,
			InvalidLocationException {
		this.hospital.getUserManager().createUser(userFactory);
	}

	/**
	 * @return All factories that allow you to create users.
	 */
	@controllers.PUBLICAPI
	public Collection<UserFactory> getFactories() {
		return this.hospital.getUserManager().getUserFactories();
	}

	/**
	 * @return All locations in this hospital.
	 */
	@controllers.PUBLICAPI
	public Collection<CampusIN> getLocations() {
		return new LinkedList<CampusIN>(this.hospital.getAllCampuses());
	}

	/**
	 * True if the user to whom the given login controller belongs to is a
	 * hospital administrator.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
