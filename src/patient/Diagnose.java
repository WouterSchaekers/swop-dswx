package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import scheduler.tasks.Task;
import treatment.Treatment;
import treatment.TreatmentFactory;
import users.Doctor;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.TaskIN;
import exceptions.ApproveDiagnoseException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidComplaintsException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidPatientFileException;

/**
 * This class represents a diagnosis that's given to a patient after admission
 * and examination. It keeps a collections of Treatments to keep track of which
 * Diagnosis have been treated with which Treatments.
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
	/**
	 * the treatments associated with this diagnose
	 */
	private Collection<Task<? extends Treatment>> treatments;

	/**
	 * Default constructor. Initialises fields.
	 * 
	 * @param doc
	 *            The doctor who made the diagnose.
	 * @param diag
	 *            The diagnose made by the doctor.
	 * @param complaints
	 *            The complaints the patient has made that lead to this
	 *            diagnose.
	 * @throws InvalidDoctorException
	 * @throws InvalidDiagnoseException
	 * @throws InvalidComplaintsException
	 * @throws InvalidPatientFileException 
	 */
	Diagnose(Doctor doc, String complaints, String diag,PatientFile creator) throws InvalidDoctorException,
			InvalidDiagnoseException, InvalidComplaintsException, InvalidPatientFileException {
		if(!this.canHaveAsPatientFile(creator))
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
	 * USE THIS METHOD ONLY IN THE DOMAIN LAYER!!
	 */
	@Deprecated
	public void addTreatment(Task<? extends Treatment> treatment) {
		if (!isValidtreatment(treatment))
			throw new IllegalArgumentException(treatment + " is not valid!");
		this.treatments.add(treatment);
	}

	/**
	 * Approves this diagnosis.
	 * 
	 * @throws ApproveDiagnoseException
	 *             If this diagnose was not marked for second opinion.
	 */
	public void approve() throws ApproveDiagnoseException {
		if (!isMarkedForSecOp())
			throw new ApproveDiagnoseException("Can't approve diagnose that does not need second opinion!");
		this.approved = true;
		this.unmarkForSecOp();
		this.notifyObservers();
	}

	/**
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
	 * @return True if doc is a valid doctor for this Diagnose.
	 */
	private boolean canHaveAsDoctor(Doctor doc) {
		return !(doc == null || this.secopDoc.equals(doc));
	}
	private boolean canHaveAsPatientFile(PatientFile creator) {
		
		return creator != null;
	}
	/**
	 * Use to create treatment descriptions.
	 */
	public Treatment createTreatment(TreatmentFactory treatFact) throws FactoryInstantiationException {
		try {
			return treatFact.create();
		} catch (InvalidHospitalDateException e) {
			throw new FactoryInstantiationException(e.getMessage());
		}
	}

	/**
	 * Disapproves this diagnose.
	 * 
	 * @throws ApproveDiagnoseException
	 */
	private void disapprove() throws ApproveDiagnoseException {
		if (!isMarkedForSecOp())
			throw new ApproveDiagnoseException("Can't disapprove diagnose that does not need second opinion!");
		this.approved = false;
		this.secOpFlag = false;
		this.attending = null;
		this.secopDoc = null;
		this.notifyObservers(this);
	}

	public void disapprove(String newDiagnose,String newComplaints) throws ApproveDiagnoseException 	{
		Doctor futureSecondOp = this.getAttendingIN();
		Doctor futureAttending = (Doctor) this.needsSecOpFromIN();
		if(!canBeReplacedWith(newDiagnose, newComplaints, futureAttending, futureSecondOp))
			throw new ApproveDiagnoseException("");
		this.disapprove();
		try{
			
		this.myPatientFile.createDiagnose(newComplaints, newDiagnose, futureAttending, futureAttending);
		}catch(Exception e)
		{
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
	public Collection<TaskIN> getTreatmentsIN() {
		return new LinkedList<TaskIN>(treatments);
	}

	@Basic
	public boolean isApprovedIN() {
		return this.approved;
	}

	@Basic
	public boolean isMarkedForSecOp() {
		return this.secOpFlag;
	}

	/**
	 * @return True if diag is a valid Diagnose description for this Diagnose.
	 */
	private boolean isValidDiagnosis(String diag) {
		return diag != null && !diag.equals("");
	}

	private boolean isValidtreatment(Task<?> treatment) {
		return treatment != null;
	}

	/**
	 * ONLY USE IN DOMAIN LAYER!!
	 */
	public void markForDeletion() {
		this.mustBeDeleted = true;
	}

	/**
	 * This function marks this Diagnose for second opinion.
	 * 
	 * @param from
	 *            The doctor that needs to give the second opinion.
	 * @throws InvalidDoctorException
	 *             if !canHaveAsDoctor(from)
	 */
	void markForSecOp(Doctor from) throws InvalidDoctorException {
		if (!canHaveAsDoctor(from))
			throw new InvalidDoctorException("Invalid Doctor given to request for second opinion!");
		this.secOpFlag = true;
		this.approved = false;
		this.secopDoc = from;
	}
	
	public boolean mustBeDeleted() {
		return this.mustBeDeleted;
	}
	
	@Basic
	public DoctorIN needsSecOpFromIN() {
		DoctorIN rv = (DoctorIN) (this.secopDoc);
		return rv;
	}

	/**
	 * ONLY USE IN TREATMENT DEINITMETHOD!!
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

	public Collection<Task<? extends Treatment>> getTreatments() {
		return new ArrayList<Task<? extends Treatment>>(treatments);
		
	}
}
