package controllers;

import machine.MachinePool;
import org.junit.*;
import exceptions.InvalidSchedulerException;
import patient.PatientFileManager;
import scheduler.Scheduler;
import task.TaskManager;
import users.Doctor;
import users.User;
import users.UserManager;

public class MedicalTestControllerTest
{
	UserManager usm;
	MachinePool mp;
	User u;
	DTOUser dtouser;
	PatientFileManager pfm;
	Scheduler scheduler;
	TaskManager tm;

	@Before
	public void setUp(){
		usm = new UserManager();
		mp = new MachinePool();
		u = new Doctor("Dude");
		dtouser = new DTOUser(u);
		pfm = new PatientFileManager();
		scheduler = new Scheduler(usm, mp);
		try{
			tm = new TaskManager(scheduler);
		}
		catch(InvalidSchedulerException e){
			System.out.println(e.getMessage() + " Check the Setup in MedicalTestController.");
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void creationFail() throws IllegalArgumentException {
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		new MedicalTestController(lc, null, dp, tm);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creationFail2() throws IllegalArgumentException {
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(
				pfm, u, lc, pfoc);
		new MedicalTestController(null, cpfc, dp, tm);
	}

	@Test(expected = NullPointerException.class)
	public void creationFail3() throws IllegalArgumentException {
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(
				pfm, u, lc, pfoc);
		new MedicalTestController(lc, cpfc, dp, tm);
	}

	@Test
	public void creationSuccess() {
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(
				pfm, u, lc, pfoc);
		new MedicalTestController(lc, cpfc, dp, tm);
	}

	@Test
	public void orderXRay() {
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(
				pfm, u, lc, pfoc);
		MedicalTestController mtc = new MedicalTestController(lc, cpfc, dp, tm);
		String bodypart = "Bionic eye";
		int amountOfImages = 666;
		int zoomLevel = 100;
		mtc.orderXRay(bodypart, amountOfImages, zoomLevel);
	}

	@Test
	public void orderUltraSound() {
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(
				pfm, u, lc, pfoc);
		MedicalTestController mtc = new MedicalTestController(lc, cpfc, dp, tm);
		String focus = "Bionic eye";
		boolean recVid = false;
		boolean recImg = false;
		mtc.orderUltraSound(focus, recVid, recImg);
	}

	@Test
	public void orderBloodAnalysis() {
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(
				pfm, u, lc, pfoc);
		MedicalTestController mtc = new MedicalTestController(lc, cpfc, dp, tm);
		String focus = "Bionic eye";
		int amount = 100;
		mtc.orderBloodAnalysis(focus, amount);
	}

}
