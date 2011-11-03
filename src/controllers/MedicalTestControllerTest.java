package controllers;

import machine.MachinePool;
import org.junit.*;
import patient.PatientFileManager;
import scheduler.Scheduler;
import task.TaskManager;
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
		u = new Nurse("Janinne");
		cpfc = new ConsultPatientFileController(pfm, u);
		mtc = new MedicalTestController(lc, cpfc, dp);
	}
	
	@Test
	public void 
}
