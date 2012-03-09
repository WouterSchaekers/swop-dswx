package controllers;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import system.Hospital;
import users.Doctor;
import users.Nurse;
import users.UserManager;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class _PatientFileOpenControllerTest
{
	private Hospital data;
	private UserManager um;
	private ConsultPatientFileController pfoc;
	private LoginController lc;
	private Doctor d;
	private Nurse n;
	private UserIN u;
	Hospital hospital;

	@Before
	public void setUp() throws Exception {
		um = new UserManager();
		hospital = new Hospital();
	}

	@Test
	public void testPatientFileOpenControllerSucces()
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException, InvalidLoginControllerException, InvalidHospitalException {
		lc = new LoginController(data);
		d = um.createAndAddDoctor("Jef");
		u = d;
		lc.logIn(u);
		pfoc = new ConsultPatientFileController(lc);
	}

	@Test(expected = InvalidLoginControllerException.class)
	public void testGetLoginControllerFail()
			throws InvalidLoginControllerException, InvalidHospitalException {
		lc = new LoginController(data);
		pfoc = new ConsultPatientFileController(data, null);
	}

	@Test(expected = InvalidLoginControllerException.class)
	public void testGetLoginControllerFail2()
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException, InvalidLoginControllerException, InvalidHospitalException {
		lc = new LoginController(data);
		n = um.createAndAddNurse("Sandrien");
		u = n;
		pfoc = new ConsultPatientFileController(data, lc);
	}

	@Test
	public void testValidLoginControllerSucces()
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException, InvalidLoginControllerException, InvalidHospitalException {
		lc = new LoginController(data);
		d = um.createAndAddDoctor("Jef");
		u = d;
		lc.logIn(u);
		pfoc = new ConsultPatientFileController(data, lc);
		assertTrue(pfoc.isValidLoginController(lc));
	}

	@Test(expected = InvalidLoginControllerException.class)
	public void testValidLoginControllerFail()
			throws UserAlreadyExistsException, InvalidNameException,
			InvalidTimeSlotException, InvalidLoginControllerException, InvalidHospitalException {
		lc = new LoginController(data);
		n = um.createAndAddNurse("Margo");
		u = n;
		lc.logIn(u);
		pfoc = new ConsultPatientFileController(data, lc);
	}
}
