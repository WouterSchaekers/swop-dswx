package controllers;

import static org.junit.Assert.assertTrue;
import machine.MachineBuilder;
import org.junit.Before;
import org.junit.Test;
import system.Hospital;
import system.Whereabouts;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;
import exceptions.InvalidCampusException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;

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
		hospital = new Hospital();
		loginController = new LoginController(hospital);
		hospital.addCampus("Campus 1");
		Whereabouts w = hospital.getCampus("Campus 1");
		nurse = new Nurse("jenny",w);
		doctor = new Doctor("jonathan",w);
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

	@Test
	public void test() throws InvalidLoginControllerException,
			InvalidLocationException, InvalidSerialException,
			InvalidHospitalException {
		loginController.logIn(hospitad);
		addHospitalEquipmentController = new AddHospitalEquipmentController(
				loginController);
		MachineBuilder m = hospital.getCampus("Campus 1").getMachinePool().getAllBuilders().iterator().next();
		addHospitalEquipmentController.createMachine(m, 3, "jozef", hospital
				);
		assertTrue(hospital.getCampus("Campus 1").getMachinePool().getAllMachines().size() == 1);
	}

}
