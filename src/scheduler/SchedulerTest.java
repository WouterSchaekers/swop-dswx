package scheduler;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import scheduler.*;
import scheduler.task.Schedulable;
import scheduler.task.ScheduledTask;
import users.UserManager;
import exceptions.*;

public class SchedulerTest
{

	UserManager m;
	LinkedList<Schedulable> listOfDoctors;
	LinkedList<Schedulable> listOfNurses;
	LinkedList<LinkedList<Schedulable>> listOfSchedulables;
	LinkedList<Integer> occurences;
	LinkedList<Integer> fullOccurences;

	@Before
	public void create() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException {
		m = new UserManager();
		m.createDoctor("Jonathan");
		m.createDoctor("Jeffrey");
		m.createDoctor("Jack");
		m.createDoctor("Jonas");
		m.createNurse("Jenny");
		m.createNurse("Jill");
		//
		// ###########################################
		// # #J #
		// ###########################################
		// Jennifer
		// Joanne
		// Jenna
		// Jasper
		// Joe
		// Jeroen
		//

		listOfDoctors = new LinkedList<Schedulable>(m.getAllDoctors());
		listOfNurses = new LinkedList<Schedulable>(m.getAllNurses());
		listOfSchedulables = new LinkedList<LinkedList<Schedulable>>();
		listOfSchedulables.add(listOfDoctors);
		listOfSchedulables.add(listOfNurses);
		occurences = new LinkedList<Integer>();
		occurences.add(1);
		occurences.add(2);
		fullOccurences = new LinkedList<Integer>();
		fullOccurences.add(0);
		fullOccurences.add(1);
		fullOccurences.add(1);
		new Scheduler().setNewSystemTime(HospitalDate.START_OF_TIME);
	}

	@Test
	public void makeTreeMatrixTest() {
		boolean[][] treeMatrix = new Scheduler().makeTreeMatrix(listOfSchedulables,
				fullOccurences);
		assertTrue(treeMatrix.length == 3);
		assertTrue(treeMatrix[0].length == 4);
		assertTrue(treeMatrix[1].length == 2);
		assertTrue(treeMatrix[2].length == 2);
	}

	@Test
	public void schedule0Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException, InvalidResourceException {
		listOfNurses.get(0).scheduleAt(
				new TimeSlot(new StartTimePoint(HospitalDate.START_OF_TIME),
						new StopTimePoint(HospitalDate.START_OF_TIME
								.getTimeSinceStart() + 5000)));
		new Scheduler().schedule(5000, listOfSchedulables,occurences).getTimeSlot();
	}

