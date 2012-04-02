package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;
import medicaltest.MedicalTest;
import medicaltest.MedicalTestFactory;
import scheduler.HospitalDate;
import scheduler.tasks.Task;
import scheduler.tasks.TaskDescription;
import scheduler.tasks.TaskManager;
import users.Doctor;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.TaskIN;
import exceptions.DischargePatientException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidNameException;

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

	private ArrayList<HospitalDate> xrays = new ArrayList<HospitalDate>();

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
		if(! isValidMedicalTest(medicalTest))
				throw new IllegalArgumentException(medicalTest+ " is not valid!");
		this.medicaltests.add(medicalTest);
	}
	
	/**
	 * This method must be called if an XRrayScan is ordered for this patient.
	 * It is needed to keep track of the amount of xrays a patient has had in
	 * the past year.
	 * 
	 * @param d
	 *            The date on which the XRay is scheduled.
	 */
	public void addXRay(HospitalDate d) {
		//TODO fix da ding van die XRays
		xrays.add(d);
	}

	/**
	 * @param curDate
	 *            The current system time.
	 * @return The amount of xrays this patient has had in the last year.
	 */
	public int amountOfXraysThisYear(HospitalDate hospitalDate) {
		int amount = 0;
		for (HospitalDate xr : this.xrays) {
			if (hospitalDate.before(xr) && xr.getTimeBetween(hospitalDate) <= HospitalDate.ONE_YEAR)
				amount++;
		}
		return amount;
	}

	/**
	 * @return True if this patient is ready to be discharged.
	 */
	private boolean canBeDischarged() {
		for (Diagnose d : diagnosis) {
			if (d.isMarkedForSecOp())
				return false;
			for (TaskIN t : d.getTreatments())
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
	public void checkIn() {
		this.discharged = false;
	}

	/**
	 * Use to create diagnosis for this patient file.
	 * 
	 * @param user
	 *            The doctor who made the diagnose.
	 * @param complaints
	 *            The complaints the patient had that lead to the diagnose.
	 * @param diag
	 *            The diagnose.
	 * @param manager
	 *            The TaskManager that should observe the state of the newly
	 *            created diagnose object should it's approved-state change.
	 * @return The created diagnose
	 * @throws InvalidDiagnoseException
	 * @throws InvalidDoctorException
	 * @throws InvalidComplaintsException
	 */
	public DiagnoseIN createDiagnose(String complaints, String diag, Doctor user, Doctor secOp, TaskManager manager)
			throws InvalidDiagnoseException, InvalidDoctorException, InvalidComplaintsException {
		Diagnose diagnose;
		diagnose = new Diagnose(user, complaints, diag);
		diagnose.addObserver((Observer) manager);
		this.addDiagnose(diagnose);
		
		if(secOp != null) {
			diagnose.markForSecOp(secOp);
		}
		return diagnose;
	}

	/**
	 * Use to create new medical test descriptions.
	 * 
	 * @param test
	 * @return The created medical test description.
	 * @throws FactoryInstantiationException
	 */
	@SuppressWarnings("deprecation")
	public MedicalTest createMedicalTest(MedicalTestFactory test) throws FactoryInstantiationException {
		return test.create();
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
	public Collection<DiagnoseIN> getAllDiagnosis() {
		Collection<DiagnoseIN> rv = new ArrayList<DiagnoseIN>();
		rv.addAll(diagnosis);
		return rv;
	}

	@Override
	public Collection<TaskIN> getallMedicalTests() {
		return new LinkedList<TaskIN>(medicaltests);
	}

	/**
	 * @return All diagnosis kept in this patient file made by a certain doctor.
	 */
	public Collection<DiagnoseIN> getDiagnosisFrom(Doctor doc) {
		Collection<DiagnoseIN> rv = new LinkedList<DiagnoseIN>();
		for (Diagnose d : this.diagnosis)
			if (d.getAttending().equals(doc))
				rv.add((DiagnoseIN) d);
		return rv;
	}

	/**
	 * @return The first date this patientfile has a
	 */
	public HospitalDate getFirstNewXRaySchedDate(HospitalDate hospitalDate) {
		if (this.amountOfXraysThisYear(hospitalDate) >= 9) {
			return new HospitalDate(this.xrays.get(xrays.size() - 10).getTimeSinceStart());
		}
		return hospitalDate;
	}

	@Override
	public String getPatientName() {
		return this.getPatient().getName();
	}

	/**
	 * DO NOT USE THIS METHOD ANYWHERE OUTSIDE OF THE DOMAIN LAYER!
	 * 
	 */
	public Patient getPatient() {
		return this.patient_;
	}

	@Override
	public Collection<DiagnoseIN> getPendingDiagnosisFor(DoctorIN d) {
		Collection<DiagnoseIN> rv = new LinkedList<DiagnoseIN>();
		for (Diagnose diag : this.diagnosis)
			if (diag.isMarkedForSecOp() && !diag.isApproved() && diag.getAttending().equals((Doctor) d))
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
}