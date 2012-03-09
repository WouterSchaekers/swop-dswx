package controllers;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import system.Hospital;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;

public class _AdvanceTimeControllerTest
{
	Hospital hospital;
	LoginController loginController;
	AddHospitalEquipmentController addHospitalEquipmentController;
	User nurse;
	User Doctor;
	User hospitad;

	@Before
	public void setup() throws InvalidNameException, InvalidHospitalException {
		hospital = new Hospital();
		loginController = new LoginController(hospital);
		nurse = new Nurse("jenny");
		Doctor = new Doctor("jonathan");
		hospitad = new HospitalAdmin("asdfd");
	}

	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest1() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(nurse);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController);
	}

	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest2() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(Doctor);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController);
	}

	@Test
	public void authenticationTest3() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(hospitad);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController);
		assertTrue(addHospitalEquipmentController
				.isValidLoginController(loginController));
	}

	@Test
	public void test() throws InvalidLoginControllerException,
			InvalidLocationException, InvalidSerialException {
		// TODO TEST
	}
}