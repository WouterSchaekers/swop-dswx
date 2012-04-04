package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import medicaltest.MedicalTest;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import treatment.Treatment;
import users.Doctor;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.PatientIN;
import controllers.interfaces.TaskIN;
import exceptions.DischargePatientException;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;

/**
 * This class represents the patient file of a patient.
 */
public class PatientFile implements PatientFileIN
{
	/**
	 * All the Diagnosis for this patient.
	 */
	private Collection<Diagnose> diagnosis = new ArrayList<Diagnose>();
	private boolean discharged = false;
	private Collection<Task<? extends TaskDescription>> medicaltests = new LinkedList<Task<? extends TaskDescription>>();
	private Patient patient_;

	/**
	 * Default Constructor. Creates empty patient file with a name.
	 * 
	 * @param patient
	 *            The name of the patient to whom this patient file belongs to.
	 * @throws InvalidNameException
	 *             if(!isValidName(patientname))
	 */
	PatientFile(Patient patient) throws InvalidNameException {
		this.patient_ = patient;
	}

	/**
	 * This method will add a Diagnose to this PatientFile.
	 * 
	 * @param d
	 *            The Diagnose to add.
	 * @throws InvalidDiagnoseException
	 *             if(!isValidDiagnose(d))
	 */
	private void addDiagnose(Diagnose d) throws InvalidDiagnoseException {
		if (!isValidDiagnose(d))
			throw new InvalidDiagnoseException("The given Diagnose is not a valid!");
		this.diagnosis.add(d);
	}

	/**
	 * USE THIS METHOD ONLY IN THE DOMAIN LAYER!!
	 */
	public void addMedicalTest(Task<? extends MedicalTest> medicalTest) {
		if (!isValidMedicalTest(medicalTest))
			throw new IllegalArgumentException(medicalTest + " is not valid!");
		this.medicaltests.add(medicalTest);
	}

	/**
	 * @return True if this patient is ready to be discharged.
	 */
	private boolean canBeDischarged() {
		for (Diagnose d : diagnosis) {
			if (d.isMarkedForSecOp())
				return false;
			for (Task<? extends Treatment> t : d.getTreatments())
				if (!t.isFinished())
					return false;
		}
		for (Task<? extends TaskDescription> m : medicaltests)
			if (!m.isFinished())
				return false;
		return true;
	}

	/**
	 * Checks this patient in in the hospital.
	 */
	void checkIn() {
		this.discharged = false;
	}

	/**
	 * Factory method for diagnose.
	 * 
	 * @param complaints
	 *            The complaints of the patient
	 * @param diag
	 *            The diagnose of the doctor
	 * @param user
	 *            The Doctor that gives this diagnose
	 * @param secOp
	 *            The Doctor that has to give a second opinion, if null is
	 *            provided it will not be marked for second op.
	 * @return
	 * @throws InvalidDiagnoseException
	 * @see Diagnose
	 * @throws InvalidDoctorException
	 * @see Diagnose
	 * @throws InvalidComplaintsException
	 */
	public Diagnose createDiagnose(String complaints, String diag, Doctor user, Doctor secOp)
			throws InvalidDiagnoseException, InvalidDoctorException, InvalidComplaintsException {
		Diagnose diagnose;
		try {
			diagnose = new Diagnose(user, complaints, diag, this);
		} catch (InvalidPatientFileException e) {
			throw new Error("unexpected error, can not create diagnose for this patientfile");
		}
		this.addDiagnose(diagnose);

		if (secOp != null) {
			diagnose.markForSecOp(secOp);
		}
		return diagnose;
	}

	/**
	 * This function discharges this patient.
	 * 
	 * @throws DischargePatientException
	 */
	void discharge() throws DischargePatientException {
		if (!canBeDischarged())
			throw new DischargePatientException("Patient cannot be discharged!");
		this.discharged = true;
	}

	@Override
	public Collection<DiagnoseIN> getAllDiagnosisIN() {
		Collection<DiagnoseIN> rv = new ArrayList<DiagnoseIN>();
		rv.addAll(diagnosis);
		return rv;
	}

	public Collection<Diagnose> getAllDiagnosis() {
		Collection<Diagnose> rv = new ArrayList<Diagnose>();
		rv.addAll(diagnosis);
		return rv;
	}

	public Collection<Task<?>> getAllMedicalTests() {
		return new LinkedList<Task<?>>(medicaltests);
	}

	/**
	 * @return All diagnosis kept in this patient file made by a certain doctor.
	 */
	public Collection<DiagnoseIN> getDiagnosisFrom(Doctor doc) {
		Collection<DiagnoseIN> rv = new LinkedList<DiagnoseIN>();
		for (Diagnose d : this.diagnosis)
			if (d.getAttendingIN().equals(doc))
				rv.add((DiagnoseIN) d);
		return rv;
	}

	public PatientIN getPatientIN() {
		return patient_;
	}

	/**
	 * DO NOT USE THIS METHOD ANYWHERE OUTSIDE OF THE DOMAIN LAYER!
	 * 
	 */
	public Patient getPatient() {
		return this.patient_;
	}

	@Override
	public Collection<DiagnoseIN> getPendingDiagnosisForIN(DoctorIN d) {
		Collection<DiagnoseIN> rv = new LinkedList<DiagnoseIN>();
		for (Diagnose diag : this.diagnosis)
			if (diag.isMarkedForSecOp() && !diag.isApprovedIN() && diag.getAttendingIN().equals((Doctor) d))
				rv.add((DiagnoseIN) d);
		return rv;
	}

	@Basic
	public boolean isDischarged() {
		return this.discharged;
	}

	/**
	 * ONLY USE IN DOMAIN LAYER!!
	 */
	public void removeTest(Task<? extends MedicalTest> test) {
		this.medicaltests.remove(test);
	}

	/**
	 * @return True if d is a valid Diagnose.
	 */
	private boolean isValidDiagnose(Diagnose d) {
		return d != null;
	}

	/**
	 * @return True if medicalTest is a valid medical test.
	 */

	private boolean isValidMedicalTest(Task<? extends MedicalTest> medicalTest) {
		return medicalTest != null;
	}

	@Override
	public Collection<TaskIN> getAllMedicalTestsIN() {

		return new ArrayList<TaskIN>(medicaltests);
	}

}