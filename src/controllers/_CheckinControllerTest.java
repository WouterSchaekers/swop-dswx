package controllers;

import static org.junit.Assert.assertTrue;
import machine.MachineBuilder;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFile;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;
import system.HospitalState;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;

public class _CheckinControllerTest
{
	HospitalState hospital;
	LoginController loginController;
	CheckinController checkinController;
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
		loginController.logIn(doctor);
		checkinController = new CheckinController(
				loginController, hospital);
	}
	
	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest2() throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		loginController.logIn(hospitad);
		checkinController = new CheckinController(
				loginController, hospital);
	}
	
	@Test
	public void authenticationTest3() throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		loginController.logIn(nurse);
		checkinController = new CheckinController(
				loginController, hospital);
	}
	
	@Test
	public void test() throws InvalidLoginControllerException,
			InvalidLocationException, InvalidSerialException,
			InvalidHospitalStateException, InvalidNameException {
		loginController.logIn(nurse);
		checkinController = new CheckinController(loginController, hospital);
		checkinController.signUpNewPatient("Thibault");
		assertTrue(hospital.getPatientFileManager().amountOfActivePatients() == 1);
		PatientFile thibault = hospital.getPatientFileManager().getPatientFileFrom("Thibault");
		checkinController.checkIn(thibault);
		assertTrue(!thibault.isDischarged());
	}
}