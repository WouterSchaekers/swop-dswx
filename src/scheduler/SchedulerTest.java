package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import exceptions.*;
import scheduler.task.Schedulable;
import users.*;

public class SchedulerTest {
	UserManager m;
	Scheduler s = new Scheduler();
	Collection<Collection<Schedulable>> t;
	Collection<Collection<Schedulable>> t4;
	ArrayList<Schedulable> t2;
	ArrayList<Schedulable> t3;
	
	Schedulable n1;
	Schedulable n2;
	Schedulable d1;
	Schedulable d2;
	Schedulable d3;

	@Before
	public void create() throws UserAlreadyExistsException,	InvalidNameException, InvalidTimeSlotException {
		m = new UserManager();
		m.createNurse("Jenny");
		m.createNurse("Jill");
		m.createDoctor("Jonathan");
		m.createDoctor("Jeffrey");
		m.createDoctor("Jack");
		m.createDoctor("Jonas");
		s = new Scheduler();
		
		t =  new ArrayList<Collection<Schedulable>>();
		t4 = new ArrayList<Collection<Schedulable>>();
		t2 = (ArrayList<Schedulable>) m.getAllNurses();
		t3 = (ArrayList<Schedulable>) m.getAllDoctors();
		
		t.add(t2);
		t4.add(t3);
		t4.add(t2);
		
		n1 = t2.get(0);
		n2 = t2.get(1);
		d1 = t3.get(0);
		d2 = t3.get(1);
		d3 = t3.get(2);
	}
	
	@BeforeClass
	public void setupScheduler() {
		Scheduler.setNewSystemTime(Scheduler.START_OF_TIME);
	}

	@Test
	public void scheduleNurseFor1Min() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = Scheduler.ONE_MINUTE;

		Date scheduledDate = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		
		assertTrue(n2.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(n2.canBeScheduledOn(endScheduledDate, new Date(endScheduledDate.getTime() + 1)));
	}

	@Test
	public void scheduleNurseFor1Hour42Min5Sec() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = Scheduler.ONE_HOUR + Scheduler.ONE_MINUTE * 42 + Scheduler.ONE_SECOND * 55;

		Date scheduledDate = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		
		assertTrue(n2.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertTrue(n2.canBeScheduledOn(endScheduledDate, new Date(endScheduledDate.getTime() + 1)));
	}

