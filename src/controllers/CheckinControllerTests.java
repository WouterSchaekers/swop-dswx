package controllers;

import static org.junit.Assert.*;
import machine.MachinePool;
import org.junit.*;
import patient.PatientFile;
import patient.PatientFileManager;
import scheduler.Scheduler;
import users.*;

public class CheckinControllerTests
{

	private PatientFileManager pfm;
	private CheckinController cic;
	private UserManager um;
	private LoginController lc;
	private Scheduler s;
	private Nurse n;
	private DTOUser uDTO;
	private MachinePool mp;
	private DataPasser data;
	private PatientFile pf;
	
	@Before
	public void setUp() {
		pfm = new PatientFileManager();
	}
	
	
	@Test(expected = IllegalArgumentException.class) 
	public void creationFail(){
		cic = new CheckinController(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void creationFail2(){
		LoginController lc = new LoginController(null);
		cic = new CheckinController(lc, null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void creationFail3(){
		um = new UserManager();
		pfm = new PatientFileManager();
		MachinePool mp = new MachinePool();
		s = new Scheduler(um, mp);
		DataPasser data = new DataPasser(um, pfm, s);
		LoginController lc = new LoginController(data);
		cic = new CheckinController(lc, null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void creationFail4(){
		pfm = new PatientFileManager();
		cic = new CheckinController(null, pfm);
	}
	
	@Test
	public void creationSucces() {
		um = new UserManager();
		pfm = new PatientFileManager();
		n = new Nurse("Nina");
		uDTO = new DTOUser(n);
		MachinePool mp = new MachinePool();
		s = new Scheduler(um, mp);
		DataPasser data = new DataPasser(um, pfm, s);
		lc = new LoginController(data);
		lc.logIn(uDTO);
		cic = new CheckinController(lc, pfm);
	}
	
	@Test
	public void checkInSucces(){

		pfm = new PatientFileManager();
		um = new UserManager();
		mp = new MachinePool();
		s = new Scheduler(um, mp);
		data = new DataPasser(um, pfm, s);
		lc = new LoginController(data);
		n = new Nurse("Nina");
		uDTO = new DTOUser(n);
		lc.logIn(uDTO);
		pf = new PatientFile("Katrien");
		cic = new CheckinController(lc, pfm);
		cic.checkIn(pf);
		assertTrue(!pf.isDischarged());
	}
	
	@Test(expected = IllegalStateException.class)
	public void checkInFail(){
		pfm = new PatientFileManager();
		um = new UserManager();
		mp = new MachinePool();
		s = new Scheduler(um, mp);
		data = new DataPasser(um, pfm, s);
		lc = new LoginController(data);
		
		n = new Nurse("Nina");
		uDTO = new DTOUser(n);
		lc.logIn(uDTO);
		pf = new PatientFile("Katrien");
		cic = new CheckinController(lc, pfm);
		pfm.patientIsDischarged(pf);
	}
	
	@Test
	public void checkInNewPatientSucces(){
		pfm = new PatientFileManager();
		um = new UserManager();
		mp = new MachinePool();
		s = new Scheduler(um, mp);
		data = new DataPasser(um, pfm, s);
		lc = new LoginController(data);
		n = new Nurse("Nina");
		uDTO = new DTOUser(n);
		lc.logIn(uDTO);
		
		cic = new CheckinController(lc, pfm);
		PatientFile p = cic.signUpNewPatient("Katrien");
		assertTrue(pfm.containsFileOf(p) && !pfm.patientIsDischarged(p));
	}
	
	@Test(expected = IllegalStateException.class)
	public void CheckInNewPatientFail(){
		pfm = new PatientFileManager();
		um = new UserManager();
		mp = new MachinePool();
		s = new Scheduler(um, mp);
		data = new DataPasser(um, pfm, s);
		lc = new LoginController(data);
		n = new Nurse("Nina");
		uDTO = new DTOUser(n);
		lc.logIn(uDTO);
		
		cic = new CheckinController(lc, pfm);
		PatientFile pf2 = new PatientFile("Stefaan");
		pfm.patientIsDischarged(pf2);
	}

}
