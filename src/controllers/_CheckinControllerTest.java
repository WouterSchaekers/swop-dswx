package controllers;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFile;
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

public class _CheckinControllerTest
{
	Hospital hospital;
	LoginController loginController;
	CheckinController checkinController;
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
		loginController.logIn(doctor);
		checkinController = new CheckinController(
				loginController);
	}
	
	@Test(expected = InvalidLoginControllerException.class)
	public void authenticationTest2() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(hospitad);
		checkinController = new CheckinController(
				loginController);
	}
	
	@Test
	public void authenticationTest3() throws InvalidLoginControllerException,
			InvalidHospitalException {
		loginController.logIn(nurse);
		checkinController = new CheckinController(
				loginController);
	}
	
	@Test
	public void test() throws InvalidLoginControllerException,
			InvalidLocationException, InvalidSerialException,
			InvalidHospitalException, InvalidNameException {
		loginController.logIn(nurse);
		checkinController = new CheckinController(loginController);
		checkinController.signUpNewPatient("Thibault");
		assertTrue(hospital.getPatientFileManager().amountOfActivePatients() == 1);
		PatientFile thibault = hospital.getPatientFileManager().getPatientFileFrom("Thibault");
		checkinController.checkIn(thibault);
		assertTrue(!thibault.isDischarged());
	}
}