	@Test
	public void schedule2NursesAtTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = Scheduler.ONE_MINUTE * 10;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration - 1 * Scheduler.ONE_SECOND, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		assertTrue(n1.getTimeTable().getTimeSlots().size() == 1);
		
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime() - 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime())));
		assertTrue(n2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1)));
		assertTrue(n2.getTimeTable().getTimeSlots().size() == 1);
		
	}
	
	@Test
	public void schedule2NursesAtTheSameTimeForTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = Scheduler.ONE_MINUTE * 10;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		assertTrue(n1.getTimeTable().getTimeSlots().size() == 1);
		
		assertFalse(n2.canBeScheduledOn(scheduledDate, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate, new Date(endScheduledDate2.getTime() - 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate, new Date(endScheduledDate2.getTime())));
		assertTrue(n2.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate2.getTime() + 1)));
		assertTrue(n2.getTimeTable().getTimeSlots().size() == 1);
	}

	
	@Test
	public void schedule2NursesAtTheSameTimeForDifferentTimes() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = Scheduler.ONE_MINUTE * 10;
		long duration2 = duration * 2;

		Date scheduledDate = s.schedule(duration, t);
		Date scheduledDate2 = s.schedule(duration2, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration2);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		assertTrue(n1.getTimeTable().getTimeSlots().size() == 1);
		
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime() - 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime())));
		assertTrue(n2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1)));
		assertTrue(n2.getTimeTable().getTimeSlots().size() == 1);
	}
	
	@Test
	public void scheduleBusyNurseFor1Min() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = Scheduler.ONE_MINUTE;
		TimeSlot busySlot = new TimeSlot(new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime()), TimeType.start), new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime() + Scheduler.ONE_MINUTE), TimeType.stop));
		n1.scheduleAt(busySlot);
		
		Collection<Schedulable> temp = new ArrayList<Schedulable>(Arrays.asList(n1));
		Collection<Collection<Schedulable>> temp2 = new ArrayList<Collection<Schedulable>>();
		temp2.add(temp);
		
		Date scheduledDate = s.schedule(duration, temp2);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertFalse(n1.getTimeTable().getTimeSlots().get(0).overlaps(n1.getTimeTable().getTimeSlots().get(1)));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
	}
	
	@Test
	public void scheduleAnotherNurseThanTheBusyOneFor1Min() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = Scheduler.ONE_MINUTE;

		TimeSlot busySlot = new TimeSlot(new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime()), TimeType.start), new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime() + Scheduler.ONE_MINUTE * 100), TimeType.stop));
		n1.scheduleAt(busySlot);
		
		Date scheduledDate = s.schedule(duration, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
			
		assertTrue(n1.getTimeTable().getTimeSlots().get(0).equals(busySlot));
		assertFalse(n1.canBeScheduledOn(scheduledDate,endScheduledDate));
		
		assertFalse(n2.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertFalse(n2.getTimeTable().getTimeSlots().get(0).equals(busySlot));
		assertTrue(n2.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
	}

	@Test
	public void scheduleMoreThingsThanNurses() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration1 = Scheduler.ONE_MINUTE * 10;
		long duration2 = Scheduler.ONE_MINUTE * 15;
		long duration3 = Scheduler.ONE_MINUTE * 20;
 
		Date scheduledDate = s.schedule(duration1, t);
		Date scheduledDate2 = s.schedule(duration2, t);
		Date scheduledDate3 = s.schedule(duration3, t);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration1);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration2);
		Date endScheduledDate3 = new Date(scheduledDate3.getTime() + duration3);
			
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertFalse(n1.canBeScheduledOn(scheduledDate3, new Date(scheduledDate3.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate3, new Date(endScheduledDate3.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate3, new Date(endScheduledDate3.getTime())));
		assertTrue(n1.getTimeTable().getTimeSlots().size() == 2);
		assertTrue(n1.canBeScheduledOn(endScheduledDate3,new Date(endScheduledDate3.getTime() + 1)));
				
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime() - 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime())));
		assertTrue(n2.getTimeTable().getTimeSlots().size() == 1);
		assertTrue(n2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1)));
	}
	
	@Test
	public void schedule2DifferentKindsOfSchedulables() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = Scheduler.ONE_MINUTE;
		
		Date scheduledDate = s.schedule(duration, t4);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		
		assertFalse(d1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(d1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(d1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(d1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
	}
	
	@Test
	public void schedule2DifferentKindsOfSchedulablesForDifferentDurations() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = Scheduler.ONE_MINUTE;
		long duration2 = Scheduler.ONE_MINUTE * 23;
		
		Date scheduledDate = s.schedule(duration, t4);
		Date scheduledDate2 = s.schedule(duration2, t4);
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration2);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		
		assertFalse(d1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(d1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(d1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(d1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
		
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime() - 1)));
		assertFalse(n2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime())));
		assertTrue(n2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1)));
		
		assertFalse(d2.canBeScheduledOn(scheduledDate2, new Date(scheduledDate2.getTime() + 1)));
		assertFalse(d2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime() - 1)));
		assertFalse(d2.canBeScheduledOn(scheduledDate2, new Date(endScheduledDate2.getTime())));
		assertTrue(d2.canBeScheduledOn(endScheduledDate2,new Date(endScheduledDate2.getTime() + 1)));
	}
	
	@Test
	public void hybridCase1() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = Scheduler.ONE_MINUTE * 5;
		long duration2 = Scheduler.ONE_MINUTE * 23;
		long duration3 = Scheduler.ONE_MINUTE - Scheduler.ONE_SECOND;

		//busy 1: kan de eerste 1 minuut wel iets doen, maar de 9 minuten erna niks.
		//busy 2: kan de eerste minuut niks doen
		//busy 3: kan niks doen van 9 minuten tot 15 minuten
		TimeSlot busySlot = new TimeSlot(new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime() + Scheduler.ONE_MINUTE), TimeType.start), new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime() + Scheduler.ONE_MINUTE * 9), TimeType.stop));
		TimeSlot busySlot2 = new TimeSlot(new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime()), TimeType.start), new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime() + Scheduler.ONE_MINUTE), TimeType.stop));
		TimeSlot busySlot3 = new TimeSlot(new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime() + Scheduler.ONE_MINUTE * 9), TimeType.start), new TimePoint(new Date(Scheduler.getCurrentSystemTime().getTime() + Scheduler.ONE_MINUTE * 15), TimeType.stop));
		n1.scheduleAt(busySlot);
		n2.scheduleAt(busySlot2);
		d1.scheduleAt(busySlot);
		d1.scheduleAt(busySlot2);
		d2.scheduleAt(busySlot3);
		d3.scheduleAt(busySlot);
		d3.scheduleAt(busySlot2);
		d3.scheduleAt(busySlot3);
		
		System.out.println("Before:\n----------\n\n");
		System.out.println("TimeTable Nurse 1: " + n1.getTimeTable());
		System.out.println("TimeTable Nurse 2: " + n2.getTimeTable());
		System.out.println("TimeTable Doctor 1: " + d1.getTimeTable());
		System.out.println("TimeTable Doctor 2: " + d2.getTimeTable());
		System.out.println("TimeTable Doctor 3: " + d3.getTimeTable());
		System.out.println("------------------------------------");
		
		
		Date scheduledDate = s.schedule(duration, t4);
		Date scheduledDate2 = s.schedule(duration2, t4);
		Date scheduledDate3 = s.schedule(duration3, t4);
		
		System.out.println("TimeTable Nurse 1: " + n1.getTimeTable());
		System.out.println("TimeTable Nurse 2: " + n2.getTimeTable());
		System.out.println("TimeTable Doctor 1: " + d1.getTimeTable());
		System.out.println("TimeTable Doctor 2: " + d2.getTimeTable());
		System.out.println("TimeTable Doctor 3: " + d3.getTimeTable());
		System.out.println("\n\nAppointments were made at " + scheduledDate + ", " + scheduledDate2 + " and " + scheduledDate3);
		
		
		Date endScheduledDate = new Date(scheduledDate.getTime() + duration);
		Date endScheduledDate2 = new Date(scheduledDate2.getTime() + duration2);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new Date(endScheduledDate.getTime())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new Date(endScheduledDate.getTime() + 1)));
	}

	//TODO: hybrid of all previous
	//TODO: expected: exception
	//TODO: when constraints are implemented in canBeScheduledAt() update ALL the asserts!
}
