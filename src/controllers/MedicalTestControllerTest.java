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
	User u;
	DTOUser dtouser;
	PatientFileManager pfm;
	
	@Before
	public void setUp(){
		usm = new UserManager();
		mp = new MachinePool();
		u = new Doctor("Dude");
		dtouser = new DTOUser(u);
		pfm = new PatientFileManager();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void creationFail() throws IllegalArgumentException{
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		new MedicalTestController(lc, null, dp);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void creationFail2() throws IllegalArgumentException{
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(pfm, u, lc, pfoc);
		new MedicalTestController(null, cpfc, dp);
	}
	
	@Test(expected = NullPointerException.class)
	public void creationFail3() throws IllegalArgumentException{
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(pfm, u, lc, pfoc);
		new MedicalTestController(lc, cpfc, dp);
	}
	
	@Test
	public void creationSuccess(){
		Scheduler scheduler = new Scheduler(usm, mp);
		DataPasser dp = new DataPasser(usm, pfm, scheduler);
		LoginController lc = new LoginController(dp);
		lc.logIn(dtouser);
		TaskManager tsm = new TaskManager(scheduler);
		PatientFileOpenController pfoc = new PatientFileOpenController(dp, lc);
		ConsultPatientFileController cpfc = new ConsultPatientFileController(pfm, u, lc, pfoc);
		MedicalTestController mtc = new MedicalTestController(lc, cpfc, dp);
	}
	
}
