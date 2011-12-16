package scheduler.task;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import scheduler.HospitalDate;
import users.Nurse;
import exceptions.*;

public class TaskTest
{
	//XXX rerun tests and see if they fail to run and are coded correctly.
	
	@Test
	public void simpletask() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidTimeSlotException, InvalidNameException, InvalidResourceException, InvalidOccurencesException{
		LinkedList<LinkedList<Schedulable>> r = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> zero= new LinkedList<Schedulable>();
		zero.add(new Nurse("jenny"));
		r.add(zero);
		LinkedList<Integer> l = new LinkedList<Integer>(Arrays.asList(1));
		UnscheduledTask simpletask= new UnscheduledTask(r,10l,new LinkedList<Requirement>(),l);
		TaskManager m = new TaskManager();
		m.addTask(simpletask);
		m.updateQueue();
	}
	
	@Test
	public void testIfCollectionsGetCopied() throws InvalidNameException, InvalidTimeSlotException, QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidResourceException, InvalidOccurencesException
	{
		LinkedList<LinkedList<Schedulable>> r = new LinkedList<LinkedList<Schedulable>>();
		LinkedList<Schedulable> zero= new LinkedList<Schedulable>();
		zero.add(new Nurse("jenny"));
		r.add(zero);
		LinkedList<Integer> l = new LinkedList<Integer>(Arrays.asList(1));
		UnscheduledTask simpletask= new UnscheduledTask(r,10l,new LinkedList<Requirement>(),l);
		TaskManager m = new TaskManager();
		m.addTask(simpletask);
		HashMap<ScheduledTask, HospitalDate> scheduled = m.updateQueue();
		Collection<ScheduledTask> tasks = scheduled.keySet();
		ScheduledTask theTask = null;
		for(ScheduledTask t : tasks)
			theTask = t;
		
		assertTrue(theTask.getResources()!=zero);
		Iterator<Schedulable> _1=zero.iterator();
		Iterator<Schedulable> _2=theTask.getResources().iterator();
		while(_1.hasNext())
		{
			assertFalse(_1.next()==_2.next());
		}
	}
	@Test
	public void abraFail()
	{
		
	}
}
