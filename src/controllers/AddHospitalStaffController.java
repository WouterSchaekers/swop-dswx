package controllers;

import system.Location;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
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

	public void addNurse(String name, String location) throws UserAlreadyExistsException,
			InvalidNameException {
		Location w = this.hospital.getCampus(location);
		Nurse n = new Nurse(name,w);
		this.hospital.getUserManager().addUser(n, w);
	}

	public void addDoctor(String name, String location)
			throws UserAlreadyExistsException, InvalidNameException {
		Location w = this.hospital.getCampus(location);
		Doctor d = new Doctor(name, w);
		this.hospital.getUserManager().addUser(d, w);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}
