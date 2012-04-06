package users;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import controllers.interfaces.LocationIN;
import system.Campus;
import system.StandardHospitalBuilder;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidUserFactory;
import exceptions.UserAlreadyExistsException;

public class _UserTest
{
	Campus campus = new StandardHospitalBuilder().build().getAllCampuses().iterator().next();
	static UserManager usermanager;
	static UserManager withDoctor;

	@Before
	public void voi() throws InvalidNameException {
		usermanager = new UserManager("hospital admin");
		usermanager.addType(new NurseFactory());
		usermanager.addType(new WarehouseAdminFactory());
		withDoctor = new UserManager("hospital admin");
		withDoctor.addType(new NurseFactory());
		withDoctor.addType(new WarehouseAdminFactory());
		withDoctor.addType(new DoctorFactory());
	}
	@Test
	public void testFactories()
	{
		assertTrue(usermanager.getUserFactories().contains(new NurseFactory()));
		assertFalse(usermanager.getUserFactories().contains(new DoctorFactory()));
		assertTrue(withDoctor.getUserFactories().contains(new DoctorFactory()));
	}
	@Test(expected = InvalidUserFactory.class)
	public void factoryWrong() throws UserAlreadyExistsException, InvalidNameException, InvalidLocationException,
			InvalidUserFactory {
		DoctorFactory doc = new DoctorFactory();
		doc.setName("Name");
		doc.setLocation(campus);
		usermanager.createUser(doc);
	}

	@Test
	public void createDoctor() throws UserAlreadyExistsException, InvalidNameException, InvalidLocationException,
			InvalidUserFactory {
		DoctorFactory doc = new DoctorFactory();
		doc.setName("Name");
		doc.setLocation(campus);
		User user = withDoctor.createUser(doc);
		withDoctor.getAllUsers().contains(user);
	}

	@Test
	public void testNameEqual() throws UserAlreadyExistsException, InvalidNameException, InvalidLocationException,
			InvalidUserFactory {
		NurseFactory doc = new NurseFactory();
		doc.setName("Jeffry");
		doc.setLocation(campus);
		User user = usermanager.createUser(doc);
		assertTrue(user.getName().equals("Jeffry"));
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testUserAlreadyExists() throws UserAlreadyExistsException, InvalidNameException,
			InvalidLocationException, InvalidUserFactory {
		NurseFactory doc = new NurseFactory();
		doc.setName("Jeffry");
		doc.setLocation(campus);
		usermanager.createUser(doc);
		doc = new NurseFactory();
		doc.setName("Jeffry");
		doc.setLocation((LocationIN)campus);
		usermanager.createUser(doc);
	}

	@Test(expected = InvalidNameException.class)
	public void testInvalidName() throws UserAlreadyExistsException, InvalidNameException, InvalidLocationException,
			InvalidUserFactory {
		NurseFactory doc = new NurseFactory();
		doc.setName(null);
		doc.setLocation(campus);
		usermanager.createUser(doc);
	}

	@Test(expected = InvalidNameException.class)
	public void testInvalidName2() throws UserAlreadyExistsException, InvalidNameException, InvalidLocationException,
			InvalidUserFactory {
		NurseFactory doc = new NurseFactory();
		doc.setName("");
		doc.setLocation(campus);
		usermanager.createUser(doc);
	}

	@Test(expected = InvalidLocationException.class)
	public void testInvalidLocation() throws UserAlreadyExistsException, InvalidNameException,
			InvalidLocationException, InvalidUserFactory {
		NurseFactory doc = new NurseFactory();
		doc.setName("abra");
		doc.setLocation(null);
		usermanager.createUser(doc);

	}
}
