package patient;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.PatientFileIN;

/**
 * This class represents the patient file of a patient. It contains all the
 * information on a certain patient e.g.: name, diagnosis, treatment,...
 */
public class PatientFile implements PatientFileIN 
{

	private String name = ""; // the name of the patient
	private Collection<Diagnose> diagnosis = new ArrayList<Diagnose>(); // all
																			// diags
																			// for
																			// this
																			// patient
	private boolean discharged = false; // whether or not this patient has been
										// discharged

	public void addDiagnosis(Diagnose D) {
		this.diagnosis.add(D);
	}

	/**
	 * Obligatory alternative constructor.
	 * 
	 * @param patient
	 *            The name of the patient to whom this patient file belongs to.
	 * @param pfm
	 *            The patient file manager for this patient file.
	 */
	public PatientFile(String patientname) {
		this.name = patientname;
	}

	/**
	 * @return The name of this patient.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return The Diagnosis of this patient (has .toString()) -> printfriendly.
	 */
	public Collection<Diagnose> getDiagnosis() {
		return this.diagnosis;
	}

	/**
	 * This function checks in an already registered patient in the hospital.
	 */
	public void checkIn() {
		this.discharged = false;
	}

	/**
	 * This function discharges this patient.
	 */
	public void discharge() {
		this.discharged = true;
	}

	/**
	 * This function checks if this patient has been discharged.
	 * 
	 * @return true if the patient has been discharged.
	 */
	public boolean isDischarged() {
		return this.discharged;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * @return The treatment this patient is currently receiving.
	 */

}
