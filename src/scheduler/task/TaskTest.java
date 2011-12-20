package scheduler.task;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import patient.Diagnose;
import scheduler.EndTimePoint;
import scheduler.HospitalDate;
import scheduler.StartTimePoint;
import scheduler.TimeSlot;
import users.Doctor;
import users.Nurse;
import users.UserManager;
import exceptions.*;

public class TaskTest
{

	UserManager m;
	LinkedList<Schedulable> listOfNurses;
	LinkedList<LinkedList<Schedulable>> listoflist;
	LinkedList<Integer> occurences;

	@Before
	public void create() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException {
		m = new UserManager();
		m.createNurse("Jenny");
		m.createNurse("Jill");
		listOfNurses = new LinkedList<Schedulable>(m.getAllNurses());
		listoflist = new LinkedList<LinkedList<Schedulable>>();
		listoflist.add(listOfNurses);
		occurences = new LinkedList<Integer>();
		occurences.add(1);
	}

	@Test
	public void simpletask() throws QueueException, InvalidDurationException,
			InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidNameException, InvalidResourceException,
			InvalidOccurencesException, InvalidRequirementException {
		LinkedList<LinkedList<Schedulable>> r = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> zero = new LinkedList<Schedulable>();
		zero.add(new Nurse("jenny"));
		r.add(zero);
		LinkedList<Integer> l = new LinkedList<Integer>(Arrays.asList(1));
		UnscheduledTask simpletask = new UnscheduledTask(r, 10l,
				new LinkedList<Requirement>(), l);
		TaskManager m = new TaskManager();
		m.addTask(simpletask);
		m.updateQueue();
	}

	@Test
	public void testIfCollectionsGetCopied() throws InvalidNameException,
			InvalidTimeSlotException, QueueException, InvalidDurationException,
			InvalidSchedulingRequestException, InvalidResourceException,
			InvalidOccurencesException, InvalidRequirementException {
		LinkedList<LinkedList<Schedulable>> r = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> zero = new LinkedList<Schedulable>();
		zero.add(new Nurse("jenny"));
		r.add(zero);
		LinkedList<Integer> l = new LinkedList<Integer>(Arrays.asList(1));
		UnscheduledTask simpletask = new UnscheduledTask(r, 10l,
				new LinkedList<Requirement>(), l);
		TaskManager m = new TaskManager();
		m.addTask(simpletask);
		HashMap<ScheduledTask, HospitalDate> scheduled = m.updateQueue();
		Collection<ScheduledTask> tasks = scheduled.keySet();
		ScheduledTask theTask = null;
		for (ScheduledTask t : tasks)
			theTask = t;

		assertTrue(theTask.getResources() != zero);
		Iterator<Schedulable> _1 = zero.iterator();
		Iterator<Schedulable> _2 = theTask.getResources().iterator();
		while (_1.hasNext()) {
			assertTrue(_1.next() == _2.next());
		}
	}

	@Test
	public void durationGetterTest() throws InvalidResourceException {
		ScheduledTask t = new ScheduledTask(listOfNurses, new TimeSlot(
				new StartTimePoint(new HospitalDate()), new EndTimePoint(
						new HospitalDate(1))));
		assertTrue(t.getDuration() == 1);
	}

	@Test(expected = InvalidResourceException.class)
	public void scheduledTaskFailTest() throws InvalidResourceException {
		ScheduledTask t = new ScheduledTask(null, new TimeSlot(
				new StartTimePoint(new HospitalDate()), new EndTimePoint(
						new HospitalDate(1))));
		assertTrue(t.getDuration() == 1);
	}
	
	@Test(expected = InvalidResourceException.class)
	public void unScheduledTaskConstructorFailTest0() throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException {
		new UnscheduledTask(null,0,null,null);

	}
	
	@Test(expected = InvalidDurationException.class)
	public void unScheduledTaskConstructorFailTest1() throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException {
		new UnscheduledTask(listoflist,-1,null,null);
	}
	
	@Test(expected = InvalidRequirementException.class)
	public void unScheduledTaskConstructorFailTest2() throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException {
		new UnscheduledTask(listoflist,1,null,null);
	}
	
	@Test(expected = InvalidOccurencesException.class)
	public void unScheduledTaskConstructorFailTest3() throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException {
		new UnscheduledTask(listoflist,1,new LinkedList<Requirement>(),null);
	}
	
	@Test
	public void requirements() throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException, InvalidDoctorException, InvalidDiagnoseException, InvalidNameException, InvalidTimeSlotException {
		Diagnose d = new Diagnose(new Doctor("Frits"), "yay!");
		d.markForSecOp(new Doctor("Bobby"));
		LinkedList<Requirement> lr = new LinkedList<Requirement>(Arrays.asList(d));
		UnscheduledTask t = new UnscheduledTask(listoflist,1,lr,occurences);
		assertFalse(t.canBeScheduled());
		d.approve();
		assertTrue(t.canBeScheduled());
		
		Diagnose d2 = new Diagnose(new Doctor("Frits2"), "xfdghyay!");
		d2.markForSecOp(new Doctor("Bobby2"));
		t.addRequirement(d2);
		assertTrue(d.isReady());
		assertFalse(d2.isReady());
		assertFalse(t.canBeScheduled());
		d2.approve();
		assertTrue(t.canBeScheduled());
		
		t.removeRequirement(d2);
		assertTrue(t.canBeScheduled());
		assertFalse(t.hasRequirement(d2));
	}
	
	
	@Test(expected = InvalidRequirementException.class)
	public void requirements2() throws InvalidResourceException, InvalidDurationException, InvalidOccurencesException, InvalidRequirementException, InvalidDoctorException, InvalidDiagnoseException, InvalidNameException, InvalidTimeSlotException {
		LinkedList<Requirement> lr = new LinkedList<Requirement>();
		UnscheduledTask t = new UnscheduledTask(listoflist,1,lr,occurences);
		t.addRequirement(null);
	}
	

}
