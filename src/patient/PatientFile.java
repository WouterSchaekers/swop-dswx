package patient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import medicaltest.Result;
import medicaltest.Treatment;

/**
 * This class represents the patient file of a patient. It contains all the
 * information on a certain patient e.g.: name, diagnosis, treatment,...
 */
public class PatientFile
{

	private String name = ""; // the name of the patient
	private Collection<Diagnosis> diagnosis = new ArrayList<Diagnosis>(); // all diags for this patient
	private Map<Diagnosis,Treatment> diagToTreatment = new HashMap<Diagnosis, Treatment>();
	private Treatment treatment = null; // the treatment this patient is currently on
	private boolean discharged = false; // whether or not this patient has been discharged
	Collection<Result> results; 
	public void addDiagnosis(Diagnosis D){
		this.diagnosis.add(D);
	}
	/**
	 *  
	 * @param D
	 * @param T
	 */
	public void addDiagTreatPair(Diagnosis D,Treatment T)
	{
		diagToTreatment.put(D, T);
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
	public Collection<Diagnosis> getDiagnosis() {
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
	 * @return All testresults of this patient.
	 */
	public Collection<Result> getAllResults() {
		return this.results;
	}

	/**
	 * @return The treatment this patient is currently receiving.
	 */
	public Treatment getTreatment() {
		return this.treatment;
	}
	
}
