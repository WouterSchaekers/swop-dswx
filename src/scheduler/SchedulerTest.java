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
	Schedulable n1;
	Schedulable n2;

	@Before
	public void create() throws UserAlreadyExistsException,	InvalidNameException {
		m = new UserManager();
		 m.createNurse("Jenny");
		n2 = m.createNurse("Jill");
		m.createNurse("Johanne");
		m.createNurse("Janet");
		m.createDoctor("Jasper");
		s = new Scheduler();
		t =  new ArrayList<Collection<Schedulable>>();
		t2 = (ArrayList<Schedulable>) m.getAllNurses();
		t.add(t2);
		n1 = t2.get(0);
		n2 = t2.get(1);
	}

	@Test
	public void scheduleNurseFor1Min() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		s.setNewSystemTime(Scheduler.START_OF_TIME);
		
		long duration = Scheduler.ONE_MINUTE;

		Date scheduledDate = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(n2.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(n1.canBeScheduledOn(new Date(endScheduledDate.getTime()),new Date(endScheduledDate.getTime() + 1)));
		assertTrue(n2.canBeScheduledOn(endScheduledDate, new Date(endScheduledDate.getTime() + 1)));
	}

	@Test
	public void scheduleNurseFor1Hour42Min5Sec() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		long duration = Scheduler.ONE_HOUR + Scheduler.ONE_MINUTE * 42 + Scheduler.ONE_SECOND * 55;

		Date scheduledDate = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(n2.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(n1.canBeScheduledOn(new Date(endScheduledDate.getTime()),new Date(endScheduledDate.getTime() + 1)));
		assertTrue(n2.canBeScheduledOn(endScheduledDate, new Date(endScheduledDate.getTime() + 1)));
	}

	@Test
	public void schedule2NursesAtTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		long duration = Scheduler.ONE_MINUTE * 10;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration - 1 * Scheduler.ONE_SECOND, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration + 1);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1 )));
		assertTrue(n2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1 )));
	}
	
	@Test
	public void schedule2NursesAtTheSameTimeForTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		long duration = Scheduler.ONE_MINUTE * 10;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration + 1);
		
		System.out.println("Appointment scheduled on: " + scheduledDate);
		System.out.println("\n\nNurse1's timetable = " +t2.get(0).getTimeTable());
		System.out.println("Nurse2's timetable = " +t2.get(1).getTimeTable());
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1 )));
		assertTrue(n2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1 )));
	}

	
	@Test
	public void schedule2NursesAtTheSameTimeForTheLongerTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		long duration = Scheduler.ONE_MINUTE * 10;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration * 2, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration + 1);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration + 1);
		
		System.out.println("Appointment scheduled on: " + scheduledDate);
		System.out.println("\n\nNurse1's timetable = " +t2.get(0).getTimeTable());
		System.out.println("Nurse2's timetable = " +t2.get(1).getTimeTable());
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1 )));
		assertTrue(n2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1 )));
	}

	
}
