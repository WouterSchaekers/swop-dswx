package controllers;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import system.HospitalState;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;

public class _ApproveDiagnosisControllerTest
{
	HospitalState hospital;
	LoginController loginController;
	AddHospitalEquipmentController addHospitalEquipmentController;
	User nurse;
	User doctor;
	User hospitad;

	@Before
	public void setup() throws InvalidNameException {
		hospital = new HospitalState();
		loginController = new LoginController(hospital);
		nurse = new Nurse("jenny");
		doctor = new Doctor("jonathan");
		hospitad = new HospitalAdmin("asdfd");
	}

	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest1() throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		loginController.logIn(nurse);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController, hospital);
	}

	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest2() throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		loginController.logIn(hospitad);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController, hospital);
	}

	@Test
	public void authenticationTest3() throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		loginController.logIn(doctor);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController, hospital);
		assertTrue(addHospitalEquipmentController
				.isValidLoginController(loginController));
	}

	@Test
	public void test() throws InvalidLoginControllerException,
			InvalidLocationException, InvalidSerialException {
		// TODO TEST
	}
}