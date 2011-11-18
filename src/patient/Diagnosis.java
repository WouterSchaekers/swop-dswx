package patient;

import exceptions.*;
import users.Doctor;

/**
 * This class represents a diagnosis. It can insert, update and read the
 * diagnosis of a certain patient. It can mark a diagnosis for second opinion
 * and remembers if the diagnosis has been approved or not.
 */
public class Diagnosis
{

	private String diag = ""; // the diagnosis
	private boolean approved = false; // whether or not this diag has been approved
	private boolean secOpFlag = false; // flag for second opinion
	private Doctor attending = null; // the attending doctor
	private Doctor secopDoc = null; // the doctor to give second opinion

	/**
	 * This function allow a diagnosis to be created.
	 * 
	 * @param doc
	 *            The doctor who made the diagnosis.
	 * @param diag
	 *            The diagnosis made by the doctor.
	 * @param patientfile
	 *            The file of the patient who has been diagnosed.
	 * @return This diagnosis.
	 * @throws InvalidDoctorException 
	 */
	public Diagnosis createDiag(Doctor doc, String diag, PatientFile patientfile) throws InvalidDoctorException {
		if (doc == null)
			throw new InvalidDoctorException("Doctor is null!");
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
	 * This function approves or disapproves this diagnosis after second
	 * opinion.
	 * 
	 * @param validity
	 *            Should be true when the second opinion and the original
	 *            diagnosis match.
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
	 * This function checks if this diagnosis needs a second opinion.
	 * 
	 * @return True if this diagnosis needs a second opinion before approval.
	 */
	public boolean isMarkedForSecOp() {
		return this.secOpFlag;
	}

	/**
	 * This function checks if this diagnosis has been approved.
	 * 
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

	/**
	 * @return The doctor assigned by the attending to give a second opinion on
	 *         this diagnosis.
	 */
	public Doctor needsSecOpFrom() {
		return this.secopDoc;
	}
}
