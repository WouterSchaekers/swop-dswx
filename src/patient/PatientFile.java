package patient;

import java.util.ArrayList;
import java.util.Collection;
import medicaltest.Result;
import treatment.Treatment;
import diagnosis.Diagnosis;

public class PatientFile
{

	private String name = "";
	private Collection<Diagnosis> diagnosis;
	private PatientFileManager pfm;
	private Treatment treatment;
	private boolean discharged = false;
	private static ArrayList<PatientFile> pf = new ArrayList<PatientFile>();

	/**
	 * Default constructor is NOT allowed!
	 */
	@SuppressWarnings("unused")
	private PatientFile() {
		throw new IllegalStateException("FATAL SYSTEM ERROR!");
	}

	/**
	 * 
	 * @param patient
	 *            The name of the patient to whom this file belongs to.
	 * @param pfm
	 *            The patient file manager for this patient file.
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
	 * @return The Diagnosis of this patient (has .toString()) -> printfriendly.
	 */
	public Collection<Diagnosis> getDiagnosis() {
		return this.diagnosis;
	}

	public PatientFileManager getPfm() {
		return pfm;
	}

	public void checkin() {
		this.discharged = false;
	}

	public void discharge() {
		this.discharged = true;
	}

	public boolean isDischarged() {
		return this.discharged;
	}

	public static ArrayList<PatientFile> getPatientList() {
		return pf;
	}

	public void addPatientFile(PatientFile p) {
		pf.add(p);
	}

	public String toString() {
		return name;
	}

	public Collection<Result> getAllResults() {
		return null;
		// TODO implement
	}
}
