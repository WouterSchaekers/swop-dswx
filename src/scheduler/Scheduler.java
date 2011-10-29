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
	public Scheduler() {
		appointments = new HashMap<Date, ArrayList<Appointment>>();
		medicalTests = new HashMap<Date, ArrayList<MedicalTest>>();
		treatments = new HashMap<Date, ArrayList<Machine>>();
	}

	private HashMap<Date, ArrayList<Appointment>> appointments;
	private HashMap<Date, ArrayList<MedicalTest>> medicalTests;
	private HashMap<Date, ArrayList<Machine>> treatments;
	private static int appointmentDuration = 30;

	public Appointment addAppointment(PatientFile patient, Doctor doctor) {
		Date nextDate = getNextAppointmentMoment(doctor);
		Appointment appointment = new Appointment(patient, doctor, nextDate);
		appointments.get(doctor).add(appointment);
		return appointment;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Appointment> getAppointments(Doctor doctor) {
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

	public Date getLatestAppointmentMoment(Doctor doctor) {
		Date latestDate = null;
		ArrayList<Appointment> doctorAppointments = getAppointments(doctor);
		for (Appointment appointment : doctorAppointments) {
			Date currentDate = appointment.getDate();
			if (currentDate.after(latestDate)) {
				latestDate = currentDate;
			}
		}
		return latestDate;
	}

	public Date getNextAppointmentMoment(Doctor doctor) {
		Date latestDate = getLatestAppointmentMoment(doctor);
		Date newDate = DateCalculator.addTimeToDate(latestDate,
				appointmentDuration * 60);
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