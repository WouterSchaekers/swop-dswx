package controllers;

import machine.MachinePool;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFileManager;
import scheduler.Scheduler;
import users.Doctor;
import users.Nurse;
import users.UserAlreadyExistsException;
import users.UserManager;

public class ConsultPatientFileControllerTests
{

	private PatientFileManager pfm;
	private Doctor d;
	private UserManager um;
	private Nurse n;
	private LoginController lc;
	private PatientFileOpenController pfoc;
	private DataPasser data;
	private Scheduler s;
	private MachinePool mp;
	private DTOUser u;
	@SuppressWarnings("unused")
	private ConsultPatientFileController cpfc;

	
	@Before
	public void setUp() throws Exception {

		pfm = new PatientFileManager();
		mp = new MachinePool(); 
		s = new Scheduler(um, mp);
		}
	
	@Test
	public void creationSucces() throws UserAlreadyExistsException{
		um = new UserManager();
		d = um.CreateDoctor("Jef");
		u = new DTOUser(d);
		data = new DataPasser(um, pfm, s); 
		lc = new LoginController(data);	
		lc.logIn(u);
		pfoc = new PatientFileOpenController(data, lc);
		cpfc = new ConsultPatientFileController(pfm, d,lc, pfoc);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void creationFail() throws UserAlreadyExistsException{
		um = new UserManager();
		n = um.CreateNurse("Janine");
		cpfc = new ConsultPatientFileController(pfm, n, lc, pfoc);
	}

}
	
	
	
