package scheduler;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import machine.MachinePool;
import machine.XRayScanner;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFile;
import task.requirement.AresourceRequirement;
import task.requirement.Requirement;
import users.UserManager;

public class SchedulerTest
{
	MachinePool machinepool;
	UserManager usermanager;
	Scheduler scheduler;

	@Before
	public void intialize() {
		machinepool = new MachinePool();
		usermanager = new UserManager();
		machinepool.addMachine(new XRayScanner("main hall"));
		//machinepool.addMachine(new XRayScanner("main Abra"));
		scheduler = new Scheduler(usermanager, machinepool);
	}

	@Test
	public void ScheduleOneThing() throws ImpossibleToScheduleException {
//		Collection<Requirement> requirements = new ArrayList<Requirement>();
//		requirements.add(new AresourceRequirement(XRayScanner.class));
//		ScheduledElement s = scheduler.find(requirements, 10);
//		System.out.println(s.toString());
//		System.out.println("done");
	}

	@Test
	public void ScheduleTwoThings() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		Collection<Requirement> requirements2 = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements2.add(new AresourceRequirement(XRayScanner.class));
		ScheduledElement s = scheduler.find(requirements, 10000);
		ScheduledElement s2 = scheduler.find(requirements2, 20000);
		ScheduledElement s3 = scheduler.find(requirements2, 10000);
		ScheduledElement s4 = scheduler.find(requirements2, 10000);
		System.out.println(s.getDate().toString());
		System.out.println(s2.getDate().toString());
		System.out.println(s3.getDate().toString());
		System.out.println(s4.getDate().toString());
		System.out.println("done");
	}
	
	@Test
	public void ScheduleTenThings() throws ImpossibleToScheduleException{
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		PatientFile patient = new PatientFile("Jos");
		requirements.add(new AresourceRequirement(XRayScanner.class));
	//	requirements.add(new AresourceRequirement(XRayScanner.class));
		Collection<Appointment> aps=new ArrayList<Appointment>();
		for(int i = 0; i < 20;i++)
		{
			aps.add(scheduler.addAppointment(patient, requirements, 10000));
		}
		for(Appointment a : aps)
			System.out.println(a);
		
		assertTrue(true);
	}
	
}
