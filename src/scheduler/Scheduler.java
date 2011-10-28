package scheduler;

import help.DateCalculator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import machine.Machine;
import medicaltest.MedicalTest;
import patient.PatientFile;
import users.Doctor;

public class Scheduler
{
	public Scheduler(){
		appointments = new HashMap<Doctor, ArrayList<Appointment>>();
		medicalTests = new HashMap<PatientFile, ArrayList<MedicalTest>>();
		treatments = new HashMap<PatientFile, ArrayList<Machine>>();
	}
	
	private HashMap<Doctor, ArrayList<Appointment>> appointments;
	private HashMap<PatientFile, ArrayList<MedicalTest>> medicalTests;
	private HashMap<PatientFile, ArrayList<Machine>> treatments;
	private static int appointmentDuration = 30;
	
	public Appointment addAppointment(PatientFile patient, Doctor doctor){
		Date nextDate = getNextAppointmentMoment(doctor);
		Appointment appointment = new Appointment(patient, doctor, nextDate);
		appointments.get(doctor).add(appointment);
		return appointment;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Appointment> getAppointments(Doctor doctor){
		return (ArrayList<Appointment>) appointments.get(doctor).clone();
	}
	
	public Date getLatestAppointmentMoment(Doctor doctor){
		Date latestDate = null;
		ArrayList<Appointment> doctorAppointments = appointments.get(doctor);
		for(Appointment appointment : doctorAppointments){
			Date currentDate = appointment.getDate();
			if(currentDate.after(latestDate)){
				latestDate = currentDate;
			}
		}
		return latestDate;
	}
	
	public Date getNextAppointmentMoment(Doctor doctor){
		Date latestDate = getLatestAppointmentMoment(doctor);
		Date newDate = DateCalculator.addTimeToDate(latestDate, appointmentDuration*60);
		return newDate;
	}

	public void addMedicalTest(PatientFile patient, MedicalTest medicalTest) {
		Collection<PatientFile> patientFileCollection = medicalTests.keySet();
		Iterator<PatientFile> patientFileIterator = patientFileCollection.iterator();
		while (patientFileIterator.hasNext()) {
			PatientFile currentPatient = (PatientFile) patientFileIterator.next();

		}
		// To be implemented
	}
}