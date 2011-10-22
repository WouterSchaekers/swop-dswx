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

	/**
	 * This function allows to update or insert the diagnosis of a patient.
	 * 
	 * @param changer
	 *            The doctor responsible for the change.
	 * @param diag
	 *            The new diagnosis for the patient.
	 * @param patientfile
	 *            The patientfile of which the diagnosis needs to be updated.
	 * @param secOp
	 * @throws IllegalStateException
	 *             If the doctor trying to update the diagnosis of a patient has
	 *             not openend the patient file.
	 * @return Returns a string containing some information for the user: e.g.
	 *         "diagnosis updated".
	 * @post A couple of important notes for the UCHandler: If the diagnosis has
	 *       already been approved, the function will ask for confirmation. <br>
	 *       A positive answer to that <u><b>MUST</b></u> be followed up with a
	 *       <b>this.disapprove()</b>. <br>
	 * <br>
	 *       The function will also ask if the user needs a <i>second
	 *       opinion</i> on this diagnosis. <br>
	 *       If the changer needs this, <b>this.markForSecOp()</b> needs to be
	 *       executed! <br>
	 *       If, however, the answer is negative, then <b>this.approve()</b>
	 *       must be executed!
	 * 
	 */
	public String updateDiag(Doctor changer, String diag, PatientFile patientfile) 
		throws IllegalStateException, IllegalAccessException {

		// check if the changer has the patientfile open
		if (!new PatientFileManager().doctorHasFileOpened(changer, patientfile))
			throw new IllegalStateException("Diagnosis not updated! Doctor has not opened the associated patientfile!");
		// see if the attending doctor has already been filled in.
		this.attending = (this.getAttending() == null) ? changer : this.attending;

		// if changer is not the attending doctor, that means he's giving a second opinion.
		if (!this.getAttending().equals(changer))
			return giveSecOp(diag, changer, patientfile);

		if (!this.isApproved()) { // diagnosis hasn't been approved yet -> allow change
			this.diag = diag;
			return "The diagnosis has been updated. Would you like to flag it for second opinion?";
			// if "n" -> this.approve()
			// else -> this.markForSecOp()
		} else { // diagnosis has already been approved; ask for confirmation
			this.disapprove();
			return "The current diagnosis '"
					+ this.getDiagnosis()
					+ "' has already been approved.\nAre you sure you want to update it? ";
			// if "y" -> run this.disapprove() and run function again with the correct params.
			// else -> no-op
		}

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
	public String giveSecOp(String secOp, Doctor doc, PatientFile patientfile) throws IllegalAccessException, IllegalStateException {
		// the attending doctor cannot give a second opinion on his own diagnosis
		if (this.getAttending() == doc) throw new IllegalAccessException("Doctor of the second opinion cannot be the attending doctor!");
		// this diagnosis needs to marked for second opinion before one can be given
		if(!this.isMarkedForSecOp()) throw new IllegalAccessException("This diagnosis does not need a second opinion!");
		// the patientfile needs to be opened by the doctor
		if(!new PatientFileManager().doctorHasFileOpened(doc, patientfile)) throw new IllegalStateException("Diagnosis not updated! Doctor has not opened the associated patientfile!");
		
		if (secOp.equalsIgnoreCase(this.getDiagnosis())) {
			// the second opinion is the same as the first opinion -> approve diagnosis and unflag for second opinion
			this.approve();
			this.unmarkForSecOp();
			return "Original diagnosis was successfully approved by second opinion!";
		}
		this.disapprove();
		this.updateDiag(this.getAttending(), this.getDiagnosis() + "/" + secOp + " by " + this.getAttending().toString() + "/" + doc.toString(), patientfile);
		this.markForSecOp();
		return "Original diagnosis was not the same as the second opinion.\nDiagnosis was not approved.\nDiagnosis has been adjusted accordingly.\nDiagnosis has been marked for second opinion.";
	}

	/**
	 * This function marks this diagnosis for review by a second opinion.
	 * @return A string containing useful information for the user.
	 */
	public String markForSecOp() {
		this.secOpFlag = true;
		this.disapprove();
		return "Diagnosis has been successfully marked for second opinion. The diagnosis has been disapproved.";
	}
	
	/**
	 * Unmarks this diagnosis for second opinion.
	 */
	private void unmarkForSecOp() {
		this.secOpFlag = false;
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
	 * @return A string containing useful information for the user.
	 */
	public String approve() {
		this.approved = true;
		this.unmarkForSecOp();
		return "Diagnosis sucessfully approved!";
	}

	/**
	 * Disapproves this diagnosis.
	 */
	public void disapprove() {
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
