package users;

import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.Appointment;
import scheduler.AppointmentScheduler;

public class Nurse extends User
{
	public Nurse(String name) {
		super(name, "Nurse");
	}

	public void registerPatient(PatientFile patient) {
		patient.addPatientFile(patient);
	}
	
	public String getPatients(PatientFileManager pfm){
		String patientFiles = pfm.getPatienfFiles();
		patientFiles = patientFiles.concat(pfm.getPatientFileSize() + ". The patient is not registered yet.");
		return patientFiles;
	}
	
	public PatientFile selectPatient(PatientFileManager pfm, String patient) throws NullPointerException{
		return pfm.getPatientFile(patient);
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
