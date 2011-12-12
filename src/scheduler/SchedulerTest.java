package scheduler;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
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
	Schedulable d4;

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
		Scheduler.setNewSystemTime(HospitalDate.START_OF_TIME);
		
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
		d4 = t3.get(3);
		
	}
	/**
	 * This method contains some asserts that have to be checked a LOT!
	 * @throws InvalidTimeSlotException 
	 * @throws InvalidSchedulingRequestException 
	 */
	private void checkDefaultAsserts(Schedulable s, HospitalDate scheduledDate, HospitalDate endScheduledDate) throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		assertFalse(s.canBeScheduledOn(scheduledDate, new HospitalDate(scheduledDate.getTotalMillis() + 1)));
		assertFalse(s.canBeScheduledOn(scheduledDate, new HospitalDate(endScheduledDate.getTotalMillis() - 1)));
		assertFalse(s.canBeScheduledOn(scheduledDate, new HospitalDate(endScheduledDate.getTotalMillis())));
		assertTrue(s.canBeScheduledOn(endScheduledDate,new HospitalDate(endScheduledDate.getTotalMillis() + 1)));
	}
	
	@Test
	public void scheduleNurseFor1Min() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = HospitalDate.ONE_MINUTE;
		Collection<Schedulable> oneNurse = new ArrayList<Schedulable>();
		oneNurse.add(n1);
		Collection<Collection<Schedulable>> col = new ArrayList<Collection<Schedulable>>();
		col.add(oneNurse);
		
		HospitalDate scheduledDate = s.schedule(duration, col);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
		assertTrue(n2.canBeScheduledOn(scheduledDate, new HospitalDate(scheduledDate.getTotalMillis() + 1)));
		assertTrue(n2.canBeScheduledOn(endScheduledDate, new HospitalDate(endScheduledDate.getTotalMillis() + 1)));
	}
	

	@Test
	public void scheduleNurseFor1Hour42Min5Sec() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = HospitalDate.ONE_HOUR + HospitalDate.ONE_MINUTE * 42 + HospitalDate.ONE_SECOND * 55;

		HospitalDate scheduledDate = s.schedule(duration, t);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
		assertTrue(n2.canBeScheduledOn(scheduledDate, new HospitalDate(scheduledDate.getTotalMillis() + 1)));
		assertTrue(n2.canBeScheduledOn(endScheduledDate, new HospitalDate(endScheduledDate.getTotalMillis() + 1)));
	}

	@Test
	public void schedule2NursesAtTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = HospitalDate.ONE_MINUTE * 10;

		HospitalDate scheduledDate = s.schedule(duration, t);
		HospitalDate scheduledDate2 = s.schedule(duration - 1 * HospitalDate.ONE_SECOND, t);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		HospitalDate endScheduledDate2 = new HospitalDate(scheduledDate2.getTotalMillis() + duration);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
		checkDefaultAsserts(n2,scheduledDate2, endScheduledDate2);
	}
	
	
	
	@Test
	public void schedule2NursesAtTheSameTimeForTheSameTime() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = HospitalDate.ONE_MINUTE * 10;

		HospitalDate scheduledDate = s.schedule(duration, t);
		HospitalDate scheduledDate2 = s.schedule(duration, t);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		HospitalDate endScheduledDate2 = new HospitalDate(scheduledDate2.getTotalMillis() + duration);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
		checkDefaultAsserts(n1,scheduledDate2, endScheduledDate2);
	}

	
	@Test
	public void schedule2NursesAtTheSameTimeForDifferentTimes() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration = HospitalDate.ONE_MINUTE * 10;
		long duration2 = duration * 2;

		HospitalDate scheduledDate = s.schedule(duration, t);
		HospitalDate scheduledDate2 = s.schedule(duration2, t);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		HospitalDate endScheduledDate2 = new HospitalDate(scheduledDate2.getTotalMillis() + duration2);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
		checkDefaultAsserts(n2,scheduledDate2, endScheduledDate2);
	}
	
	@Test
	public void scheduleBusyNurseFor1Min() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = HospitalDate.ONE_MINUTE;
		TimeSlot busySlot = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis()), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE), TimeType.stop));
		n1.scheduleAt(busySlot);
		
		HospitalDate scheduledDate = s.schedule(duration, t);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
	}
	
	@Test
	public void scheduleAnotherNurseThanTheBusyOneFor1Min() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = HospitalDate.ONE_MINUTE;

		TimeSlot busySlot = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis()), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 100), TimeType.stop));
		n1.scheduleAt(busySlot);
		
		HospitalDate scheduledDate = s.schedule(duration, t);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		
		checkDefaultAsserts(n2,scheduledDate, endScheduledDate);
		assertTrue(n1.getTimeTable().getTimeSlots().get(0).equals(busySlot));
		assertFalse(n2.getTimeTable().getTimeSlots().get(0).equals(busySlot));
		assertTrue(n2.canBeScheduledOn(endScheduledDate, new HospitalDate(endScheduledDate.getTotalMillis() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, endScheduledDate));
	}

	@Test
	public void scheduleMoreThingsThanNurses() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {
		long duration1 = HospitalDate.ONE_MINUTE * 10;
		long duration2 = HospitalDate.ONE_MINUTE * 15;
		long duration3 = HospitalDate.ONE_MINUTE * 20;
 
		HospitalDate scheduledDate = s.schedule(duration1, t);
		HospitalDate scheduledDate2 = s.schedule(duration2, t);
		HospitalDate scheduledDate3 = s.schedule(duration3, t);
		HospitalDate endScheduledDate2 = new HospitalDate(scheduledDate2.getTotalMillis() + duration2);
		HospitalDate endScheduledDate3 = new HospitalDate(scheduledDate3.getTotalMillis() + duration3);
			
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate3); // TODO: check if correct
		checkDefaultAsserts(n1,scheduledDate3, endScheduledDate3);		
		checkDefaultAsserts(n2,scheduledDate2, endScheduledDate2);
	}
	
	@Test
	public void schedule2DifferentKindsOfSchedulables() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = HospitalDate.ONE_MINUTE;
		
		HospitalDate scheduledDate = s.schedule(duration, t4);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
		checkDefaultAsserts(d1,scheduledDate, endScheduledDate);
	}

	@Test
	public void schedule2DifferentKindsOfSchedulablesForDifferentDurations() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = HospitalDate.ONE_MINUTE;
		long duration2 = HospitalDate.ONE_MINUTE * 23;
		
		HospitalDate scheduledDate = s.schedule(duration, t4);
		HospitalDate scheduledDate2 = s.schedule(duration2, t4);
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		HospitalDate endScheduledDate2 = new HospitalDate(scheduledDate2.getTotalMillis() + duration2);
		
		checkDefaultAsserts(n1,scheduledDate, endScheduledDate);
		checkDefaultAsserts(d1,scheduledDate, endScheduledDate);
		checkDefaultAsserts(n2,scheduledDate2, endScheduledDate2);
		checkDefaultAsserts(d2,scheduledDate2, endScheduledDate2);
	}
	
	@Test(expected = InvalidSchedulingRequestException.class)
	public void schedule3NursesWhileOnly2InHospital() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidTimeSlotException{
		long duration = HospitalDate.ONE_HOUR;
		long duration2 = HospitalDate.ONE_MINUTE * 2;
		long duration3 = HospitalDate.ONE_MINUTE * 3;
		
		Collection<Collection<Schedulable>> maakHetStuk = new ArrayList<Collection<Schedulable>>();
		Collection<Schedulable> teWeinigNurses = new ArrayList<Schedulable>();
		teWeinigNurses.add(n1);
		teWeinigNurses.add(n2);
		maakHetStuk.add(teWeinigNurses);
		maakHetStuk.add(teWeinigNurses);
		maakHetStuk.add(teWeinigNurses);
		
		System.out.println(s.schedule(duration, t));
		System.out.println(s.schedule(duration2, t));
		System.out.println(s.schedule(duration3, t));
		
	}
	
	//@Test
	public void hybridCase1() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidSchedulingRequestException, InvalidTimeSlotException {		
		long duration = HospitalDate.ONE_MINUTE * 5;
		long duration2 = HospitalDate.ONE_MINUTE * 23;
		long duration3 = HospitalDate.ONE_MINUTE;

		//busy 1: kan de eerste 1 minuut wel iets doen, maar de 9 minuten erna niks.
		//busy 2: kan de eerste minuut niks doen
		//busy 3: kan niks doen van 9 minuten tot 15 minuten
		//busy 4: kan niks doen van 12 tot 20 minuten
		//busy 5: kan niks doen van start tot over 25 minuten
		//busy 6: kan niks doen van 50 minuten na start to 1 uur na start
		TimeSlot busySlot = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 9), TimeType.stop));
		TimeSlot busySlot2 = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis()), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE), TimeType.stop));
		TimeSlot busySlot3 = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 9), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 15), TimeType.stop));
		TimeSlot busySlot4 = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 12), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 20), TimeType.stop));
		TimeSlot busySlot5 = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis()), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 25), TimeType.stop));
		TimeSlot busySlot6 = new TimeSlot(new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_MINUTE * 50), TimeType.start), new TimePoint(new HospitalDate(Scheduler.getCurrentSystemTime().getTotalMillis() + HospitalDate.ONE_HOUR), TimeType.stop));
