package controllers;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import patient.PatientFileManager;
import scheduler.Scheduler;
import users.*;

public class LoginControllerTests
{
	UserManager um;
	LoginController lc;
	DataPasser d;
	PatientFileManager p;
	Scheduler s;
	
	@Before
	public void setUp() {
		um = new UserManager();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void creationFail() throws IllegalArgumentException {
		lc = new LoginController(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creationFail2() throws IllegalArgumentException {
		d = null;
		lc = new LoginController(d);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void creationFail3() {
		d = new DataPasser(null,null,null);
		lc = new LoginController(d);
	}
	
	@Test
	public void creationSuccess1() {
		p = new PatientFileManager();
		s = new Scheduler(um, null);
		d = new DataPasser(um,p,s);
		lc = new LoginController(d);
	}
	
	@Test
	public void creationSuccess2() {
		s = new Scheduler(um, null);
		d = new DataPasser(um,null,s);
		lc = new LoginController(d);
	}
	
	@Test
	public void creationSuccess3() {
		p = new PatientFileManager();
		d = new DataPasser(um,p,null);
		lc = new LoginController(d);
	}
	
	@Test
	public void creationSuccess4() {
		d = new DataPasser(um,null,null);
		lc = new LoginController(d);
	}
	
	@Test
	public void getAllUsersSuccess() throws UserAlreadyExistsException {
		ArrayList<User> users = new ArrayList<User>(4);
		users.add(um.CreateDoctor("Fred"));
		users.add(um.CreateDoctor("Frits"));
		users.add(um.CreateNurse("Jan"));
		users.add(um.CreateNurse("Gert"));
		d = new DataPasser(um,null,null);
		lc = new LoginController(d);
		
		ArrayList<DTOUser> col = (ArrayList<DTOUser>) lc.getAllUsers();
		Collection<String> names = new ArrayList<String>();
		
		for (int i = 0; i < 4; i++)
			names.add(col.remove(0).getName());
	
		
		for(int i = 0; i < 4; i++)
			assertTrue(names.contains(users.remove(0).getName()));
		
				
	}
	
	@Test
	public void getAllUsersFailure() throws UserAlreadyExistsException {
		ArrayList<User> users = new ArrayList<User>(4);
		users.add(um.CreateDoctor("Fred"));
		users.add(um.CreateDoctor("Frits"));
		users.add(um.CreateNurse("Jan"));
		users.add(new Nurse("Gert"));
		d = new DataPasser(um,null,null);
		lc = new LoginController(d);

		ArrayList<DTOUser> col = (ArrayList<DTOUser>) lc.getAllUsers();
		Collection<String> names = new ArrayList<String>();
		Collection<String> usernames = new ArrayList<String>();
		
		for(DTOUser s:col)
			names.add(s.getName());
	
		for(User s:users)
			usernames.add(s.getName());
	
		for(String s: usernames)
			assertTrue(names.contains(s) || s.equals("Gert"));
				
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loginFail() {
		d = new DataPasser(um,null,null);
		lc = new LoginController(d);
		lc.logIn(null);
	}
	
	@Test
	public void loginSuccess() {
		d = new DataPasser(um,null,null);
		lc = new LoginController(d);
		Doctor doc = new Doctor("Jef");
		DTOUser u = new DTOUser(doc);
		lc.logIn(u);
		assertTrue(lc.loggedIn());
		assertTrue(lc.getUser().equals(doc));
		assertTrue(lc.getUserDTO().equals(u));
	}
	
}
