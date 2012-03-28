package controllers;

import java.util.Collection;
import medicaltest.MedicalTestFactory;
import medicaltest.MedicalTests;
import scheduler.HospitalDate;
import users.Doctor;
import users.User;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use to order medical tests for patients.
 */
public class OrderMedicalTestController extends	NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 */
	public OrderMedicalTestController(LoginController lc,
			ConsultPatientFileController cpf)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(lc, cpf);
		if (cpf.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged");
	}

	/**
	 * Use to allow the user to select the factory from which he/she would like
	 * to create a medical test from.
	 */
	public Collection<MedicalTestFactory> getMedicalTestFactories()
			throws InvalidLoginControllerException,
			InvalidPatientFileException, InvalidPatientFileOpenController {
		return new MedicalTests().factories();
	}
	
	/**
	 * 
	 * @param medicalTestFactory
	 * @return
	 */
	public HospitalDate addMedicaltest(MedicalTestFactory medicalTestFactory) {
		// TODO: implement
		return null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}