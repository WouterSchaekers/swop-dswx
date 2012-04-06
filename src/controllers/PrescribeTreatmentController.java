package controllers;

import java.util.Collection;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import treatment.TreatmentFactory;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;

/**
 * Use this controller to prescribe a treatment for a patient's diagnose.
 */
public class PrescribeTreatmentController extends NeedsLoginAndPatientFileController
{
	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to prescribe a
	 *            treatment for a patient.
	 * @param consultPatientFileController
	 *            The consult patient file controller of the user that wants to
	 *            prescribe a treatment.
	 * @throws InvalidLoginControllerException
	 *             If the given login controller does not belong to a doctor or
	 *             is invalid in any other way.
	 * @throws InvalidHospitalException
	 * @throws InvalidConsultPatientFileController
	 * @throws InvalidPatientFileException
	 *             If the patient file opened in the consult patient file
	 *             controller has been discharged.
	 * @see HospitalController
	 * @see NeedsLoginAndPatientFileController
	 */
	@controllers.PUBLICAPI
	public PrescribeTreatmentController(LoginController loginController,
			ConsultPatientFileController consultPatientFileController) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidConsultPatientFileController, InvalidPatientFileException {
		super(loginController, consultPatientFileController);
		if (consultPatientFileController.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged!");
	}

	/**
	 * Creates a new treatment from the given factory and adds it to the
	 * selected diagnose. Also tries to schedule the new treatment right away.
	 * 
	 * @param selected
	 *            The diagnose you want to prescribe a treatment for.
	 * @param treatmentFactory
	 *            The factory that can create the treatment the way you want it
	 *            to be.
	 * @return The date the created treatment has been scheduled. If the
	 *         treatment was not able to be scheduled right away, returns null.
	 * @throws InvalidDiagnoseException
	 *             If the selected Diagnose is not a valid diagnose object or is
	 *             not from the patient file that's currently been opened.
	 * @throws FactoryInstantiationException
	 *             If there was a problem while creating the treatment from the
	 *             given factory.
	 * @throws CanNeverBeScheduledException
	 *             If there is not enough staff available at the hospital to
	 *             carry out this treatment. Note that should this exception
	 *             occur, the system will forget you have ever tried to
	 *             prescribe it.
	 */
	@controllers.PUBLICAPI
	public HospitalDate addTreatment(DiagnoseIN selected, TreatmentFactory treatmentFactory)
			throws InvalidDiagnoseException, FactoryInstantiationException, CanNeverBeScheduledException {
		if (!(selected instanceof Diagnose))
			throw new InvalidDiagnoseException("The selected diagnose is invalid!");
		if (!getAllPossibleDiagnosis().contains(selected))
			throw new InvalidDiagnoseException("Trying to add a treatment to a diagnose that this doctor has not made!");
		treatmentFactory.setCreationDate(hospital.getTimeKeeper().getSystemTime());
		@SuppressWarnings("deprecation")
		Task<?> createdTreatment = hospital.getTaskManager().add(treatmentFactory.create());

		if (createdTreatment.isScheduled())
			return createdTreatment.getDate();
		return null;
	}

	/**
	 * @return The diagnosis made by the doctor, that created this controller,
	 *         that are in the patient file that is currently opened.
	 */
	@controllers.PUBLICAPI
	public Collection<DiagnoseIN> getAllPossibleDiagnosis() {
		return ((PatientFile) consultPatientFileController_.getPatientFile())
				.getDiagnosisFrom((Doctor) loginController_.getUser());
	}

	/**
	 * @return All factories with which you can create new treatments
	 */
	@controllers.PUBLICAPI
	public Collection<TreatmentFactory> getTreatmentFactories() {
		return hospital.getTreatments();
	}

	/**
	 * @return True if the given user is a doctor.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}
