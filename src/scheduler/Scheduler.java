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

	public Appointment addAppointment(PatientFile patient, Doctor doctor,
			int appointmentDuration) {
		Date nextDate = getNextAppointmentMoment(doctor);
		Appointment appointment = new Appointment(patient, doctor, nextDate,
				appointmentDuration);
		if(this.appointments.get(nextDate) == null){
			ArrayList<Appointment> newArrayList = new ArrayList<Appointment>();
			newArrayList.add(appointment);
			this.appointments.put(nextDate, newArrayList);
		}
		else{
			this.appointments.get(nextDate).add(appointment);
		}
		return appointment;
	}

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

	private Appointment getLatestAppointment(Doctor doctor) {
		ArrayList<Appointment> doctorAppointments = getAppointments(doctor);
		Appointment latestAppointment = null;
		Date latestDate = null;
		for (Appointment appointment : doctorAppointments) {
			Date currentDate = appointment.getDate();
			if (latestDate == null || currentDate.after(latestDate)) {
				latestDate = currentDate;
				latestAppointment = appointment;
			}
		}
		return latestAppointment;
	}

	private Date getNextAppointmentMoment(Doctor doctor) {
		Appointment latestAppointment = getLatestAppointment(doctor);
		Date latestDate = latestAppointment.getDate();
		int timeToAdd = latestAppointment.getAppointmentDuration();
		Date newDate = DateCalculator.addTimeToDate(latestDate, timeToAdd);
		return newDate;
	}

	public MedicalTest addMedicalTest(MedicalTest medicalTest) {
		Date nextDate = getNextMedicalTestMoment(medicalTest);
		medicalTest.setDate(nextDate);
		if(medicalTests.get(nextDate) == null){
			ArrayList<MedicalTest> newArrayList = new ArrayList<MedicalTest>();
			newArrayList.add(medicalTest);
			this.medicalTests.put(nextDate, newArrayList);
		}
		else{
			this.medicalTests.get(nextDate).add(medicalTest);
		}
		return medicalTest;
	}

	private MedicalTest getLatestMedicalTest(MedicalTest medicalTest) {
		Collection<Date> dates = this.medicalTests.keySet();
		MedicalTest latestMedicalTest = null;
		Date latestDate = null;
		for (Date date : dates) {
			ArrayList<MedicalTest> curMedicalTests = this.medicalTests.get(date);
			for (MedicalTest curMedicalTest : curMedicalTests) {
				if (curMedicalTest.getTestName().equals(
						medicalTest.getTestName())) {
					Date curDate = curMedicalTest.getDate();
					if (latestDate == null || curDate.after(latestDate)) {
						latestDate = curDate;
						latestMedicalTest = curMedicalTest;
					}
				}
			}
		}
		return latestMedicalTest;
	}

	private Date getNextMedicalTestMoment(MedicalTest medicalTest) {
		MedicalTest latestMedicalTest = getLatestMedicalTest(medicalTest);
		Date latestDate = latestMedicalTest.getDate();
		int timeToAdd = latestMedicalTest.getTestDuration();
		Date newDate = DateCalculator.addTimeToDate(latestDate, timeToAdd);
		return newDate;
	}
}