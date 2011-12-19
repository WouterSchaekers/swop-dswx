package scheduler.task;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import scheduler.EndTimePoint;
import scheduler.HospitalDate;
import scheduler.StartTimePoint;
import scheduler.TimeSlot;
import users.Nurse;
import users.UserManager;
import exceptions.*;

public class TaskTest
{

	UserManager m;
	LinkedList<Schedulable> listOfNurses;

	@Before
	public void create() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException {
		m = new UserManager();
		m.createNurse("Jenny");
		m.createNurse("Jill");
		listOfNurses = new LinkedList<Schedulable>(m.getAllNurses());
	}

	@Test
	public void simpletask() throws QueueException, InvalidDurationException,
			InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidNameException, InvalidResourceException,
			InvalidOccurencesException {
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
			InvalidOccurencesException {
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
}
