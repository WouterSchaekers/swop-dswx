package patient;

import diagnosis.Diagnosis;

public class PatientFile
{

	private String name = "";
	private Diagnosis diagnosis = new Diagnosis();
	private PatientFileManager pfm;
	
	/**
	 * Default constructor is NOT allowed!
	 */
	@SuppressWarnings("unused")
	private PatientFile(){throw new IllegalStateException("FATAL SYSTEM ERROR!");}
	
	/**
	 * 
	 * @param patient
	 *            The name of the patient to whom this file belongs to.
	 * @param pfm
	 * 			  The patient file manager for this patient file.
	 */
	public PatientFile(String patientname, PatientFileManager pfm) {
		this.pfm = pfm;
		this.name = patientname;
		pfm.addPatientFile(this);
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
	
	public PatientFileManager getPfm() {
		return pfm;
	}

}
