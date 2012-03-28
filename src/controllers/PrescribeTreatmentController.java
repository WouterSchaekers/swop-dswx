package controllers;

import java.util.Collection;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.tasks.Task;
import treatment.Treatment;
import treatment.TreatmentFactory;
import treatment.Treatments;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidAmountException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTreatmentException;

/**
 * Use this controller to prescribe a treatment for a patient's diagnose.
 */
public class PrescribeTreatmentController extends
		NeedsLoginAndPatientFileController
{

	/**
	 * Default constructor.
	 */
	public PrescribeTreatmentController(LoginController lc,
			ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidPatientFileOpenController, InvalidPatientFileException {
		super(lc, pfoc);
		if (pfoc.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged!");
	}

	/**
	 * Adds a treatment to the selected Diagnose.
	 */
	@SuppressWarnings("unchecked")
	public void addTreatment(DiagnoseIN selected,
			TreatmentFactory treatmentFactory) throws InvalidDiagnoseException,
			InvalidTreatmentException, FactoryInstantiationException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidSchedulingRequestException, CanNeverBeScheduledException {
		if (!getAllPossibleDiagnosis().contains(selected))
			throw new InvalidDiagnoseException(
					"Trying to add a treatment to a diagnose that this doctor has not made!");
		if (!(selected instanceof Diagnose))
			throw new IllegalArgumentException(
					"The selected diagnose is invalid!");
		// TODO: zelfde als bij patientfile en medical test... hoe kan een
		// diagnose zijn treatmenttasks bijhouden?
		// De implementatie is zeker voor kritiek vatbaar. 't Is dan ook maar
		// een concept he ;)
		Treatment treatment = ((Diagnose) selected)
				.createTreatment(treatmentFactory);
		((Diagnose) selected)
				.addTreatmentTask((Task<? extends Treatment>) hospital
						.getTaskManager().add(treatment));
	}

	/**
	 * @return The diagnosis made by the doctor to whom this controller belongs
	 *         to.
	 */
	public Collection<DiagnoseIN> getAllPossibleDiagnosis() {
		return ((PatientFile) cpfc.getPatientFile())
				.getDiagnosisFrom((Doctor) lc.getUser());
	}

	/**
	 * @return All factories with which new treatments can be created
	 */
	public Collection<TreatmentFactory> getTreatmentFactories() {
		return new Treatments().factories();
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}
