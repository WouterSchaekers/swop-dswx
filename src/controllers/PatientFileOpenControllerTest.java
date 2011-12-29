package controllers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controllers.interfaces.UserIN;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;
import patient.PatientFileManager;
import users.Doctor;
import users.UserManager;
import users.Nurse;

public class PatientFileOpenControllerTest
{
	private DataPasser data;
	private UserManager um;
	private PatientFileManager pfm;
	private PatientFileOpenController pfoc;
	private LoginController lc;
	private Doctor d;
	private Nurse n;
	private UserIN u;

	@Before
	public void setUp() throws Exception {
		um = new UserManager();
		pfm = new PatientFileManager();
		data = new DataPasser(um, pfm, null,null, null);

	}

	@Test
	public void testPatientFileOpenControllerSucces()
			throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException {
		lc = new LoginController(data);
		d = um.createDoctor("Jef");
		u = d;
		lc.logIn(u);
		pfoc = new PatientFileOpenController(data, lc);
	}

	@Test(
			expected = NullPointerException.class)
	public void testGetLoginControllerFail() {
		lc = new LoginController(data);
		pfoc = new PatientFileOpenController(data, null);
	}

	@Test(
			expected = NullPointerException.class)
	public void testGetLoginControllerFail2() throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException {
		lc = new LoginController(data);
		n = um.createNurse("Sandrien");
		u = n;
		pfoc = new PatientFileOpenController(data, lc);
	}

	@Test
	public void testValidLoginControllerSucces()
			throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException {
		lc = new LoginController(data);
		d = um.createDoctor("Jef");
		u = d;
		lc.logIn(u);
		pfoc = new PatientFileOpenController(data, lc);
		assertTrue(pfoc.validLoginController(lc));
	}

	@Test(
			expected = IllegalArgumentException.class)
	public void testValidLoginControllerFail()
			throws UserAlreadyExistsException, InvalidNameException, InvalidTimeSlotException {
		lc = new LoginController(data);
		n = um.createNurse("Margo");
		u = n;
		lc.logIn(u);
		pfoc = new PatientFileOpenController(data, lc);
	}
}