//		n1.scheduleAt(busySlot);
		n1.scheduleAt(busySlot6);
		n2.scheduleAt(busySlot6);
		//TODO: de nullpointer is gefixt, nu nog de invalidTimePointException o-o
//		d1.scheduleAt(busySlot);
//		d1.scheduleAt(busySlot2);
//		d2.scheduleAt(busySlot3);
//		d3.scheduleAt(busySlot);
//		d3.scheduleAt(busySlot2);
//		d3.scheduleAt(busySlot3);
		
		System.out.println("Before:\n----------\n\n");
		System.out.println("TimeTable Nurse 1: " + n1.getTimeTable());
		System.out.println("TimeTable Nurse 2: " + n2.getTimeTable());
		System.out.println("TimeTable Doctor 1: " + d1.getTimeTable());
		System.out.println("TimeTable Doctor 2: " + d2.getTimeTable());
		System.out.println("TimeTable Doctor 3: " + d3.getTimeTable());
		System.out.println("------------------------------------\n");
		
		
		HospitalDate scheduledDate = s.schedule(duration, t4);
		System.out.println("After first schedule:\n----------\n\n");
		System.out.println("TimeTable Nurse 1: " + n1.getTimeTable());
		System.out.println("TimeTable Nurse 2: " + n2.getTimeTable());
		System.out.println("TimeTable Doctor 1: " + d1.getTimeTable());
		System.out.println("TimeTable Doctor 2: " + d2.getTimeTable());
		System.out.println("TimeTable Doctor 3: " + d3.getTimeTable());
		System.out.println("------------------------------------");
		
		HospitalDate scheduledDate2 = s.schedule(duration2, t4);
		System.out.println("After second schedule:\n----------\n\n");
		System.out.println("TimeTable Nurse 1: " + n1.getTimeTable());
		System.out.println("TimeTable Nurse 2: " + n2.getTimeTable());
		System.out.println("TimeTable Doctor 1: " + d1.getTimeTable());
		System.out.println("TimeTable Doctor 2: " + d2.getTimeTable());
		System.out.println("TimeTable Doctor 3: " + d3.getTimeTable());
		System.out.println("------------------------------------");
		
		HospitalDate scheduledDate3 = s.schedule(duration3, t4);
		System.out.println("Finally:\n----------\n\n");
		System.out.println("TimeTable Nurse 1: " + n1.getTimeTable());
		System.out.println("TimeTable Nurse 2: " + n2.getTimeTable());
		System.out.println("TimeTable Doctor 1: " + d1.getTimeTable());
		System.out.println("TimeTable Doctor 2: " + d2.getTimeTable());
		System.out.println("TimeTable Doctor 3: " + d3.getTimeTable());
		System.out.println("------------------------------------");
		System.out.println("\n\nAppointments were made at " + scheduledDate + ", " + scheduledDate2 + " and " + scheduledDate3);
		
		
		HospitalDate endScheduledDate = new HospitalDate(scheduledDate.getTotalMillis() + duration);
		HospitalDate endScheduledDate2 = new HospitalDate(scheduledDate2.getTotalMillis() + duration2);
		HospitalDate endScheduledDate3 = new HospitalDate(scheduledDate3.getTotalMillis() + duration3);
		
		assertFalse(n1.canBeScheduledOn(scheduledDate, new HospitalDate(scheduledDate.getTotalMillis() + 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new HospitalDate(endScheduledDate.getTotalMillis() - 1)));
		assertFalse(n1.canBeScheduledOn(scheduledDate, new HospitalDate(endScheduledDate.getTotalMillis())));
		assertTrue(n1.canBeScheduledOn(endScheduledDate,new HospitalDate(endScheduledDate.getTotalMillis() + 1)));
	}

	//TODO: hybrid of all previous
	//TODO: expected: exception
	//TODO: when constraints are implemented in canBeScheduledAt() update ALL the asserts!
}
