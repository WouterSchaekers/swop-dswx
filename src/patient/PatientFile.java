package patient;

import java.util.ArrayList;
import java.util.Collection;
import medicaltest.MedicalTest;
import medicaltest.XRayScan;
import observers.DiagnoseObserverTaskManager;
import scheduler.HospitalDate;
import schedulerold.task.TaskManager;
import users.Doctor;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.MedicalTestIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.TreatmentIN;
import exceptions.DischargePatientException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidNameException;

/**
 * This class represents the patient file of a patient.
 */
public class PatientFile implements PatientFileIN
{

	private String name = "";
	/**
	 * All the Diagnosis for this patient.
	 */
	private Collection<Diagnose> diagnosis = new ArrayList<Diagnose>();
	private boolean discharged = false;
	private ArrayList<HospitalDate> xrays = new ArrayList<HospitalDate>();
	private Collection<MedicalTest> medicaltests = new ArrayList<MedicalTest>();

	/**
	 * Default Constructor. Creates empty patient file with a name.
	 * 
	 * @param patientname
	 *            The name of the patient to whom this patient file belongs to.
	 * @throws InvalidNameException
	 *             if(!isValidName(patientname))
	 */
	public PatientFile(String patientname) throws InvalidNameException {
		if (!isValidName(patientname))
			throw new InvalidNameException(
					"The given patientname is not valid!");
		this.name = patientname;
	}

	/**
	 * This method will add a Diagnose to this PatientFile.
	 * 
	 * @param d
	 *            The Diagnose to add.
	 * @throws InvalidDiagnoseException
	 *             if(!isValidDiagnose(d))
	 */
	public void addDiagnosis(Diagnose d) throws InvalidDiagnoseException {
		if (!isValidDiagnose(d))
			throw new InvalidDiagnoseException(
					"The given Diagnose is not a valid!");

		this.diagnosis.add(d);
	}

	/**
	 * Checks this patient in in the hospital.
	 */
	public void checkIn() {
		this.discharged = false;
	}

	/**
	 * This function discharges this patient.
	 * 
	 * @throws DischargePatientException
	 */
	void discharge() throws DischargePatientException {
		if (!canBeDischarged())
			throw new DischargePatientException();
		this.discharged = true;
	}

	/**
	 * @return True if this patient is ready to be discharged.
	 */
	private boolean canBeDischarged() {
		for (Diagnose d : diagnosis) {
			if (d.isMarkedForSecOp())
				return false;
			for (TreatmentIN t : d.getTreatments())
				if (!t.hasFinished())
					return false;
			for (MedicalTest m : medicaltests)
				if (!m.hasFinished())
					return false;
		}
		return true;
	}

	/**
	 * @return True if d is a valid Diagnose.
	 */
	private boolean isValidDiagnose(Diagnose d) {
		return d != null;
	}

	/**
	 * @return True if d is a valid name.
	 */
	private boolean isValidName(String n) {
		return !n.equals("");
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
			if (hospitalDate.before(xr)
					&& xr.getTimeBetween(hospitalDate) <= HospitalDate.ONE_YEAR)
				amount++;
		}
		return amount;
	}

	/**
	 * @return
	 * The first date this patientfile has a 
	 */
	public HospitalDate getFirstNewXRaySchedDate(HospitalDate hospitalDate) {
		if (this.amountOfXraysThisYear(hospitalDate) >= 9) {
			return new HospitalDate(this.xrays.get(xrays.size() - 10)
					.getTimeSinceStart() + XRayScan.DURATION);
		}
		return hospitalDate;
	}

	@Basic
	public boolean isDischarged() {
		return this.discharged;
	}

	@Basic
	public String getName() {
		return this.name;
	}

	@Basic
	public Collection<Diagnose> getDiagnosis() {
		return new ArrayList<Diagnose>(this.diagnosis);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public Collection<DiagnoseIN> getAlldiagnosis() {
		Collection<DiagnoseIN> rv = new ArrayList<DiagnoseIN>();
		rv.addAll(diagnosis);
		return rv;
	}

	public static Diagnose createDiagnose(String diag, Doctor attending,
			TaskManager taskmanager) throws InvalidDoctorException,
			InvalidDiagnoseException {
		Diagnose d = new Diagnose(attending, diag);
		d.addObserver(new DiagnoseObserverTaskManager(taskmanager));
		return d;
	}

	public static Diagnose createDiagnoseSecondOp(String diag,
			Doctor attending, Doctor secondop, TaskManager taskmanager)
			throws InvalidDoctorException, InvalidDiagnoseException {
		Diagnose d = new Diagnose(attending, diag);
		d.addObserver(new DiagnoseObserverTaskManager(taskmanager));
		d.markForSecOp(secondop);
		return d;
	}

	public void addMedicalTest(MedicalTest create) {
		this.medicaltests.add(create);
	}

	@Override
	public Collection<TreatmentIN> getAllTreatments() {
		return null;// not yet implemented
	}

	@Override
	public Collection<MedicalTestIN> getallMedicalTests() {
		return new ArrayList<MedicalTestIN>(medicaltests);
	}

	public void createDiagnose(Doctor user, String diag, TaskManager manager)
			throws InvalidDiagnoseException, InvalidDoctorException {
		Diagnose diagnose = new Diagnose(user, diag);
		// XXX: diagnose.addObserver(manager);
		addDiagnosis(diagnose);

	}
}