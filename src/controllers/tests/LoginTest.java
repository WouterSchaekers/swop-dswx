package controllers.tests;

import static org.junit.Assert.*;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import controllers.LoginController;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;
import system.Campus;
import system.ExtendedStandardHospitalBuilder;
import system.Hospital;
import users.Doctor;
import users.Nurse;
import users.User;

public class LoginTest
{
	Hospital hospital ;
	Campus theCampus;
	LoginController logincontroller;
	@Before
	public void setUpHospital() throws InvalidHospitalException
	{
		hospital = new ExtendedStandardHospitalBuilder().build();
		logincontroller = new LoginController(hospital);
		theCampus = hospital.getAllCampuses().iterator().next();
	}
	@Test
	public void testLogin()
	{
		UserIN doctor = select(logincontroller.getAllUsers(),Doctor.class,"jonathan");
		UserIN nurse = select(logincontroller.getAllUsers(),Nurse.class,"jenny");
		assertTrue(nurse!=null);
		assertTrue(doctor!=null);
		logincontroller.logIn(doctor,theCampus);
		assertTrue((logincontroller.getLocation()==theCampus));
		assertTrue((logincontroller.getUserIN()==doctor));
		assertTrue(logincontroller.getAllUsers().contains(doctor));
		assertTrue(logincontroller.loggedIn());
		assertTrue(logincontroller.equals(logincontroller));
		assertTrue(logincontroller.getSpecificDoctor("jonathan").equals(doctor));
		assertTrue(logincontroller.getSpecificNurse("jenny").equals(nurse));
		assertTrue(logincontroller.getLocations().contains(theCampus));
		assertFalse(logincontroller.equals(null));
	}
	@Test
	public void failLogin()
	{
		UserIN doctor = select(logincontroller.getAllUsers(),Doctor.class,"jonathan");
		try{
		logincontroller.logIn(doctor, null);
		}catch(IllegalArgumentException e)
		{
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
	@Test
	public void failLogin2()
	{
		try{
		logincontroller.logIn(null,this.theCampus);
		}catch(IllegalArgumentException e)
		{
			assertTrue(true);
			return;
		}
		assertTrue(false);
	}
	private UserIN select(Collection<UserIN> allUsers, Class<? extends User> class1, String string) {
		for(UserIN user:allUsers)
			if(user.getClass().equals(class1)&&user.getName().equals(string))
				return user;
		return null;
	}
}
