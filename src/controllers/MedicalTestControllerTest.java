package controllers;

import machine.MachinePool;
import org.junit.*;
import patient.PatientFileManager;
import scheduler.Scheduler;
import task.TaskManager;
import users.Doctor;
import users.Nurse;
import users.User;
import users.UserManager;
import users.User.usertype;

public class MedicalTestControllerTest
{
	UserManager usm;
	MachinePool mp;
	Scheduler scheduler;
	TaskManager tsm;
	PatientFileManager pfm;
	DataPasser dp;
	LoginController lc;
	User u;
	ConsultPatientFileController cpfc;
	DTOUser dtouser;
	MedicalTestController mtc;
	
	@Before
	public void setUp(){
		usm = new UserManager();
		mp = new MachinePool();
		scheduler = new Scheduler(usm, mp);
		tsm = new TaskManager(scheduler);
		pfm = new PatientFileManager();
		dp = new DataPasser(usm, pfm, scheduler);
		lc = new LoginController(dp);
		u = new Doctor("Dude");
		cpfc = new ConsultPatientFileController(pfm, u);
		dtouser = new DTOUser(u);
		lc.logIn(dtouser);
		mtc = new MedicalTestController(lc, cpfc, dp);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void creationFail() throws IllegalArgumentException{
		new MedicalTestController(lc, null, dp);
	}
	
	@Test
	public void orderXRay(){
		String bodypart = "Bionic hand";
		int amountOfImages = 77;
		int zoomLevel = 100;
		
	}
}
