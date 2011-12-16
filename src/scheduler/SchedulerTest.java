package scheduler;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import scheduler.task.Schedulable;
import users.UserManager;
import exceptions.InvalidNameException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

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
	public void makeTreeMatrixTest(){
		boolean[][] treeMatrix = Scheduler.makeTreeMatrix(listOfSchedulables, fullOccurences);
		assertTrue(treeMatrix.length == 3);
		assertTrue(treeMatrix[0].length == 4);
		assertTrue(treeMatrix[1].length == 2);
		assertTrue(treeMatrix[2].length == 2);
	}
	
	@Test
	public void schedule0Test() throws InvalidTimeSlotException, InvalidSchedulingRequestException, InvalidResourceException{
		listOfNurses.get(0).getTimeTable().addTimeSlot(new TimeSlot(new TimePoint(HospitalDate.START_OF_TIME, TimeType.start), new TimePoint(HospitalDate.START_OF_TIME.getTimeSinceStart() + 5000, TimeType.stop)));
		System.out.println(Scheduler.schedule(5000, listOfSchedulables, occurences).getTimeSlot());
	}
}