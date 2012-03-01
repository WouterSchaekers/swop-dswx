package controllers;

import static org.junit.Assert.assertTrue;
import machine.MachineBuilder;
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

public class _AddHospitalEquipmentTest
{

	HospitalState hospital;
	LoginController loginController;
	AddHospitalEquipmentController addHospitalEquipmentController;
	User nurse;
	User Doctor;
	User hospitad;

	@Before
	public void setup() throws InvalidNameException {
		hospital = new HospitalState();
		loginController = new LoginController(hospital);
		nurse = new Nurse("jenny");
		Doctor = new Doctor("jonathan");
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
		loginController.logIn(Doctor);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController, hospital);
	}

	@Test
	public void authenticationTest3() throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		loginController.logIn(hospitad);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController, hospital);
		assertTrue(addHospitalEquipmentController
				.isValidLoginController(loginController));
	}

	@Test
	public void test() throws InvalidLoginControllerException,
			InvalidLocationException, InvalidSerialException,
			InvalidHospitalStateException {
		loginController.logIn(hospitad);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController, hospital);
		MachineBuilder m = null;
		for (MachineBuilder b : hospital.getMachinePool().getAllBuilders()) {
			m = b;
			break;
		}
		addHospitalEquipmentController.createMachine(m, 3, "jozef"
				);
		assertTrue(hospital.getMachinePool().getAllMachines().size() == 1);
	}

}
