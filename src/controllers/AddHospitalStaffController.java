package controllers;

import system.CampusPreference;
import system.Hospital;
import users.HospitalAdmin;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

public class AddHospitalStaffController extends NeedsLoginController
{
	public AddHospitalStaffController(LoginController loginController,
			Hospital hospital) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, loginController);
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
		this.addStaffMember(this.hospital.getUserManager().createNurse(name));
	}

	public void addDoctor(String name, CampusPreference pref)
			throws UserAlreadyExistsException, InvalidNameException {
		// TODO: fix pref
		this.addStaffMember(this.hospital.getUserManager().createDoctor(name));
	}

	public void addHospitalAdmin(String name)
			throws UserAlreadyExistsException, InvalidNameException {
		this.hospital.getUserManager().createHospitalAdmin(name);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
