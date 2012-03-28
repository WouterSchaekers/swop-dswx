package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import scheduler.tasks.Task;
import treatment.Treatment;
import users.Doctor;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.TreatmentIN;
import exceptions.ApproveDiagnoseException;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidTreatmentException;

/**
 * This class represents a diagnosis that's given to a patient after admission
 * and examination. It keeps a collections of Treatments to keep track of which
 * Diagnosis have been treated with which Treatments.
 */
public class Diagnose extends Observable implements DiagnoseIN
{
	private String diag = "";
	private boolean approved = false;
	private boolean secOpFlag = false;
	private Doctor attending = null;
	private Doctor secopDoc = null;
	/**
	 * the treatments associated with this diagnosis
	 */
	private Collection<Task<? extends Treatment>> treatments = new ArrayList<Task<? extends Treatment>>();

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
	public Diagnose(Doctor doc, String diag) throws InvalidDoctorException,
			InvalidDiagnoseException {
		if (!this.canHaveAsDoctor(doc))
			throw new InvalidDoctorException("Doctor is invalid!");
		if (!this.isValidDiagnosis(diag))
			throw new InvalidDiagnoseException(
					"Diagnose in Diagnoseconstructor is invalid!");
		this.attending = doc;
		this.diag = diag;
	}

	public void addTreatment(Task<? extends Treatment> t) throws InvalidTreatmentException {
		if (this.isValidTreatment(t))
			this.treatments.add(t);
		else
			throw new InvalidTreatmentException(
					"Trying to add invalid treatment to diagnose!");
	}

	/**
	 * @return True if this diagnose can be replaced by the given replacement.
	 */
	public boolean canBeReplacedWith(DiagnoseIN replacement) {
		Diagnose temp = (Diagnose)replacement;
		boolean rv = this.secopDoc.equals(temp.attending);
		rv &= (this.attending.equals(temp.secopDoc));
		rv &= temp.secOpFlag;
		return rv;
	}

	/**
	 * @return True if doc is a valid doctor for this Diagnose.
	 */
	private boolean canHaveAsDoctor(Doctor doc) {
		return !(doc == null);
	}
	
	/**
	 * Approves this diagnosis.
	 * 
	 * @throws ApproveDiagnoseException
	 *             If this diagnose was not marked for second opinion.
	 */
	public void approve() throws ApproveDiagnoseException {
		if (!isMarkedForSecOp())
			throw new ApproveDiagnoseException(
					"Can't approve diagnose that does not need second opinion!");
		this.approved = true;
		this.unmarkForSecOp();
		this.notifyObservers();
	}

	/**
	 * Disapproves this diagnose.
	 *
	 * @throws ApproveDiagnoseException
	 */
	public void disapprove()
			throws ApproveDiagnoseException {
		if (!isMarkedForSecOp())
			throw new ApproveDiagnoseException(
					"Can't disapprove diagnose that does not need second opinion!");
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
	public String getDiagnosis() {
		return this.diag;
	}

	@Basic
	public Collection<TreatmentIN> getTreatments() {
		ArrayList<TreatmentIN> rv = new ArrayList<TreatmentIN>();
		for (Task<? extends Treatment> t : this.treatments)
			rv.add((TreatmentIN)(t));
		return rv;
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

	/**
	 * @return True if t is a valid treatment for this Diagnose.
	 */
	private boolean isValidTreatment(Task<? extends Treatment> t) {
		return t != null;
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
			throw new InvalidDoctorException(
					"Invalid Doctor given to request for second opinion!");
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
		return this.getDiagnosis();
	}

	/**
	 * Unmarks this diagnosis for second opinion.
	 */
	private void unmarkForSecOp() {
		this.secOpFlag = false;
		this.secopDoc = null;
	}

}
