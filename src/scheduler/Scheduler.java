package scheduler;

import help.DateCalculator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import machine.*;
import medicaltest.*;
import patient.*;
import users.*;

public class Scheduler
{
	private TreeMap<Date, Collection<User>> staff = new TreeMap<Date, Collection<User>>();
	private TreeMap<Date, PatientFile> patients = new TreeMap<Date, PatientFile>();
	private TreeMap<Date, MedicalTest> medicalTests = new TreeMap<Date, MedicalTest>();
	private TreeMap<Date, Machine> machines = new TreeMap<Date, Machine>();
	private Date nextScheduledDate;
	private final int DAYBUFFER = 30;
	private final int INTERVALTIME = 1;
	
	public Scheduler(Date nextScheduledDate) {
//		appointments = new HashMap<Date, ArrayList<Appointment>>();
//		medicalTests = new HashMap<Date, ArrayList<MedicalTest>>();
//		treatments = new HashMap<Date, ArrayList<Machine>>();
		this.nextScheduledDate = nextScheduledDate;
	}
	
	public void addHospitalStaff(User user) throws UserAlreadyExistsException{
		Collection<Date> allDates = this.staff.keySet();
		for(Date date : allDates){
			if(this.staff.get(date) == user){
				throw new UserAlreadyExistsException(user.getName());
			}
		}
		Date endDate = new Date(nextScheduledDate.getTime() + DAYBUFFER*24*60*60*1000);
		Date thisDate = this.nextScheduledDate;
		while(endDate.after(thisDate)){
			Collection<User> dateUsers = staff.get(thisDate);
			dateUsers.add(user);
			staff.put(thisDate, dateUsers);
			thisDate = new Date(thisDate.getTime() + this.INTERVALTIME*60*1000);
		}
	}
	
	public void changeNextScheduledDate(Date newNextScheduledDate){
		Date realNewNextScheduledDate = getNextScheduledDate(newNextScheduledDate);
		Collection<Date> allDates = this.staff.keySet();
		for(Date date : allDates){
			if(realNewNextScheduledDate.after(date)){
				this.staff.remove(date);
			}
		}
		nextScheduledDate = realNewNextScheduledDate;
	}
	
	private Date getNextScheduledDate(Date date){
		long timeInMillis = this.nextScheduledDate.getTime();
		long newTimeInMillis = date.getTime();
		long differenceTime = newTimeInMillis - timeInMillis;
		if(differenceTime < 0){
			return nextScheduledDate;
		}
		else{
			if(differenceTime%(INTERVALTIME*60*1000) == 0){
				return date;
			}
			else{
				long differenceTimeInMinutes = differenceTime/(1000*60);
				return new Date((differenceTimeInMinutes+1)*60*1000);
			}
		}
	}

//	private HashMap<Date, ArrayList<Appointment>> appointments;
//	private HashMap<Date, ArrayList<MedicalTest>> medicalTests;
//	private HashMap<Date, ArrayList<Machine>> treatments;

//	public Appointment addAppointment(PatientFile patient, Doctor doctor,
//			int appointmentDuration) {
//		Date nextDate = getNextAppointmentMoment(doctor);
//		Appointment appointment = new Appointment(patient, doctor, nextDate,
//				appointmentDuration);
//		if(this.appointments.get(nextDate) == null){
//			ArrayList<Appointment> newArrayList = new ArrayList<Appointment>();
//			newArrayList.add(appointment);
//			this.appointments.put(nextDate, newArrayList);
//		}
//		else{
//			this.appointments.get(nextDate).add(appointment);
//		}
//		return appointment;
//	}

//	private ArrayList<Appointment> getAppointments(Doctor doctor) {
//		ArrayList<Appointment> doctorAppointments = new ArrayList<Appointment>();
//		Collection<Date> allDates = this.appointments.keySet();
//		for (Date curDate : allDates) {
//			ArrayList<Appointment> curAppointments = this.appointments
//					.get(curDate);
//			for (Appointment appointment : curAppointments) {
//				if (appointment.getDoctor() == doctor) {
//					doctorAppointments.add(appointment);
//				}
//			}
//		}
//		return doctorAppointments;
//	}

//	private Appointment getLatestAppointment(Doctor doctor) {
//		ArrayList<Appointment> doctorAppointments = getAppointments(doctor);
//		Appointment latestAppointment = null;
//		Date latestDate = null;
//		for (Appointment appointment : doctorAppointments) {
//			Date nextScheduledDate = appointment.getDate();
//			if (latestDate == null || nextScheduledDate.after(latestDate)) {
//				latestDate = nextScheduledDate;
//				latestAppointment = appointment;
//			}
//		}
//		return latestAppointment;
//	}

//	private Date getNextAppointmentMoment(Doctor doctor) {
//		Appointment latestAppointment = getLatestAppointment(doctor);
//		Date latestDate = latestAppointment.getDate();
//		int timeToAdd = latestAppointment.getAppointmentDuration();
//		Date newDate = DateCalculator.addTimeToDate(latestDate, timeToAdd);
//		return newDate;
//	}

//	public MedicalTest addMedicalTest(MedicalTest medicalTest) {
//		Date nextDate = getNextMedicalTestMoment(medicalTest);
//		medicalTest.setDate(nextDate);
//		if(medicalTests.get(nextDate) == null){
//			ArrayList<MedicalTest> newArrayList = new ArrayList<MedicalTest>();
//			newArrayList.add(medicalTest);
//			this.medicalTests.put(nextDate, newArrayList);
//		}
//		else{
//			this.medicalTests.get(nextDate).add(medicalTest);
//		}
//		return medicalTest;
//	}

//	private MedicalTest getLatestMedicalTest(MedicalTest medicalTest) {
//		Collection<Date> dates = this.medicalTests.keySet();
//		MedicalTest latestMedicalTest = null;
//		Date latestDate = null;
//		for (Date date : dates) {
//			ArrayList<MedicalTest> curMedicalTests = this.medicalTests.get(date);
//			for (MedicalTest curMedicalTest : curMedicalTests) {
//				if (curMedicalTest.getTestName().equals(
//						medicalTest.getTestName())) {
//					Date curDate = curMedicalTest.getDate();
//					if (latestDate == null || curDate.after(latestDate)) {
//						latestDate = curDate;
//						latestMedicalTest = curMedicalTest;
//					}
//				}
//			}
//		}
//		return latestMedicalTest;
//	}

//	private Date getNextMedicalTestMoment(MedicalTest medicalTest) {
//		MedicalTest latestMedicalTest = getLatestMedicalTest(medicalTest);
//		Date latestDate = latestMedicalTest.getDate();
//		int timeToAdd = latestMedicalTest.getTestDuration();
//		Date newDate = DateCalculator.addTimeToDate(latestDate, timeToAdd);
//		return newDate;
//	}
}