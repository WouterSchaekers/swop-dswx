package patient;

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
import exceptions.InvalidAmountException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidHospitalDateException;

/**
 * This class represents a diagnosis that's given to a patient after admission
 * and examination. It keeps a collections of Treatments to keep track of which
 * Diagnosis have been treated with which Treatments.
 */
public class Diagnose extends Observable implements DiagnoseIN
{
	private boolean approved = false;
	private Doctor attending = null;
	private String diag = "";
	private Doctor secopDoc = null;
	private boolean secOpFlag = false;
	/**
	 * the treatments associated with this diagnose
	 */
	private Collection<Task<? extends Treatment>> treatments = new LinkedList<Task<? extends Treatment>>();

	/**
	 * Default constructor. Initialises fields.
	 * 
	 * @param doc
	 *            The doctor who made the diagnosis.
	 * @param diag
	 *            The diagnosis made by the doctor.
	 * @throws InvalidDoctorException
	 *             If !isValidDoc(doc)
	 * @throws InvalidDiagnoseException
	 *             if !isValidDiagnosis(diag)
	 */
	public Diagnose(Doctor doc, String diag) throws InvalidDoctorException, InvalidDiagnoseException {
		if (!this.canHaveAsDoctor(doc))
			throw new InvalidDoctorException("Doctor is invalid!");
		if (!this.isValidDiagnosis(diag))
			throw new InvalidDiagnoseException("Diagnose in Diagnoseconstructor is invalid!");
		this.attending = doc;
		this.diag = diag;
	}

	/**
	 * USE THIS METHOD ONLY IN THE DOMAIN LAYER!!
	 */
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
	public boolean canBeReplacedWith(DiagnoseIN replacement) {
		Diagnose temp = (Diagnose) replacement;
		boolean rv = this.secopDoc.equals(temp.attending);
		rv &= (this.attending.equals(temp.secopDoc));
		rv &= temp.secOpFlag;
		return rv;
	}

	/**
	 * @return True if doc is a valid doctor for this Diagnose.
	 */
	private boolean canHaveAsDoctor(Doctor doc) {
		return !(doc == null || this.secopDoc.equals(doc));
	}

	/**
	 * Use to create treatment descriptions.
	 */
	public Treatment createTreatment(TreatmentFactory treatFact) throws FactoryInstantiationException {
		try { 
			return treatFact.create();
		} catch (InvalidAmountException e) {
			throw new FactoryInstantiationException(e.getMessage());
		} catch (InvalidHospitalDateException e) {
			throw new FactoryInstantiationException(e.getMessage());
		}		
	}

	/**
	 * Disapproves this diagnose.
	 * 
	 * @throws ApproveDiagnoseException
	 */
	public void disapprove() throws ApproveDiagnoseException {
		if (!isMarkedForSecOp())
			throw new ApproveDiagnoseException("Can't disapprove diagnose that does not need second opinion!");
		this.approved = false;
		this.secOpFlag = false;
		this.attending = null;
		this.secopDoc = null;
	}

	@Basic
	public Doctor getAttending() {
		return this.attending;
	}

	@Basic
	public String getDiagnose() {
		return this.diag;
	}

	@Basic
	public Collection<TaskIN> getTreatments() {
		return new LinkedList<TaskIN>(treatments);
	}

	@Basic
	public boolean isApproved() {
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
		return !diag.equals("");
	}

	private boolean isValidtreatment(Task<?> treatment) {
		return treatment != null;
	}

	/**
	 * This function marks this Diagnose for second opinion.
	 * 
	 * @param from
	 *            The doctor that needs to give the second opinion.
	 * @throws InvalidDoctorException
	 *             if !canHaveAsDoctor(from)
	 */
	public void markForSecOp(Doctor from) throws InvalidDoctorException {
		if (!canHaveAsDoctor(from))
			throw new InvalidDoctorException("Invalid Doctor given to request for second opinion!");
		this.secOpFlag = true;
		this.approved = false;
		this.secopDoc = from;
	}

	@Basic
	public DoctorIN needsSecOpFrom() {
		DoctorIN rv = (DoctorIN) (this.secopDoc);
		return rv;
	}

	@Override
	public String toString() {
		return this.getDiagnose();
	}

	/**
	 * Unmarks this diagnosis for second opinion.
	 */
	private void unmarkForSecOp() {
		this.secOpFlag = false;
		this.secopDoc = null;
	}

}
