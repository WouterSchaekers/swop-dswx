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
	public void intialize() {
		machinepool = new MachinePool();
		usermanager = new UserManager();
		try{
			testNurse = usermanager.CreateNurse("jenny");
			patient = new PatientFile("Jos");
		}
		catch (UserAlreadyExistsException e){
			System.out.println("The system is horribly broken. The developpers should cry in the corner and kill themselves.");
		}
		machinepool.addMachine(new XRayScanner("Main Hall"));
		machinepool.addMachine(new XRayScanner("Stefaan's place"));
		scheduler = new Scheduler(usermanager, machinepool);
	}

	@Test
	public void ScheduleOneThing() {
		 Collection<Requirement> requirements = new ArrayList<Requirement>();
		 requirements.add(new AresourceRequirement(XRayScanner.class));
		 boolean exception = false;
		 try{
			 scheduler.addAppointment(patient, requirements, 1000);
		 }
		 catch (ImpossibleToScheduleException e){
			 exception = true;
		 }
		 assertFalse(exception);
	}

	@Test
	public void ScheduleTwoThings() {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		Collection<Requirement> requirements2 = new ArrayList<Requirement>();
		Collection<Requirement> requirements3 = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements2.add(new AresourceRequirement(XRayScanner.class));
		requirements3.add(new AspecificResourceRequirement(testNurse));
	}

	@Test
	public void ScheduleTwentyThings() {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		boolean exception = false;
		for (int i = 0; i < 20; i++) {
			try{
				scheduler.addAppointment(patient, requirements, 10000);
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
		boolean exception = false;
		try {
			scheduler.addAppointment(patient, requirements, 900000);
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
		boolean exception = false;
		try {
			scheduler.addAppointment(patient, requirements, 900000);
		} catch (ImpossibleToScheduleException e) {
			exception = true;
		}
		assertTrue(exception);
	}

}
