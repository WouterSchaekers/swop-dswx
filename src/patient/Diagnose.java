package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.TreatmentIN;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.*;
import treatment.Treatment;
import users.Doctor;

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
	 * the  treatments associated with this  diagnosis
	 */
	private Collection<Treatment> treatments = new ArrayList<Treatment>(); 

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
	public Diagnose(Doctor doc, String diag) throws InvalidDoctorException,InvalidDiagnoseException {
		if (!this.canHaveAsDoctor(doc))
			throw new InvalidDoctorException("Doctor is invalid!");
		if (!this.isValidDiagnosis(diag))
			throw new InvalidDiagnoseException("Diagnose in Diagnoseconstructor is invalid!");
		this.attending = doc;
		this.diag = diag;
	}

	/**
	 * This function allows a Doctor to give second opinion on this Diagnose.
	 * 
	 * @param from
	 *            The Doctor that's trying to give a second opinion on this
	 *            Diagnose.
	 * @param diag
	 *            The second opinion the Doctor is giving.
	 * @throws InvalidDoctorException
	 *             if (!canGvieSecondOpionion(from))
	 * @throws InvalidDiagnoseException
	 *             if (!isValidDiagnosis(diag))
	 * @throws InvalidDisaprovementException 
	 */
	public void disapprove(Doctor from, Diagnose replacement)
			throws InvalidDoctorException, InvalidDiagnoseException, InvalidDisaprovementException {
		if(!canBeReplacedWith(replacement))
			throw new InvalidDisaprovementException();
		if (!canGiveSecondOpinion(from))
			throw new InvalidDoctorException(
					"The given Doctor cannot give a second opinion on the selected Diagnose because he's not been asked to do so!");
		if (!isValidDiagnosis(diag))
			throw new InvalidDiagnoseException(
					"Invalid Diagnose for second opinion!"); 
		this.disapprove();
	}

	private boolean canBeReplacedWith(Diagnose replacement) {
		boolean rv = this.secopDoc.equals(replacement.attending);
		rv&= (this.attending.equals(replacement.secopDoc));
		rv&= replacement.secOpFlag;
		return rv;
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
		this.disapprove();
		this.secopDoc = from;
	}

	@Basic
	public DoctorIN needsSecOpFrom() {
		DoctorIN rv = (DoctorIN)(this.secopDoc);
		return rv;
	}

	/**
	 * Unmarks this diagnosis for second opinion.
	 */
	private void unmarkForSecOp() {
		this.secOpFlag = false;
		this.secopDoc = null;
	}

	/**
	 * Approves this diagnosis.
	 * @throws ApproveDiagnoseException 
	 * 	If this diagnose was not marked for second opinion.
	 */
	public void approve() throws ApproveDiagnoseException {
		if(!isMarkedForSecOp())
			throw new ApproveDiagnoseException();
		this.approved = true;
		this.unmarkForSecOp();
	}

	/**
	 * Disapproves this diagnosis.
	 */
	@Basic
	private void disapprove() {
		this.approved = false;
	}

	@Basic
	public boolean isMarkedForSecOp() {
		return this.secOpFlag;
	}

	@Basic
	public boolean isApproved() {
		return this.approved;
	}

	/**
	 * @return True if doc is a valid doctor for this Diagnose.
	 */
	private boolean canHaveAsDoctor(Doctor doc) {
		return !(doc.equals(null));
	}

	/**
	 * @return True if diag is a valid Diagnose description for this Diagnose.
	 */
	private boolean isValidDiagnosis(String diag) {
		return !diag.equals("");
	}

	/**
	 * @return True if doc is the Doctor assigned to be giving a second opinion
	 *         on this Diagnose.
	 */
	private boolean canGiveSecondOpinion(Doctor doc) {
		return doc.equals(this.needsSecOpFrom());
	}

	/**
	 * @return true if secOp.equals(this.getDiagnosis())
	 */
	private boolean evaluateSecOp(String secOp) {
		return secOp.equalsIgnoreCase(this.getDiagnosis());
	}

	/**
	 * This method assigns an extra Treatment to this Diagnose.
	 * @param t
	 * The new Treatment.
	 * @throws InvalidTreatmentException
	 * if(!isValidTreatment(t))
	 */
	public void assignTreatment(Treatment t) throws InvalidTreatmentException {
		if (!isValidTreatment(t))
			throw new InvalidTreatmentException("Trying to associate an invalid treatment for a diagnosis!");
		treatments.add(t);
	}

	@Basic
	public String getDiagnosis() {
		return this.diag;
	}

	@Basic
	public Doctor getAttending() {
		return this.attending;
	}

	@Basic
	public Collection<TreatmentIN> getTreatments() {
		return new ArrayList<TreatmentIN>(treatments);
	}

	@Override
	public String toString() {
		return this.getDiagnosis();
	}

	/**
	 * @return True if t is a valid treatment for this Diagnose.
	 */
	private boolean isValidTreatment(Treatment t) {
		return t != null;
	}


	public void disaprove(DiagnoseIN replacement) throws ApproveDiagnoseException {
		if(!isMarkedForSecOp())
			throw new ApproveDiagnoseException();
		this.approved=false;
		this.secOpFlag=false;
		this.attending=null;
		this.secopDoc=null;
		
}
	

}
