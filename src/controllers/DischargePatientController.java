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
public class DischargePatientController extends NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The login controller of the user that wants to discharge a
	 *            patient from this hospital.
	 * @param cpfc
	 *            The consult patient file controller of the user that wants to
	 *            discharge a patient from this hospital.
	 * @throws InvalidLoginControllerException
	 *             If the user to whom the given login controller belongs to is
	 *             not a doctor or is invalid in any other way.
	 * @throws InvalidPatientFileOpenController
	 *             If the given consult patient file controller is invalid in
	 *             any way.
	 * @throws InvalidPatientFileException
	 *             If the patient of the patient file that is stored in the
	 *             consult patient file controller is already discharged.
	 * @throws InvalidHospitalException
	 * @see HospitalController
	 * @see NeedsLoginAndPatientFileController
	 */
	@controllers.PUBLICAPI
	public DischargePatientController(LoginController lc, ConsultPatientFileController cpfc)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController,
			InvalidPatientFileException {
		super(lc, cpfc);
		if (cpfc.getPatientFile().isDischarged())
			throw new InvalidPatientFileException("Trying to discharge a patient that already has been discharged!");
	}

	/**
	 * Discharges the patient whose file is currently opened.
	 * 
	 * @throws DischargePatientException
	 *             If the patient file that is currently open cannot be
	 *             discharged for any reason (open treatments etc...)
	 */
	@controllers.PUBLICAPI
	public void dischargePatient() throws DischargePatientException {
		try {
			hospital.getPatientFileManager().checkOut((PatientFile) consultPatientFileController_.getPatientFile());
		} catch (InvalidPatientFileException e) {
			throw new Error(e);
		}
	}

	/**
	 * @return True if the given user is a doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}