package patient;

import diagnosis.Diagnosis;

public class PatientFile
{

	private String name = "";
	private Diagnosis diagnosis = new Diagnosis();

	/**
	 * 
	 * @param patient
	 *            The name of the patient to whom this file belongs to.
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
	 * 
	 * @return
	 * The Diagnosis of this patient (has .toString()) -> printfriendly.
	 */
	public Diagnosis getDiagnosis() {
		return this.diagnosis;
	}

}
