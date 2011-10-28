package users;

import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.Appointment;
import scheduler.Scheduler;

public class Nurse extends User
{
	private PatientFileManager pfm;
	public Nurse(String name, PatientFileManager pfm) {
		super(name, "Nurse");
		this.pfm = pfm;
	}

	/**
	 * 
	 * @param patient
	 */
	public void registerPatient(PatientFile patientFile) {
		pfm.registerPatient(patientFile);
	}
	
	public String getPatients(){
		String patientFiles = pfm.getPatientFilesAsString();
		patientFiles = patientFiles.concat(pfm.getPatientFileSize() + ". The patient is not registered yet.");
		return patientFiles;
	}
	
	public String getDoctors(UserManager usm){
		return usm.getDoctors();
	}
	
	public Doctor getDoctor(String doctorName, UserManager usm) throws NullPointerException{
		return usm.getDoctor(doctorName);
	}

	public Appointment makeAppointment(PatientFile patient, Doctor doctor, Scheduler appointmentScheduler) {
		Appointment appointment = appointmentScheduler.addAppointment(patient, doctor);
		return appointment;
	}
}