	@Test
	public void sschedule1Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses) {
			s.scheduleAt(new TimeSlot(new StartTimePoint(
					HospitalDate.START_OF_TIME), new StopTimePoint(
					HospitalDate.START_OF_TIME.getTimeSinceStart() + 5000)));
		}
		assertFalse(new Scheduler().schedule(5000, listOfSchedulables, occurences)
				.getTimeSlot().getStartPoint().getDate()
				.equals(new HospitalDate()));
	}

	@Test
	public void sschedule2Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses) {
			s.scheduleAt(new TimeSlot(new StartTimePoint(
					HospitalDate.START_OF_TIME), new StopTimePoint(
					HospitalDate.START_OF_TIME.getTimeSinceStart() + 5000)));
		}
		occurences = new LinkedList<Integer>();
		occurences.add(0);
		occurences.add(2);
		assertFalse(new Scheduler().schedule(5000, listOfSchedulables, occurences)
				.getStartDate().equals(new HospitalDate()));
	}

	@Test
	public void sschedule3Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses) {
			s.scheduleAt(new TimeSlot(new StartTimePoint(
					HospitalDate.START_OF_TIME), new StopTimePoint(
					HospitalDate.START_OF_TIME.getTimeSinceStart() + 5000)));
		}
		for (Schedulable s : listOfDoctors) {
			s.scheduleAt(new TimeSlot(new StartTimePoint(
					HospitalDate.START_OF_TIME), new StopTimePoint(
					HospitalDate.START_OF_TIME.getTimeSinceStart() + 5000)));
		}
		occurences = new LinkedList<Integer>();
		occurences.add(2);
		occurences.add(2);
		assertFalse(new Scheduler().schedule(5000, listOfSchedulables, occurences)
				.getTimeSlot().getStartPoint().getDate()
				.equals(new HospitalDate()));
	}

	@Test(
			expected = InvalidSchedulingRequestException.class)
	public void sschedule4Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		occurences = new LinkedList<Integer>();
		occurences.add(5);
		occurences.add(5);
		new Scheduler().schedule(5000, listOfSchedulables, occurences);
		throw new IllegalStateException("Something went wrong....");
	}

	@Test
	public void sschedule5Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses)
			makeBusyAt(s,0, 5000);
		
		for (Schedulable s : listOfDoctors)
			makeBusyAt(s,0, 2300);
	
		occurences = new LinkedList<Integer>();
		occurences.add(4);
		occurences.add(1);
		assertFalse(new Scheduler().schedule(5000, listOfSchedulables, occurences)
				.getTimeSlot().getStartPoint().getDate()
				.equals(new HospitalDate()));
	}

	@Test
	public void sschedule6Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		
		makeBusyAt(listOfNurses.get(0),1500, 5000);
		for (Schedulable s : listOfDoctors) {
			makeBusyAt(s,5500, 8000);
		}
		occurences = new LinkedList<Integer>();
		occurences.add(4);
		occurences.add(2);
		assertTrue(new Scheduler()
				.schedule(1501, listOfSchedulables, occurences)
				.getStartDate()
				.equals(new HospitalDate(HospitalDate.START_OF_TIME
						.getTimeSinceStart() + 8000)));
	}

	@Test
	public void sschedule7Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		
		makeBusyAt(listOfDoctors.get(0),1500, 5000);
		for (Schedulable s : listOfNurses) {
			makeBusyAt(s,5500, 8000);
		}
		
		occurences = new LinkedList<Integer>();
		occurences.add(4);
		occurences.add(2);
		assertTrue(new Scheduler()
				.schedule(1501, listOfSchedulables, occurences)
				.getStartDate()
				.equals(new HospitalDate(HospitalDate.START_OF_TIME
						.getTimeSinceStart() + 8000)));
	}
	
	@Test
	public void sschedule8Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		occurences = new LinkedList<Integer>();
		occurences.add(4);
		occurences.add(2);
		LinkedList<Schedulable> jonathan = new LinkedList<Schedulable>();
		jonathan.add(listOfDoctors.get(0));
		ScheduledTask t = new Scheduler().schedule(1500, listOfSchedulables, jonathan, occurences);
		assertTrue(t.getStartDate().equals(new HospitalDate(HospitalDate.START_OF_TIME
						.getTimeSinceStart())));
		assertTrue(t.getResources().contains(jonathan.get(0)));
	}

	@Test
	public void sschedule9Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException, InvalidResourceException{
			occurences = new LinkedList<Integer>();
			LinkedList<LinkedList<Schedulable>> things =  new LinkedList<LinkedList<Schedulable>>();
			things.add(listOfDoctors);
			occurences.add(2);
			things.add(listOfNurses);
			occurences.add(1);
			ScheduledTask t = new Scheduler().schedule(50000,things, occurences);
			LinkedList<LinkedList<Schedulable>> nurses = new LinkedList<LinkedList<Schedulable>>();
			nurses.add(listOfNurses);
			occurences = new LinkedList<Integer>();
			occurences.add(2);
			System.out.println(new Scheduler().schedule(50000, nurses, occurences).getStartDate());
			}
	@Test(expected = IllegalArgumentException.class)
	public void setSystemTimeTest0() {
		new Scheduler().setNewSystemTime(new HospitalDate(-1));

	}
	
	private void makeBusyAt(Schedulable s, long startMillis, long stopMillis) throws InvalidSchedulingRequestException {
		s.scheduleAt(
				new TimeSlot(
						new StartTimePoint(HospitalDate.START_OF_TIME
								.getTimeSinceStart() + startMillis),
						new StopTimePoint(HospitalDate.START_OF_TIME
								.getTimeSinceStart() + stopMillis)));
	}
	

}