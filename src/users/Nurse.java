package users;

import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.Appointment;
import scheduler.AppointmentScheduler;

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
	public void registerPatient(String name) {
		pfm.registerPatient(name);
	}
	
	public String getPatients(){
		String patientFiles = pfm.getPatientFilesAsString();
		patientFiles = patientFiles.concat(pfm.getPatientFileSize() + ". The patient is not registered yet.");
		return patientFiles;
	}
	
	public PatientFile selectPatient(PatientFileManager pfm, String patient) throws NullPointerException{
		return pfm.openPatientFile(patient);
	}
	
	public String getDoctors(UserManager usm){
		return usm.getDoctors();
	}
	
	public Doctor getDoctor(String doctorName, UserManager usm) throws NullPointerException{
		return usm.getDoctor(doctorName);
	}

	public Appointment makeAppointment(PatientFile patient, Doctor doctor, AppointmentScheduler appointmentScheduler) {
		Appointment appointment = appointmentScheduler.addAppointment(patient, doctor);
		return appointment;
	}
}
