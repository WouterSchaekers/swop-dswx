package controllers;

import system.CampusPreference;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

public class AddHospitalStaffController extends NeedsLoginController
{
	public AddHospitalStaffController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	/**
	 * This method allows you to add a user you created with the factories to be
	 * added to the system.
	 * 
	 * @param u
	 *            The user to add to the system
	 * @throws UserAlreadyExistsException
	 */
	private void addStaffMember(User u) throws UserAlreadyExistsException {
		hospital.getUserManager().addUser(u);
	}

	public void addNurse(String name) throws UserAlreadyExistsException,
			InvalidNameException {
		this.hospital.getUserManager().createAndAddNurse(name);
	}

	public void addDoctor(String name, CampusPreference pref)
			throws UserAlreadyExistsException, InvalidNameException {
		// TODO: fix pref
		this.hospital.getUserManager().createAndAddDoctor(name);
	}

	public void addHospitalAdmin(String name)
			throws UserAlreadyExistsException, InvalidNameException {
		this.hospital.getUserManager().createAndAddHospitalAdmin(name);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
