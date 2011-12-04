package patient;

import java.util.ArrayList;
import java.util.Collection;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.*;
import treatment.Treatment;
import users.Doctor;
/**
 * This class represents a diagnosis. It can insert, update and read the
 * diagnosis of a certain patient. It can mark a diagnosis for second opinion
 * and remembers if the diagnosis has been approved or not.
 */
public class Diagnose
{

	private String diag = ""; // the diagnosis
	private boolean approved = false; // whether or not this diag has been
										// approved
	private boolean secOpFlag = false; // flag for second opinion
	private Doctor attending = null; // the attending doctor
	private Doctor secopDoc = null; // the doctor to give second opinion
	// the  treatments associated with this  diagnosis
	private Collection<Treatment> treatments = new ArrayList<Treatment>(); 

	/**
	 * This function allows a diagnosis to be created.
	 * 
	 * @param doc
	 *            The doctor who made the diagnosis.
	 * @param diag
	 *            The diagnosis made by the doctor.
	 * @return The created diagnosis.
	 * @throws InvalidDoctorException
	 *             If !isValidDoc(doc)
	 * @throws InvalidDiagnoseException
	 *             if !isValidDiagnosis(diag)
	 */
	public Diagnose createDiag(Doctor doc, String diag)
			throws InvalidDoctorException, InvalidDiagnoseException {
		if (!this.canHaveAsDoctor(doc))
			throw new InvalidDoctorException("Doctor is invalid!");
		if (!this.isValidDiagnosis(diag))
			throw new InvalidDiagnoseException("Diagnosis is invalid!");

		Diagnose returnval = new Diagnose();
		this.attending = doc;
		this.diag = diag;
		return returnval;
	}

	/**
	 * This function approves or disapproves this diagnosis after second
	 * opinion.
	 * 
	 * @param from
	 *            The doctor trying to give a second opinion on this diagnosis.
	 * @param diag
	 *            The second opinion this doctor is giving.
	 * @throws InvalidDoctorException
	 *             if (!canGvieSecondOpionion(from))
	 * @throws InvalidDiagnoseException
	 *             if (!isValidDiagnosis(diag))
	 */
	public void giveSecOp(Doctor from, String diag)
			throws InvalidDoctorException, InvalidDiagnoseException {
		if (!canGiveSecondOpinion(from))
			throw new InvalidDoctorException(
					"The associated doctor cannot give a second opinion on the selected diagnose as he's not been asked to do so.");
		if (!isValidDiagnosis(diag))
			throw new InvalidDiagnoseException(
					"Invalid diagnosis for second opinion!");
		if (evaluateSecOp(diag))
			this.approve();
		else
			this.disapprove();
	}

	/**
	 * This function marks this diagnosis for review by a second opinion.
	 * 
	 * @param from
	 *            The doctor that needs to give the second opinion.
	 * @throws InvalidDoctorException
	 *             if !canHaveAsDoctor(from)
	 */
	public void markForSecOp(Doctor from) throws InvalidDoctorException {
		if (!canHaveAsDoctor(from))
			throw new InvalidDoctorException(
					"Invalid doctor given to mark for second opinion!");
		this.secOpFlag = true;
		this.disapprove();
		secopDoc = from;
	}

	/**
	 * @return The doctor assigned by the attending to give a second opinion on
	 *         this diagnosis.
	 */
	public Doctor needsSecOpFrom() {
		Doctor rv = this.secopDoc;
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
	 * 
	 * @return A string containing useful information for the user.
	 */
	public void approve() {
		this.approved = true;
		this.unmarkForSecOp();
	}

	/**
	 * Disapproves this diagnosis.
	 */
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

	private boolean canHaveAsDoctor(Doctor doc) {
		return !(doc.equals(null));
	}

	private boolean isValidDiagnosis(String diag) {
		if (diag.equals(""))
			return false;
		return true;
	}

	private boolean canGiveSecondOpinion(Doctor doc) {
		return doc.equals(this.needsSecOpFrom());
	}

	/**
	 * 
	 * @param secOp
	 * @return true if secOp.equals(this.getDiagnosis())
	 */
	private boolean evaluateSecOp(String secOp) {
		return secOp.equalsIgnoreCase(this.getDiagnosis());
	}

	public void prescribeTreatment(Treatment t)
			throws InvalidTreatmentException {
		if (!isValidTreatment(t))
			throw new InvalidTreatmentException(
					"Trying to associate an invalid treatment for a diagnosis!");
		treatments.add(t);
	}

	@Basic
	public String getDiagnosis() {
		return ((((((((((((((((((((((((((((((((((((((((((((((((((diag))))))))))))))))))))))))))))))))))))))))))))))))));
	}

	@Basic
	public Doctor getAttending() {
		return this.attending;
	}

	public Collection<Treatment> getTreatments() {
		Collection<Treatment> rv = new ArrayList<Treatment>(treatments);
		return rv;
	}

	@Override
	public String toString() {
		return this.getDiagnosis();
	}

	private boolean isValidTreatment(Treatment t) {
		return t != null;
	}
}
