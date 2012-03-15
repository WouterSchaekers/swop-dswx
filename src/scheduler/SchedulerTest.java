package scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import schedulerold.UnscheduledTaskTestClass;
import schedulerold.task.Schedulable;
import schedulerold.task.scheduled.ScheduledTask;
import schedulerold.task.unscheduled.UnscheduledTask1;
import system.Whereabouts;
import users.Doctor;
import users.HospitalAdmin;
import users.Nurse;
import users.UserManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidRequirementException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeLordException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class SchedulerTest
{

	UserManager m;
	LinkedList<Schedulable> listOfDoctors;
	LinkedList<Schedulable> listOfNurses;
	LinkedList<LinkedList<Schedulable>> listOfSchedulables;
	LinkedList<Integer> occurences1;
	LinkedList<Integer> occurences2;
	LinkedList<Integer> occurences3;
	LinkedList<Integer> occurences4;
	LinkedList<Integer> occurences5;
	LinkedList<Integer> occurences6;
	LinkedList<Integer> occurences7;
	LinkedList<Integer> fullOccurences;
	UnscheduledTask1 unsched1;
	UnscheduledTask1 unsched2;
	UnscheduledTask1 unsched3;
	UnscheduledTask1 unsched4;
	UnscheduledTask1 unsched5;
	UnscheduledTask1 unsched6;
	UnscheduledTask1 unsched7;
	Scheduler s;

	@Before
	public void create() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException,
			InvalidResourceException, InvalidDurationException,
			InvalidOccurencesException, InvalidAmountException,
			InvalidHospitalDateException, InvalidRequirementException,
			InvalidTimeLordException {
		s = new Scheduler(new TimeLord());
		m = new UserManager();
		m.createAndAddDoctor("Jonathan");
		m.createAndAddDoctor("Jeffrey");
		m.createAndAddDoctor("Jack");
		m.createAndAddDoctor("Jonas");
		m.createAndAddNurse("Jenny");
		m.createAndAddNurse("Jill");

		listOfDoctors = new LinkedList<Schedulable>(m.getAllDoctors());
		listOfNurses = new LinkedList<Schedulable>(m.getAllNurses());
		listOfSchedulables = new LinkedList<LinkedList<Schedulable>>();
		listOfSchedulables.add(listOfDoctors);
		listOfSchedulables.add(listOfNurses);
		occurences1 = new LinkedList<Integer>();
		occurences1.add(0);
		occurences1.add(2);
		occurences2 = new LinkedList<Integer>();
		occurences2.add(2);
		occurences2.add(2);
		occurences3 = new LinkedList<Integer>();
		occurences3.add(5);
		occurences3.add(5);
		occurences4 = new LinkedList<Integer>();
		occurences4.add(4);
		occurences4.add(1);
		occurences5 = new LinkedList<Integer>();
		occurences5.add(4);
		occurences5.add(2);
		occurences6 = new LinkedList<Integer>();
		occurences6.add(4);
		occurences6.add(2);
		occurences7 = new LinkedList<Integer>();
		occurences7.add(2);
		occurences7.add(1);
		fullOccurences = new LinkedList<Integer>();
		fullOccurences.add(0);
		fullOccurences.add(1);
		fullOccurences.add(1);
		unsched1 = new UnscheduledTaskTestClass(5000l,
				HospitalDate.START_OF_TIME, 0, new LinkedList<Schedulable>(),
				listOfSchedulables, occurences1, true);
		unsched2 = new UnscheduledTaskTestClass(5000l,
				HospitalDate.START_OF_TIME, 0, new LinkedList<Schedulable>(),
				listOfSchedulables, occurences2, true);
		unsched3 = new UnscheduledTaskTestClass(5000l,
				HospitalDate.START_OF_TIME, 0, new LinkedList<Schedulable>(),
				listOfSchedulables, occurences3, true);
		unsched4 = new UnscheduledTaskTestClass(5000l,
				HospitalDate.START_OF_TIME, 0, new LinkedList<Schedulable>(),
				listOfSchedulables, occurences4, true);
		unsched5 = new UnscheduledTaskTestClass(5000l,
				HospitalDate.START_OF_TIME, HospitalDate.ONE_HOUR,
				new LinkedList<Schedulable>(), listOfSchedulables, occurences5,
				true);
		LinkedList<Schedulable> jonathan = new LinkedList<Schedulable>();
		jonathan.add(listOfDoctors.get(0));
		unsched6 = new UnscheduledTaskTestClass(1500l,
				HospitalDate.START_OF_TIME, 0, jonathan, listOfSchedulables,
				occurences6, true);
		unsched7 = new UnscheduledTaskTestClass(1501l,
				HospitalDate.START_OF_TIME, 0, new LinkedList<Schedulable>(),
				listOfSchedulables, occurences7, true);
	}

	@Test
	public void makeTreeMatrixTest() {
		boolean[][] treeMatrix = s.makeTreeMatrix(listOfSchedulables,
				fullOccurences);
		assertTrue(treeMatrix.length == 3);
		assertTrue(treeMatrix[0].length == 4);
		assertTrue(treeMatrix[1].length == 2);
		assertTrue(treeMatrix[2].length == 2);
	}

	@Test
	public void schedule0Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException, InvalidResourceException,
			InvalidHospitalDateArgument {
		makeBusyAt(listOfNurses.get(0), 0, 5000);
		s.schedule(unsched1);
	}

	@Test
	public void schedule1Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {
		for (Schedulable s : listOfNurses) {
			makeBusyAt(s, 0, 5000);
		}
		assertFalse(s.schedule(unsched1).getTimeSlot().getStartPoint()
				.getHospitalDate().equals(new HospitalDate()));
	}

	@Test
	public void schedule2Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {
		for (Schedulable s : listOfNurses) {
			makeBusyAt(s, 0, 5000);
		}
		assertFalse(s.schedule(unsched1).getStartDate()
				.equals(new HospitalDate()));
	}

	@Test
	public void schedule3Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {
		for (Schedulable s : listOfNurses) {
			makeBusyAt(s, 0, 5000);
		}
		for (Schedulable s : listOfDoctors) {
			makeBusyAt(s, 0, 5000);
		}
		assertFalse(s.schedule(unsched2).getTimeSlot().getStartPoint()
				.getHospitalDate().equals(new HospitalDate()));
	}

	@Test(expected = InvalidSchedulingRequestException.class)
	public void schedule4Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {
		s.schedule(unsched3);
		throw new IllegalStateException("Something went wrong....");
	}

	@Test
	public void schedule5Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {
		for (Schedulable s : listOfNurses)
			makeBusyAt(s, 0, 5000);

		for (Schedulable s : listOfDoctors)
			makeBusyAt(s, 0, 2300);
		assertFalse(s.schedule(unsched4).getTimeSlot().getStartPoint()
				.getHospitalDate().equals(new HospitalDate()));
	}

	@Test
	public void schedule6Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {

		makeBusyAt(listOfNurses.get(0), 1500, 5000);
		for (Schedulable s : listOfDoctors) {
			makeBusyAt(s, 5500, 8000);
		}
		assertTrue(s.schedule(unsched5).getStartDate()
				.equals(new HospitalDate(HospitalDate.ONE_HOUR)));
	}

	@Test
	public void schedule7Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {

		makeBusyAt(listOfDoctors.get(0), 1500, 5000);
		for (Schedulable s : listOfNurses) {
			makeBusyAt(s, 5500, 8000);
		}
		assertTrue(s.schedule(unsched5).getStartDate()
				.equals(new HospitalDate(HospitalDate.ONE_HOUR)));
	}

	@Test(expected = InvalidSchedulingRequestException.class)
	public void schedule8Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {
		s.schedule(unsched6);
	}

	@Test
	public void schedule9Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException,
			InvalidDurationException, InvalidOccurencesException,
			InvalidAmountException, InvalidHospitalDateException,
			InvalidHospitalDateArgument {
		ScheduledTask t = s.schedule(unsched7);
		assertTrue(t.getStartDate().equals(new HospitalDate()));
	}

	@Test
	public void schedule10Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException, InvalidResourceException,
			InvalidHospitalDateArgument {
		ScheduledTask t0 = s.schedule(unsched5);
		assertTrue(t0.getStartDate().equals(
				new HospitalDate(HospitalDate.ONE_HOUR)));
		ScheduledTask t1 = s.schedule(unsched5);
		assertTrue(t1.getStartDate().equals(
				new HospitalDate(HospitalDate.ONE_HOUR + 5
						* HospitalDate.ONE_SECOND)));
	}

	private void makeBusyAt(Schedulable s, long startMillis, long stopMillis)
			throws InvalidSchedulingRequestException {
		s.scheduleAt(new TimeSlot(new StartTimePoint(HospitalDate.START_OF_TIME
				.getTimeSinceStart() + startMillis), new StopTimePoint(
				HospitalDate.START_OF_TIME.getTimeSinceStart() + stopMillis)));
	}
}