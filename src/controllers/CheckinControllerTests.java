/**
 * 
 */
package controllers;

import machine.MachinePool;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import patient.PatientFileManager;
import scheduler.Scheduler;
import users.Nurse;
import users.User;
import users.UserAlreadyExistsException;
import users.UserManager;
import users.User.usertype;

/**
 * @author Logan
 *
 */
public class CheckinControllerTests
{

	PatientFileManager pfm;
	CheckinController cic;
	UserManager um;
	LoginController lc;
	DataPasser d;
	PatientFileManager p;
	Scheduler s;
	Nurse n;
	UserDTO uDTO;
	/**
	 * @throws java.lang.Exception
	 */
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
	public void creationSucces() throws UserAlreadyExistsException{
		um = new UserManager();
		pfm = new PatientFileManager();
		n = new Nurse("Nina");
		uDTO = new UserDTO(n);
		MachinePool mp = new MachinePool();
		s = new Scheduler(um, mp);
		DataPasser data = new DataPasser(um, pfm, s);
		lc = new LoginController(data);
		lc.logIn(uDTO);
		System.out.println(lc.getUser().type());
		cic = new CheckinController(lc, pfm);
	}
	
	
	
	
	@Test
	public void checkInFail(){
		pfm = new PatientFileManager();
		
	}

}
