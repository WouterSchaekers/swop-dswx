package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import machine.BloodAnalyser;
import machine.MachinePool;
import machine.XRayScanner;
import org.junit.Before;
import org.junit.Test;
import exceptions.ImpossibleToScheduleException;
import exceptions.UserAlreadyExistsException;
import patient.PatientFile;
import task.requirement.AresourceRequirement;
import task.requirement.Requirement;
import users.Nurse;
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
		try {
			testNurse = usermanager.CreateNurse("jenny");
			patient = new PatientFile("Jos");
		} catch (UserAlreadyExistsException e) {
			System.out
					.println("The system is horribly broken. The developpers should cry in the corner and kill themselves.");
		}
		// TODO: fix
		// machinepool.addMachine(new XRayScanner());
		// machinepool.addMachine(new XRayScanner());
		//scheduler = new Scheduler(usermanager, machinepool);
	}

	@Test
	public void ScheduleOneThing() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		//scheduler.addAppointment(patient, requirements, 1000);
	}

	@Test
	public void ScheduleTwoThings() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		//scheduler.addAppointment(patient, requirements, 1000);
	}

	@Test
	public void ScheduleTwentyThings() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
//		for (int i = 0; i < 20; i++) {
//			scheduler.addAppointment(patient, requirements, 1000);
//		}
	}

	@Test(
			expected = ImpossibleToScheduleException.class)
	public void TooMuchXRayRequirements() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
//		scheduler.addAppointment(patient, requirements, 1000);
	}

	@Test(
			expected = ImpossibleToScheduleException.class)
	public void UnavailableRequirement() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(BloodAnalyser.class));
//		scheduler.addAppointment(patient, requirements, 1000);
	}

	@Test(
			expected = NullPointerException.class)
	public void ScheduleNullObjects() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(null);
//		scheduler.addAppointment(patient, requirements, 1000);
	}

	@Test
	public void scheduleSameDuration() throws ImpossibleToScheduleException {
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new AresourceRequirement(XRayScanner.class));
		requirements.add(new AresourceRequirement(XRayScanner.class));
//		Appointment appointment1 = scheduler.addAppointment(patient,
//				requirements, 1000);
//		Appointment appointment2 = scheduler.addAppointment(patient,
//				requirements, 1000);
////		assertTrue(appointment1.getDate().getTime() == appointment2.getDate()
//				.getTime() - 1000);
	}

	// XXX: Fix this.
	// @Test
	// public void scheduleDifferentDuration()
	// throws ImpossibleToScheduleException {
	// Collection<Requirement> requirements1 = new ArrayList<Requirement>();
	// Collection<Requirement> requirements2 = new ArrayList<Requirement>();
	// Collection<Requirement> requirements3 = new ArrayList<Requirement>();
	// requirements1.add(new AresourceRequirement(XRayScanner.class));
	// requirements2.add(new AresourceRequirement(XRayScanner.class));
	// requirements2.add(new AresourceRequirement(XRayScanner.class));
	// Appointment appointment1 = scheduler.addAppointment(patient,
	// requirements1, 1000);
	// Appointment appointment2 = scheduler.addAppointment(patient,
	// requirements2, 100000);
	// Appointment appointment3 = scheduler.addAppointment(patient,
	// requirements1, 10000);
	// System.out.println(appointment1.getDate().getTime());
	// System.out.println(appointment2.getDate().getTime());
	// System.out.println(appointment3.getDate().getTime());
	// }

}
