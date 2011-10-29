package scheduler;

import help.DateCalculator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import machine.Machine;
import medicaltest.MedicalTest;
import patient.PatientFile;
import users.Doctor;

public class Scheduler
{
	public Scheduler() {
		appointments = new HashMap<Date, ArrayList<Appointment>>();
		medicalTests = new HashMap<Date, ArrayList<MedicalTest>>();
		treatments = new HashMap<Date, ArrayList<Machine>>();
	}

	private HashMap<Date, ArrayList<Appointment>> appointments;
	private HashMap<Date, ArrayList<MedicalTest>> medicalTests;
	private HashMap<Date, ArrayList<Machine>> treatments;

	public Appointment addAppointment(PatientFile patient, Doctor doctor, int appointmentDuration) {
		Date nextDate = getNextAppointmentMoment(doctor);
		Appointment appointment = new Appointment(patient, doctor, nextDate, appointmentDuration);
		appointments.get(doctor).add(appointment);
		return appointment;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Appointment> getAppointments(Doctor doctor) {
		ArrayList<Appointment> doctorAppointments = new ArrayList<Appointment>();
		Collection<Date> allDates = this.appointments.keySet();
		for (Date curDate : allDates) {
			ArrayList<Appointment> curAppointments = this.appointments
					.get(curDate);
			for (Appointment appointment : curAppointments) {
				if (appointment.getDoctor() == doctor) {
					doctorAppointments.add(appointment);
				}
			}
		}
		return doctorAppointments;
	}

	private Appointment getLatestAppointmentMoment(Doctor doctor) {
		ArrayList<Appointment> doctorAppointments = getAppointments(doctor);
		if(doctorAppointments.size() == 0){
			return null;
		}
		Appointment latestAppointment = doctorAppointments.get(0);
		Date latestDate = latestAppointment.getDate();
		for (Appointment appointment : doctorAppointments) {
			Date currentDate = appointment.getDate();
			if (currentDate.after(latestDate)) {
				latestDate = currentDate;
			}
		}
		return latestAppointment;
	}

	public Date getNextAppointmentMoment(Doctor doctor) {
		Appointment latestAppointment = getLatestAppointmentMoment(doctor);
		Date latestDate = latestAppointment.getDate();
		int timeToAdd = latestAppointment.getAppointmentDuration();
		Date newDate = DateCalculator.addTimeToDate(latestDate, timeToAdd);
		return newDate;
	}

	// public void addMedicalTest(PatientFile patient, MedicalTest medicalTest){
	//
	// Collection<PatientFile> patientFileCollection = medicalTests.keySet();
	// Iterator<PatientFile> patientFileIterator =
	// patientFileCollection.iterator();
	// while (patientFileIterator.hasNext()) {
	// PatientFile currentPatient = (PatientFile) patientFileIterator.next();
	//
	// }
	// To be implemented
	// }
}