package scheduler;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import scheduler.task.Schedulable;
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
		Scheduler.setNewSystemTime(HospitalDate.START_OF_TIME);
	}

	@Test
	public void makeTreeMatrixTest() {
		boolean[][] treeMatrix = Scheduler.makeTreeMatrix(listOfSchedulables,
				fullOccurences);
		assertTrue(treeMatrix.length == 3);
		assertTrue(treeMatrix[0].length == 4);
		assertTrue(treeMatrix[1].length == 2);
		assertTrue(treeMatrix[2].length == 2);
	}

	@Test
	public void schedule0Test() throws InvalidTimeSlotException,
			InvalidSchedulingRequestException, InvalidResourceException {
		listOfNurses
				.get(0)
				.getTimeTable()
				.addTimeSlot(
						new TimeSlot(new StartTimePoint(
								HospitalDate.START_OF_TIME),
								new EndTimePoint(HospitalDate.START_OF_TIME
										.getTimeSinceStart() + 5000)));
		System.out.println(Scheduler.schedule(5000, listOfSchedulables,
				occurences).getTimeSlot());
	}

	@Test
	public void sschedule1Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses) {
			s.getTimeTable().addTimeSlot(
					new TimeSlot(
							new StartTimePoint(HospitalDate.START_OF_TIME),
							new EndTimePoint(HospitalDate.START_OF_TIME
									.getTimeSinceStart() + 5000)));
		}
		Scheduler.schedule(5000, listOfSchedulables,occurences);
	}

	@Test
	public void sschedule2Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses) {
			s.getTimeTable().addTimeSlot(
					new TimeSlot(
							new StartTimePoint(HospitalDate.START_OF_TIME),
							new EndTimePoint(HospitalDate.START_OF_TIME
									.getTimeSinceStart() + 5000)));
		}
		occurences = new LinkedList<Integer>();
		occurences.add(2);
		occurences.add(0);
		Scheduler.schedule(5000, listOfSchedulables,occurences);
	}

	@Test
	public void sschedule3Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses) {
			s.getTimeTable().addTimeSlot(
					new TimeSlot(
							new StartTimePoint(HospitalDate.START_OF_TIME),
							new EndTimePoint(HospitalDate.START_OF_TIME
									.getTimeSinceStart() + 5000)));
		}
		for (Schedulable s : listOfDoctors) {
			s.getTimeTable().addTimeSlot(
					new TimeSlot(
							new StartTimePoint(HospitalDate.START_OF_TIME),
							new EndTimePoint(HospitalDate.START_OF_TIME
									.getTimeSinceStart() + 5000)));
		}
		occurences = new LinkedList<Integer>();
		occurences.add(2);
		occurences.add(2);
		Scheduler.schedule(5000, listOfSchedulables,occurences);
	}

	@Test (expected = InvalidSchedulingRequestException.class)
	public void sschedule4Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		occurences = new LinkedList<Integer>();
		occurences.add(5);
		occurences.add(5);
		Scheduler.schedule(5000, listOfSchedulables,occurences);
	}
	
	@Test
	public void sschedule5Test() throws InvalidSchedulingRequestException,
			InvalidTimeSlotException, InvalidResourceException {
		for (Schedulable s : listOfNurses) {
			s.getTimeTable().addTimeSlot(
					new TimeSlot(
							new StartTimePoint(HospitalDate.START_OF_TIME),
							new EndTimePoint(HospitalDate.START_OF_TIME.getTimeSinceStart() + 5000)));
		}
		for (Schedulable s : listOfDoctors) {
			s.getTimeTable().addTimeSlot(
					new TimeSlot(
							new StartTimePoint(HospitalDate.START_OF_TIME),
							new EndTimePoint(HospitalDate.START_OF_TIME.getTimeSinceStart() + 2300)));
		}
		occurences = new LinkedList<Integer>();
		occurences.add(4);
		occurences.add(1);
		System.out.println(Scheduler.schedule(5000, listOfSchedulables,occurences).getTimeSlot());
	}

}