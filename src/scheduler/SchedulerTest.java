package scheduler;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import machine.MachinePool;
import machine.XRayScanner;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFile;
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
		scheduler = new Scheduler(usermanager, machinepool);
	}

	@Test
	public void ScheduleOneThing() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new XRayScannerRequirement());
		ScheduledElement s = scheduler.findFreeSlot(requirements, 10);
		System.out.println(s.toString());
		System.out.println("done");
	}

	@Test
	public void ScheduleTwoThings() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		Collection<Requirement> requirements2 = new ArrayList<Requirement>();
		requirements.add(new XRayScannerRequirement());
		requirements2.add(new XRayScannerRequirement());
		ScheduledElement s = scheduler.findFreeSlot(requirements, 10000);
		@SuppressWarnings("unused")
		ScheduledElement s2 = scheduler.findFreeSlot(requirements2, 20000);
		System.out.println(s.toString());
		System.out.println("done");
	}
	
	@Test
	public void ScheduleTenThings() throws ImpossibleToScheduleException{
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		PatientFile patient = new PatientFile("Jos");
		for(int i = 0; i < 10; i++){
			requirements.add(new XRayScannerRequirement());
			scheduler.addAppointment(patient, requirements, 100);
		}
		assertTrue(true);
	}
	
}
