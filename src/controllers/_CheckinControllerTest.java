package controllers;

import static org.junit.Assert.assertTrue;
import machine.MachineBuilder;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFile;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;
import system.Hospital;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.User;

public class _CheckinControllerTest
{
	Hospital hospital;
	LoginController loginController;
	CheckinController checkinController;
	User nurse;
	User doctor;
	User hospitad;
	
	@Before
	public void setup() throws InvalidNameException, InvalidHospitalException {
		hospital = new Hospital();
		loginController = new LoginController(hospital);
		nurse = new Nurse("jenny");
		doctor = new Doctor("jonathan");
		hospitad = new HospitalAdmin("asdfd");
	}
	
	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest1() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(doctor);
		checkinController = new CheckinController(
				loginController, hospital);
	}
	
	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest2() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(hospitad);
		checkinController = new CheckinController(
				loginController, hospital);
	}
	
	@Test
	public void authenticationTest3() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(nurse);
		checkinController = new CheckinController(
				loginController, hospital);
	}
	
	@Test
	public void test() throws InvalidLoginControllerException,
			InvalidLocationException, InvalidSerialException,
			InvalidHospitalException, InvalidNameException {
		loginController.logIn(nurse);
		checkinController = new CheckinController(loginController, hospital);
		checkinController.signUpNewPatient("Thibault");
		assertTrue(hospital.getPatientFileManager().amountOfActivePatients() == 1);
		PatientFile thibault = hospital.getPatientFileManager().getPatientFileFrom("Thibault");
		checkinController.checkIn(thibault);
		assertTrue(!thibault.isDischarged());
	}
}