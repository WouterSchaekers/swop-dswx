package controllers;

import system.Whereabouts;
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
		Nurse n = new Nurse(name);
		Whereabouts w = this.hospital.getCampus(location);
		this.hospital.getUserManager().addUser(n, w);
	}

	public void addDoctor(String name, String location)
			throws UserAlreadyExistsException, InvalidNameException {
		Doctor d = new Doctor(name);
		Whereabouts w = this.hospital.getCampus(location);
		this.hospital.getUserManager().addUser(d, w);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

	//TODO: use
//	public class Abra<T>{
//	private T val;
//
//	public Abra(T t)
//	{
//		this.val = t;
//	}
//	public int getKey()
//	{
//		return campus;
//		
//	}
//	 T get()
//	{
//		return val;
//	}
//}
//public Collection<Abra<UserType>> getTypes()
//{
//	return null;
//	
//}
}
