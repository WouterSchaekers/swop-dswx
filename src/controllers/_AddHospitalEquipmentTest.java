package controllers;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import system.Hospital;
import users.User;
import exceptions.InvalidCampusException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;

public class _AddHospitalEquipmentTest
{

	Hospital hospital;
	LoginController loginController;
	AddHospitalEquipmentController addHospitalEquipmentController;
	User nurse;
	User doctor;
	User hospitad;

	@Before
	public void setup() throws InvalidNameException, InvalidHospitalException, InvalidCampusException {
	//hospitad = new HospitalAdmin("asdfd");
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
		loginController.logIn(doctor);
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
}
