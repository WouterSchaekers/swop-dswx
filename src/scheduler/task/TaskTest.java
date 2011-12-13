package scheduler.task;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.junit.Test;
import users.Nurse;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import exceptions.QueueException;

public class TaskTest
{
	@Test
	public void simpletask() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, InvalidTimeSlotException, InvalidNameException{
		Collection<Collection<Schedulable>> r = new ArrayList<Collection<Schedulable>>();
		ArrayList<Schedulable> zero= new ArrayList<Schedulable>();
		zero.add(new Nurse("jenny"));
		r.add(zero );
		Task simpletask= new Task(r, 10);
		TaskManager m = new TaskManager();
		m.addTask(simpletask);
		
		m.updateQueue();
	}
	@Test
	public void testIfCollectionsGetCopied() throws InvalidNameException, InvalidTimeSlotException, QueueException, InvalidDurationException, InvalidSchedulingRequestException
	{
		Collection<Collection<Schedulable>> r = new ArrayList<Collection<Schedulable>>();
		ArrayList<Schedulable> zero= new ArrayList<Schedulable>();
		zero.add(new Nurse("jenny"));
		r.add(zero );
		Task simpletask= new Task(r, 10);
		assertTrue(simpletask.getResources()!=r);
		Iterator<Collection<Schedulable>> _1=r.iterator();
		Iterator<Collection<Schedulable>> _2=simpletask.getResources().iterator();
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
