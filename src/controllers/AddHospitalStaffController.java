package controllers;

import java.util.ArrayList;
import java.util.Collection;
import system.Location;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;
import users.UserFactory;
import exceptions.InvalidDomainObjectException;
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
	private Collection<UserFactory> passedOut=new ArrayList<UserFactory>();
	public Collection<UserFactory> getFactories()
	{
		passedOut = new ArrayList<UserFactory>();
		return new ArrayList<UserFactory>(passedOut);
	}
	public void create(UserFactory fact) throws UserAlreadyExistsException, InvalidNameException, InvalidDomainObjectException 
	{
		if(!contains(fact))
			throw new InvalidDomainObjectException();
		this.hospital.getUserManager().createUser(fact);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
	/**
	 * Checks if the object that was passed out is the samne object as the objec passed out by the getfactories
	 * method
	 */
	
	private boolean contains(UserFactory f)
	{
		for(UserFactory factory:passedOut)
			if(f==factory)
				return true;
		return false;
	}
}
