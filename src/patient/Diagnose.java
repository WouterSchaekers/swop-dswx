package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import scheduler.tasks.Task;
import treatment.Treatment;
import users.Doctor;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.TaskIN;
import exceptions.ApproveDiagnoseException;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidPatientFileException;

/**
 * This class represents a diagnose that's given to a patient after admission
 * and examination. It keeps a collection of Treatments to keep track of which
 * the diagnose is to be treated with which Treatments.
 */
public class Diagnose extends Observable implements DiagnoseIN
{
	private boolean approved;
	private Doctor attending;
	private String complaints;
	private String diag;
	private boolean mustBeDeleted = false;
	private final PatientFile myPatientFile;
	private Doctor secopDoc;
	private boolean secOpFlag;
	private Collection<Task<? extends Treatment>> treatments;

	/**
	 * Default constructor. Initialises fields.
	 * 
	 * @param doc
	 *            The doctor creating this diagnose.
	 * @param complaints
	 *            The complaints the patient came in with, that lead to this
	 *            diagnose.
	 * @param diag
	 *            The diagnose the doctor made.
	 * @param creator
	 *            The patient file that keeps this diagnose stored.
	 * @throws InvalidDoctorException
	 *             If the given attending doctor is invalid.
	 * @throws InvalidDiagnoseException
	 *             If the diagnose the doctor made is invalid.
	 * @throws InvalidComplaintsException
	 *             If the complains given are invalid.
	 * @throws InvalidPatientFileException
	 *             If the patient file that keeps this diagnose is invalid.
	 */
	Diagnose(Doctor doc, String complaints, String diag, PatientFile creator) throws InvalidDoctorException,
			InvalidDiagnoseException, InvalidComplaintsException, InvalidPatientFileException {
		if (!this.canHaveAsPatientFile(creator))
			throw new InvalidPatientFileException("can not have this as creator");
		if (!this.canHaveAsDoctor(doc))
			throw new InvalidDoctorException("Doctor is invalid!");
		if (!this.isValidDiagnosis(diag))
			throw new InvalidDiagnoseException("Diagnose in Diagnoseconstructor is invalid!");
		if (!this.isValidDiagnosis(complaints))
			throw new InvalidComplaintsException("Invalid complaints were given when trying to create new Diagnose!");
		this.myPatientFile = creator;
		this.complaints = complaints;
		this.attending = doc;
		this.diag = diag;
		this.treatments = new LinkedList<Task<? extends Treatment>>();
	}

	/**
	 * Adds a treatment to this diagnose. USE THIS METHOD ONLY IN THE DOMAIN
	 * LAYER (init method of Diagnose)!!
	 */
	@Deprecated
	public void addTreatmentTask(Task<? extends Treatment> treatment) {
		if (!isValidtreatment(treatment))
			throw new IllegalArgumentException(treatment + " is not valid!");
		this.treatments.add(treatment);
	}

	/**
	 * Approves this diagnosis.
	 * 
	 * @param secondOp
	 * 
	 * @throws ApproveDiagnoseException
	 *             If this diagnose was not marked for second opinion or if this
	 *             diagnose wasn't marked for second opinion.
	 */
	public void approveBy(Doctor secondOp) throws ApproveDiagnoseException {
		if (!isMarkedForSecOpBy(secondOp))
			throw new ApproveDiagnoseException("The given doctor is not allowed to approve this diagnose!");
		this.approved = true;
		this.unmarkForSecOp();
		this.setChanged();
		this.notifyObservers();
		this.notifyObservers(null);
	}

	/**
	 * !!! USE ONLY IN PATIENT-PACKAGE !!! Approves the diagnose if it does not
	 * to be reviewed by a second doctor. Only execute from the patient file
	 * when you're certain the diagnose does not require second opinion!
	 * 
	 * @throws IllegalAccessException
	 *             If this diagnose was already marked for second opinion, this
	 *             method will not be allowed to execute.
	 */
	void approveSelf() throws IllegalAccessException {
		if (this.secOpFlag)
			throw new IllegalAccessException("Diagnose requires second opinion and can't be self approved.");
		this.approved = true;
	}

	/**
	 * Checks if the given parameters result in a second opinion's new diagnose
	 * that can be replaced with this one.
	 * 
	 * @return True if this diagnose can be replaced by the given replacement.
	 */
	private boolean canBeReplacedWith(String newDiag, String newComplaints, Doctor newAttending, Doctor newSecOp) {
		boolean rv = this.secopDoc.equals(newAttending);
		rv &= newSecOp != null;
		rv &= (this.attending.equals(newSecOp));
		rv &= newComplaints.equals(this.getComplaintsIN());
		return rv;
	}

	/**
	 * Checks if the given doctor can be an attending doctor for this diagnose.
	 * 
	 * @return True if doc is a valid doctor for this Diagnose.
	 */
	private boolean canHaveAsDoctor(Doctor doc) {
		return !(doc == null || (this.secopDoc != null && this.secopDoc.equals(doc)));
	}

