package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;
import exceptions.*;
import scheduler.task.Schedulable;
import users.*;

public class SchedulerTest
{
	UserManager m;
	Scheduler s = new Scheduler();
	Collection<Collection<Schedulable>> t;
	ArrayList<Schedulable> t2;

	@Before
	public void create() throws UserAlreadyExistsException,	InvalidNameException {
		m = new UserManager();
		m.createNurse("Jenny");
		m.createNurse("Jill");
		m.createNurse("Johanne");
		m.createNurse("Janet");
		m.createDoctor("Jasper");
		s = new Scheduler();
		t =  new ArrayList<Collection<Schedulable>>();
		t2 = (ArrayList<Schedulable>) m.getAllNurses();
		t.add(t2);
	}
	
	

	@Test (expected = AssertionError.class)
	public void scheduleNurseFor1Min5Sec() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		s.setNewSystemTime(Scheduler.START_OF_TIME);
		
		long duration = Scheduler.ONE_MINUTE + Scheduler.ONE_SECOND * 5;

		Date scheduledDate = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		
		assertFalse(t2.get(0).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(1).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(t2.get(1).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(0).canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1 )));
	}

	@Test
	public void scheduleNurseFor1Hour42Min5Sec() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		long duration = Scheduler.ONE_MINUTE + Scheduler.ONE_SECOND * 5;

		Date scheduledDate = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		
		System.out.println("Appointment scheduled on: " + scheduledDate);
		System.out.println("Nurse's timetable = " +t2.get(0).getTimeTable());
		assertFalse(t2.get(0).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(1).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(1).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(0).canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1 )));
	}

	@Test
	public void schedule2NursesAtTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		long duration = Scheduler.ONE_MINUTE * 10;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration - 1 * Scheduler.ONE_SECOND, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration + 1);
		
		assertFalse(t2.get(0).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(t2.get(1).canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(t2.get(1).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(0).canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1 )));
		assertTrue(t2.get(1).canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1 )));
	}
	
	@Test
	public void schedule2NursesAtTheSameTimeForTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		long duration = Scheduler.ONE_MINUTE * 10;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration + 1);
		
		assertFalse(t2.get(0).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(t2.get(1).canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(t2.get(1).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(t2.get(0).canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1 )));
		assertTrue(t2.get(1).canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1 )));
	}

	
}
