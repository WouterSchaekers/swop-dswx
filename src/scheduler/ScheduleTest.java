package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import exceptions.*;
import scheduler.task.Schedulable;
import users.*;

public class ScheduleTest
{
	UserManager m;
	Scheduler s = new Scheduler();

	@Before
	public void create() throws UserAlreadyExistsException,	InvalidNameException {
		m = new UserManager();
		m.createNurse("Jenny");
		m.createNurse("Jill");
		m.createNurse("Johanne");
		m.createNurse("Janet");
		m.createDoctor("Jasper");
		s = new Scheduler();
	}
	
	@BeforeClass
	public void before() {
		s.setNewSystemTime(Scheduler.START_OF_TIME);
	}

	@Test
	public void scheduleNurseFor1Min5Sec() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		
		Collection<Collection<Schedulable>> t =  new ArrayList<Collection<Schedulable>>();
		ArrayList<Schedulable> t2 = (ArrayList<Schedulable>) m.getAllNurses();
		t.add(t2);
		long duration = Scheduler.ONE_MINUTE + Scheduler.ONE_SECOND * 5;

		Date scheduledDate = s.schedule(duration, t);
		System.out.println("Appointment scheduled on: " + scheduledDate);
		System.out.println("Nurse's timetable = " +t2.get(0).getTimeTable());
		assertFalse(t2.get(0).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(1).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
	}

	@Test
	public void scheduleNurseFor1Hour42Min5Sec() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		Collection<Collection<Schedulable>> t =  new ArrayList<Collection<Schedulable>>();
		ArrayList<Schedulable> t2 = (ArrayList<Schedulable>) m.getAllNurses();
		t.add(t2);
		long duration = Scheduler.ONE_MINUTE + Scheduler.ONE_SECOND * 5;

		Date scheduledDate = s.schedule(duration, t);
		System.out.println("Appointment scheduled on: " + scheduledDate);
		System.out.println("Nurse's timetable = " +t2.get(0).getTimeTable());
		assertFalse(t2.get(0).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		
	}

	@Test
	public void schedule2Nurses() {
		
	}
	
	
}
