package scheduler;

import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;
<<<<<<< .mine
import machine.*;
import medicaltest.*;
import patient.*;
import resources.MedicalTest;
import users.*;
=======
>>>>>>> .r122

public class Scheduler
{
<<<<<<< .mine
	private UserManager usm;
	private TreeMap<Date, Collection<User>> dateOfStaff;
	private TreeMap<Date, MedicalTest> dateOfMedicalTests;
	private Date nextScheduledDate;
	private final int DAYBUFFER = 30;
	private final int INTERVALTIME = 1;
=======
	private TreeMap<Date, Collection<Appointment>> appointments;
>>>>>>> .r122
	
<<<<<<< .mine
	public Scheduler(Date nextScheduledDate, UserManager usm) {
		this.usm = usm;
		dateOfStaff = new TreeMap<Date, Collection<User>>();
		dateOfMedicalTests = new TreeMap<Date, MedicalTest>();
		this.nextScheduledDate = nextScheduledDate;
//		appointments = new HashMap<Date, ArrayList<Appointment>>();
//		medicalTests = new HashMap<Date, ArrayList<MedicalTest>>();
//		treatments = new HashMap<Date, ArrayList<Machine>>();
=======
	public Scheduler() {
		appointments = new TreeMap<Date, Collection<Appointment>>();
>>>>>>> .r122
	}
	
}