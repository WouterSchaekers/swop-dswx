package controllers;

import java.util.Collection;
import patient.Diagnose;
import patient.PatientFile;
import scheduler.HospitalDate;
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

/**
 * Use this controller to prescribe a treatment for a patient's diagnose.
 */
public class PrescribeTreatmentController extends NeedsLoginAndPatientFileController
{
	public PrescribeTreatmentController(LoginController lc, ConsultPatientFileController pfoc)
			throws InvalidLoginControllerException, InvalidHospitalException, InvalidPatientFileOpenController,
			InvalidPatientFileException {
		super(lc, pfoc);
		if (pfoc.getPatientFile().isDischarged())
			throw new InvalidPatientFileException(
					"Invalid patient file given to create medical test from: patient is discharged!");
	}

	/**
	 * Adds a treatment to the selected Diagnose.
	 * 
	 * @return The date the treatment will take place, if it was able to be
	 *         scheduled straight away.
	 * @throws InvalidDiagnoseException
	 * @throws InvalidHospitalDateException
	 * @throws InvalidAmountException
	 * @throws FactoryInstantiationException
	 * @throws CanNeverBeScheduledException
	 */
	public HospitalDate addTreatment(DiagnoseIN selected, TreatmentFactory treatmentFactory)
			throws InvalidDiagnoseException, FactoryInstantiationException, CanNeverBeScheduledException {
		if (!(selected instanceof Diagnose))
			throw new InvalidDiagnoseException("The selected diagnose is invalid!");
		if (!getAllPossibleDiagnosis().contains(selected))
			throw new InvalidDiagnoseException("Trying to add a treatment to a diagnose that this doctor has not made!");
		
		Treatment treatment = ((Diagnose) selected).createTreatment(treatmentFactory);
		Task<?> createdTreatment = hospital.getTaskManager().add(treatment);

		// attempt to schedule and return date if it worked.
		hospital.getTaskManager().update(createdTreatment, null);
		if (createdTreatment.isScheduled())
			return createdTreatment.getDate();
		return null;
	}

	/**
	 * @return The diagnosis made by the doctor to whom this controller belongs
	 *         to.
	 */
	public Collection<DiagnoseIN> getAllPossibleDiagnosis() {
		return ((PatientFile) cpfc.getPatientFile()).getDiagnosisFrom((Doctor) lc.getUser());
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
