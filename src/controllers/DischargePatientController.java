package controllers;

import patient.PatientFile;
import users.Doctor;
import users.User;
import exceptions.DischargePatientException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

/**
 * Use this controller to discharge a patient from the hospital.
 */
@controllers.PUBLICAPI
public class DischargePatientController extends
		NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 * 
	 * @throws InvalidPatientFileException
	 *             If the patient file that is currently opened has already been
	 *             discharged.
	 */
	@controllers.PUBLICAPI
	public DischargePatientController(LoginController lc,
			ConsultPatientFileController cpfc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(lc, cpfc);
		if (cpfc.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Trying to discharge a patient that already has been discharged!");
	}

	/**
	 * Discharges the patient whose file is currently opened.
	 * 
	 * @throws DischargePatientException
	 * @throws InvalidPatientFileException
	 */
	@controllers.PUBLICAPI
	public void dischargePatient() throws DischargePatientException,
			InvalidPatientFileException {
		hospital.getPatientFileManager().checkOut(
				(PatientFile) cpfc.getPatientFile());
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}