package scheduler;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import machine.BloodAnalyser;
import machine.MachinePool;
import machine.XRayScanner;
import org.junit.Before;
import org.junit.Test;
import patient.PatientFile;
import task.requirement.AresourceRequirement;
import task.requirement.AspecificResourceRequirement;
import task.requirement.Requirement;
import users.Nurse;
import users.UserAlreadyExistsException;
import users.UserManager;

public class SchedulerTest
{
	MachinePool machinepool;
	UserManager usermanager;
	Scheduler scheduler;
	Nurse testNurse;
	PatientFile patient;

	@Before
	public void intialize() throws UserAlreadyExistsException {
		machinepool = new MachinePool();
		usermanager = new UserManager();
		testNurse = usermanager.CreateNurse("jenny");
		patient = new PatientFile("Jos");
		machinepool.addMachine(new XRayScanner("Main Hall"));
		machinepool.addMachine(new XRayScanner("Stefaan's place"));
		scheduler = new Scheduler(usermanager, machinepool);
	}

	@Test
	public void ScheduleOneThing() throws ImpossibleToScheduleException {
		 Collection<Requirement> requirements = new ArrayList<Requirement>();
		 requirements.add(new AresourceRequirement(XRayScanner.class));
		 ScheduledElement s = scheduler.find(requirements, 10);
		 System.out.println(s.toString());
		 System.out.println("done");
	}

	@Test
	public void ScheduleTwoThings() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		Collection<Requirement> requirements2 = new ArrayList<Requirement>();
		Collection<Requirement> requirements3 = new ArrayList<Requirement>();
		requirements3.add(new AspecificResourceRequirement(testNurse));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements2.add(new AresourceRequirement(XRayScanner.class));

		ScheduledElement s = scheduler.find(requirements, 10000);
		ScheduledElement s2 = scheduler.find(requirements2, 20000);
		ScheduledElement s3 = scheduler.find(requirements2, 10000);
		ScheduledElement s4 = scheduler.find(requirements2, 10000);
		ScheduledElement s5 = scheduler.find(requirements3, 200000);
	}

	@Test
	public void ScheduleTwentyThings() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		Collection<Appointment> aps = new ArrayList<Appointment>();
		boolean exception = false;
		for (int i = 0; i < 20; i++) {
			try{
				aps.add(scheduler.addAppointment(patient, requirements, 10000));
			}
			catch(ImpossibleToScheduleException e){
				exception = true;
			}
		}
		assertFalse(exception);
	}

	@Test
	public void TooMuchXRayRequirements() {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		Collection<Appointment> aps = new ArrayList<Appointment>();
		boolean exception = false;
		try {
			aps.add(scheduler.addAppointment(patient, requirements, 900000));
		} catch (ImpossibleToScheduleException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	@Test
	public void UnavailableRequirement() {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(BloodAnalyser.class));
		Collection<Appointment> aps = new ArrayList<Appointment>();
		boolean exception = false;
		try {
			aps.add(scheduler.addAppointment(patient, requirements, 900000));
		} catch (ImpossibleToScheduleException e) {
			exception = true;
		}
		assertTrue(exception);
	}

}
