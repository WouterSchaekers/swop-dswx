package diagnosis;

import patient.*;
import users.Doctor;

/**
 * This class represents a diagnosis. It can insert, update and read the
 * diagnosis of a certain patient. It can mark a diagnosis for second opinion
 * and remembers if the diagnosis has been approved or not.
 * 
 * @author Stefaan
 * 
 */
public class Diagnosis
{

	private String diag = "";
	private boolean approved = false;
	private boolean secOpFlag = false;
	private Doctor attending = null;
	private Doctor secopDoc = null;

	// TODO: update docu
	/**
	 * This function allows to update or insert the diagnosis of a patient.
	 * 
	 * @param doc
	 *            The doctor responsible for the change.
	 * @param diag
	 *            The new diagnosis for the patient.
	 * @param patientfile
	 *            The patientfile of which the diagnosis needs to be updated.
	 * @param secOp
	 * @return Returns a string containing some information for the user: e.g.
	 *         "diagnosis updated".
	 * 
	 */
	public Diagnosis createDiag(Doctor doc, String diag, PatientFile patientfile) {

		if (doc == null)
			throw new IllegalArgumentException("Doctor is null!");
		if (diag.equals(""))
			throw new IllegalArgumentException("Diagnosis is empty!");
		if (patientfile == null)
			throw new IllegalArgumentException("Patientfile is null!");

		Diagnosis returnval = new Diagnosis();
		this.attending = doc;
		this.diag = diag;
		return returnval;
	}

	/**
	 * This function allows a doctor (doc) to give a second opinion on this
	 * diagnosis. of a patient.
	 * 
	 * @param secOp
	 *            The diagnosis of the second opinion.
	 * @param doc
	 *            The doctor giving the second opinion.
	 * @param patientfile
	 *            The patient file of which diagnosis needs approval.
	 * @throws IllegalAccessException
	 *             if the doctor attempting to give a second opinion is the
	 *             attending doctor on this case, or if this case does not need
	 *             a second opinion
	 * @throws IllegalStateException
	 *             if the doctor attemtping to give a second opionion has not
	 *             opened the patient file
	 * @return A string containing useful information for the user.
	 * @post If the second opinion is not exactly equal (ignore case) to the
	 *       original diagnosis, then <b>updateDiag()</b> should be called by
	 *       the attending doctor to change the diagnosis. This diagnosis will
	 *       be marked for second opinion.
	 */
	public void giveSecOp(boolean validity) {
		this.approved = validity;
	}

	/**
	 * This function marks this diagnosis for review by a second opinion.
	 * 
	 * @return A string containing useful information for the user.
	 */
	public String markForSecOp(Doctor from) {
		this.secOpFlag = true;
		this.disapprove();
		secopDoc = from;
		return "Diagnosis has been successfully marked for second opinion. The diagnosis has been disapproved.";
	}

	/**
	 * Unmarks this diagnosis for second opinion.
	 */
	private void unmarkForSecOp() {
		this.secOpFlag = false;
		this.secopDoc = null;
	}

	/**
	 * @return True if this diagnosis needs a second opinion before approval.
	 */
	public boolean isMarkedForSecOp() {
		return this.secOpFlag;
	}

	/**
	 * @return True if this diagnosis has been approved already.
	 */
	public boolean isApproved() {
		return this.approved;
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

	/**
	 * @return The current diagnosis.
	 */
	public String getDiagnosis() {
		return diag;
	}

	/**
	 * @return The attending doctor on this diagnosis.
	 */
	public Doctor getAttending() {
		return this.attending;
	}

	@Override
	public String toString() {
		return this.getDiagnosis();
	}
}