	/**
	 * Checks if the given patient file can be the creator this diagnose.
	 * 
	 * @param creator
	 *            The patient file that is supposedly creating this diagnose.
	 * @return True if the given patient file can create this diagnose.
	 */
	private boolean canHaveAsPatientFile(PatientFile creator) {

		return creator != null;
	}

	/**
	 * Disapproves this diagnose.
	 * 
	 * @throws ApproveDiagnoseException
	 *             If this diagnose is not marked for second opinion.
	 */
	private void disapprove() throws ApproveDiagnoseException {
		this.approved = false;
		this.secOpFlag = false;
		this.attending = null;
		this.secopDoc = null;
		this.notifyObservers(this);
	}
	/**
	 * 
	 * @param newDiagnose
	 * @param newComplaints
	 * @param secondOp
	 * @return
	 * @throws ApproveDiagnoseException
	 */
	/**
	 * Disapproves this diagnose and adds a new one to the patient file that
	 * created this one.
	 * 
	 * @param newDiagnose
	 *            The new diagnose.
	 * @param newComplaints
	 *            The new complaints.
	 * @param secondOp
	 *            The doctor that's giving his/her second opinion.
	 * @return The replacement diagnose.
	 * @throws ApproveDiagnoseException
	 *             If this diagnose does not require a second opinion or if the
	 *             replacement diagnose is invalid.
	 */
	public DiagnoseIN disapproveBy(String newDiagnose, String newComplaints, DoctorIN secondOp)
			throws ApproveDiagnoseException {
		Doctor futureSecondOp = this.getAttendingIN();
		Doctor futureAttending = (Doctor) secondOp;
		if (!canBeReplacedWith(newDiagnose, newComplaints, futureAttending, futureSecondOp))
			throw new ApproveDiagnoseException("This diagnose cannot be replaced with the given replacement!");
		if (!this.secOpFlag)
			throw new ApproveDiagnoseException("Trying to disapprove a diagnose that does not need second opinion!");
		this.disapprove();
		try {
			return this.myPatientFile.createDiagnose(newComplaints, newDiagnose, futureAttending, futureAttending);
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	@Basic
	public Doctor getAttendingIN() {
		return this.attending;
	}

	@Basic
	public String getComplaintsIN() {
		return this.complaints;
	}

	@Basic
	public String getDiagnoseIN() {
		return this.diag;
	}

	@Basic
	public PatientFile getPatientFile() {
		return this.myPatientFile;
	}

	/**
	 * @return All treatments that have been queued/scheduled/finished
	 *         associated with this diagnose.
	 */
	public Collection<Task<? extends Treatment>> getTreatments() {
		return new ArrayList<Task<? extends Treatment>>(treatments);

	}

	@Basic
	public Collection<TaskIN> getTreatmentsIN() {
		return new LinkedList<TaskIN>(treatments);
	}

	@Override
	@Basic
	public boolean isApproved() {
		if (this.secOpFlag)
			return this.approved;
		else
			return true;
	}

	boolean isMarkedForSecondOp() {
		return secOpFlag;
	}
	
	@Basic
	public boolean isMarkedForSecOpBy(Doctor doctor) {
		return this.secOpFlag && this.secopDoc == doctor;
	}

	/**
	 * @return True if diag is a valid diagnose description for this diagnose.
	 */
	private boolean isValidDiagnosis(String diag) {
		return diag != null && !diag.equals("");
	}

	/**
	 * Checks if a treatment is a valid one for this diagnose.
	 * 
	 * @return True if the given treatment is a valid treatment for this
	 *         diagnose.
	 */
	private boolean isValidtreatment(Task<?> treatment) {
		return treatment != null && treatment.getDescription() instanceof Treatment;
	}

	/**
	 * Marks this diagnose's treatments for deletion. ONLY USE IN DOMAIN LAYER!!
	 */
	public void markForDeletion() {
		this.mustBeDeleted = true;
	}

	/**
	 * Marks this Diagnose for second opinion.
	 * 
	 * @param from
	 *            The doctor that the attending wants to get a second opinion
	 *            from.
	 * @throws InvalidDoctorException
	 *             if the given doctor is not a doctor that can give second
	 *             opinions.
	 */
	void markForSecOp(Doctor from) throws InvalidDoctorException {
		if (!canHaveAsDoctor(from))
			throw new InvalidDoctorException("Invalid Doctor given to request for second opinion!");
		this.secOpFlag = true;
		this.approved = false;
		this.secopDoc = from;
	}

	@Basic
	public boolean mustBeDeleted() {
		return this.mustBeDeleted;
	}

	/**
	 * @return The doctor that has been selected to give a second opinion on
	 *         this diagnose.
	 */
	@Override
	@Basic
	public DoctorIN needsSecOpFromIN() {
		DoctorIN rv = (DoctorIN) (this.secopDoc);
		return rv;
	}

	/**
	 * ONLY USE IN TREATMENT DEINIT METHOD!!
	 */
	public void removeTreatment(Task<?> treatment) {
		this.treatments.remove(treatment);
	}

	@Override
	public String toString() {
		return this.getDiagnoseIN();
	}

	/**
	 * Unmarks this diagnosis for second opinion.
	 */
	private void unmarkForSecOp() {
		this.secOpFlag = false;
		this.secopDoc = null;
	}
}
