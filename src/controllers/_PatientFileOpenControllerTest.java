package controllers;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import system.HospitalState;
import users.Doctor;
import users.Nurse;
import users.UserManager;
import controllers.interfaces.UserIN;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class _PatientFileOpenControllerTest
{
	private HospitalState data;
	private UserManager um;
	private PatientFileOpenController pfoc;
	private LoginController lc;
	private Doctor d;
	private Nurse n;
	private UserIN u;
	HospitalState hospital;

	@Before
	public void setUp() throws Exception {
		um = new UserManager();
		hospital = new HospitalState();
	}

	@Test
	public void testPatientFileOpenControllerSucces()
			throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLoginControllerException {
		lc = new LoginController(data);
		d = um.createDoctor("Jef");
		u = d;
		lc.logIn(u);
		pfoc = new PatientFileOpenController(data, lc);
	}

	@Test(
			expected = InvalidLoginControllerException.class)
	public void testGetLoginControllerFail() throws InvalidLoginControllerException {
		lc = new LoginController(data);
		pfoc = new PatientFileOpenController(data, null);
	}

	@Test(
			expected = InvalidLoginControllerException.class)
	public void testGetLoginControllerFail2() throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLoginControllerException {
		lc = new LoginController(data);
		n = um.createNurse("Sandrien");
		u = n;
		pfoc = new PatientFileOpenController(data, lc);
	}

	@Test
	public void testValidLoginControllerSucces()
			throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLoginControllerException {
		lc = new LoginController(data);
		d = um.createDoctor("Jef");
		u = d;
		lc.logIn(u);
		pfoc = new PatientFileOpenController(data, lc);
		assertTrue(pfoc.isValidLoginController(lc));
	}

	@Test(
			expected = InvalidLoginControllerException.class)
	public void testValidLoginControllerFail()
			throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException, InvalidLoginControllerException {
		lc = new LoginController(data);
		n = um.createNurse("Margo");
		u = n;
		lc.logIn(u);
		pfoc = new PatientFileOpenController(data, lc);
	}
}
