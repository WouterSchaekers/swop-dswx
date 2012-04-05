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
import exceptions.InvalidPatientFileException;

/**
 * This class represents the patient file of a patient.
 */
public class PatientFile implements PatientFileIN
{
	private Collection<Diagnose> diagnosis = new LinkedList<Diagnose>();
	private boolean discharged = false;
	private Collection<Task<? extends TaskDescription>> medicaltests = new LinkedList<Task<? extends TaskDescription>>();
	private Patient patient_;

	/**
	 * Default constructor.
	 * 
	 * @param patient
	 *            The patient to whom this patient file belongs to.
	 */
	PatientFile(Patient patient) {
		this.patient_ = patient;
	}

	/**
	 * Adds a diagnose to this patient file.
	 * 
	 * @param diagnose
	 *            The diagnose to add.
	 * @throws InvalidDiagnoseException
	 *             if the given diagnose is not a valid one.
	 */
	private void addDiagnose(Diagnose diagnose) throws InvalidDiagnoseException {
		if (!isValidDiagnose(diagnose))
			throw new InvalidDiagnoseException("The given diagnose is not a valid!");
		this.diagnosis.add(diagnose);
	}

	/**
	 * USE THIS METHOD ONLY IN THE DOMAIN LAYER!!
	 * Adds a medical test to this patient file.
	 */
	public void addMedicalTest(Task<? extends MedicalTest> medicalTest) {
		if (!isValidMedicalTest(medicalTest))
			throw new IllegalArgumentException(medicalTest + " is not valid!");
		this.medicaltests.add(medicalTest);
	}

	/**
	 * @return True if this patient is ready to be discharged. (= does not have any unfinished treatments or medical tests)
	 */
	private boolean canBeDischarged() {
		for (Diagnose d : diagnosis) {
			if (d.isMarkedForSecOpBy(null))
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
	 * Checks this patient in into the hospital.
	 */
	void checkIn() {
		this.discharged = false;
	}

	/**
	 * Factory method for diagnose.
	 * 
	 * @param complaints
	 *            The complaints of the patient.
	 * @param diag
	 *            The diagnose of the doctor.
	 * @param user
	 *            The doctor that gives this diagnose.
	 * @param secOp
	 *            The doctor that has to give a second opinion, if null is
	 *            provided it will not be marked for second opinion.
	 * @return The created diagnose
	 * @throws InvalidDiagnoseException
	 * @throws InvalidDoctorException
	 * @throws InvalidComplaintsException
	 * @see Diagnose
	 * @throws IllegalAccessException
	 *             If there's an unauthorised approve-diagnose-function call.
	 *             (if the diagnose is marked for second opinion but no doctor
	 *             was provided,...)
	 */
	public Diagnose createDiagnose(String complaints, String diag, Doctor user, Doctor secOp)
			throws InvalidDiagnoseException, InvalidDoctorException, InvalidComplaintsException, IllegalAccessException {
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
		else{
			diagnose.approveSelf();
		}
		return diagnose;
	}
	
	/**
	 * Discharges this patient from the hospital.
	 * 
	 * @throws DischargePatientException
	 *             If this patient cannot be discharged yet
	 * @see PatientFile.canBeDischarged()
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
	
	/**
	 * @return All diagnosis ever made and stored in this patient file.
	 */
	@Basic
	public Collection<Diagnose> getAllDiagnosis() {
		Collection<Diagnose> rv = new ArrayList<Diagnose>();
		rv.addAll(diagnosis);
		return rv;
	}

	/**
	 * @return All medical tests ever made for this patient file.
	 */
	@Basic
	public Collection<Task<?>> getAllMedicalTests() {
		return new LinkedList<Task<?>>(medicaltests);
	}

	/**
	 * @return All diagnosis kept in this patient file made by a certain doctor.
	 */
	public Collection<DiagnoseIN> getDiagnosisFrom(Doctor doctor) {
		Collection<DiagnoseIN> rv = new LinkedList<DiagnoseIN>();
		for (Diagnose diagnose : this.diagnosis)
			if (diagnose.getAttendingIN().equals(doctor))
				rv.add((DiagnoseIN) diagnose);
		return rv;
	}

	public PatientIN getPatientIN() {
		return patient_;
	}

	/**
	 * DO NOT USE THIS METHOD ANYWHERE OUTSIDE OF THE DOMAIN LAYER!
	 */
	@Basic
	public Patient getPatient() {
		return this.patient_;
	}

	/**
	 * @return All diagnosis that still need a second opinion by the given doctor before their treatments can be scheduled.
	 */
	@Override
	public Collection<DiagnoseIN> getPendingDiagnosisForIN(DoctorIN doctor) {
		Collection<DiagnoseIN> rv = new LinkedList<DiagnoseIN>();
		for (Diagnose diag : this.diagnosis)
			if (diag.isMarkedForSecOpBy(null) && !diag.isApprovedIN() && diag.getAttendingIN().equals((Doctor) doctor))
				rv.add((DiagnoseIN) diag);
		return rv;
	}

	@Basic
	public boolean isDischarged() {
		return this.discharged;
	}

	/**
	 * @return True if d is a valid Diagnose.
	 */
	private boolean isValidDiagnose(Diagnose d) {
		return d != null;
	}

	/**
	 * @return True if medicalTest is a valid medical test for this patient file.
	 */
	private boolean isValidMedicalTest(Task<? extends MedicalTest> medicalTest) {
		return medicalTest != null && ((PatientFile)(medicalTest.getDescription().getPatientFile())).equals(this);
	}

	@Override
	public Collection<TaskIN> getAllMedicalTestsIN() {
		return new LinkedList<TaskIN>(medicaltests);
	}

}