package controllers;

import java.util.Collection;
import java.util.LinkedList;
import patient.PatientFile;
import ui.UserFilter;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidConsultPatientFileController;

/**
 * Use this controller to enter the diagnose of a patient.
 */
@controllers.PUBLICAPI
public class EnterDiagnoseController extends NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to enter a
	 *            diagnose for a patient file.
	 * @param consultPatientFileController
	 *            The consult patient file controller that the user who wants to
	 *            enter a new diagnose has created already.
	 * @throws InvalidLoginControllerException
	 *             If the user to whom the given login controller belongs to is
	 *             not a doctor or if the controller is invalid in any other
	 *             way.
	 * @throws InvalidHospitalException
	 * @throws InvalidConsultPatientFileController
	 * @see HospitalController
	 * @see NeedsLoginAndPatientFileController
	 */
	@controllers.PUBLICAPI
	public EnterDiagnoseController(LoginController loginController,
			ConsultPatientFileController consultPatientFileController) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidConsultPatientFileController {
		super(loginController, consultPatientFileController);
	}

	/**
	 * Registers a new diagnose in the patient file that the user who created
	 * this controller has currently opened. Use this method if you do not
	 * require a second opinion for the created diagnose.
	 * 
	 * @param complaints
	 *            The complaints of the patient who has been diagnosed.
	 * @param diagnose
	 *            The diagnose you're trying to register.
	 * @return The diagnose that was created.
	 * @throws InvalidDiagnoseException
	 *             If the given diagnose was invalid.
	 * @throws InvalidComplaintsException
	 *             If the given complaints are invalid.
	 * @throws IllegalAccessException 
	 */
	@controllers.PUBLICAPI
	public DiagnoseIN enterDiagnose(String complaints, String diagnose) throws InvalidDiagnoseException,
			InvalidComplaintsException, IllegalAccessException {
		try {
			return ((PatientFile) consultPatientFileController_.getPatientFile()).createDiagnose(complaints, diagnose,
					(Doctor) loginController_.getUser(), null);
		} catch (InvalidDoctorException e) {
			throw new Error(e);
		}
	}

	/**
	 * Registers a new diagnose in the patient file that is currently opened and
	 * marks it for second opinion right after createion.
	 * 
	 * @param diagnose
	 *            The diagnose that was made by the doctor.
	 * @param complaints
	 *            The complaints the patient had when walking in.
	 * @param secondOpinionDoctor
	 *            The doctor that should give his/her second opinion on the
	 *            diagnose you're trying to register.
	 * @return The created diagnose.
	 * @throws InvalidDiagnoseException
	 *             If the given diagnose is invalid.
	 * @throws InvalidDoctorException
	 *             If the doctor you require second opinion from is invalid.
	 * @throws InvalidComplaintsException
	 *             If the given complaints are invalid.
	 * @throws IllegalAccessException 
	 */
	@controllers.PUBLICAPI
	public DiagnoseIN enterDiagnoseWithSecondOpinion(String diagnose, String complaints, DoctorIN secondOpinionDoctor)
			throws InvalidDiagnoseException, InvalidComplaintsException, IllegalAccessException {
		try {
			return ((PatientFile) consultPatientFileController_.getPatientFile()).createDiagnose(complaints, diagnose,
					(Doctor) loginController_.getUser(), (Doctor) secondOpinionDoctor);
		} catch (InvalidDoctorException e) {
			throw new Error(e);
		}
	}

	/**
	 * @return All doctors working in this hospital.
	 */
	public Collection<DoctorIN> getAllDoctors() {
		return UserFilter.DoctorFilter(new LinkedList<UserIN>(hospital.getUserManager().getAllUsers()));
	}

	/**
	 * @return true if the given user is a doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}