package scheduler;

import java.util.Collection;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import exceptions.*;
import scheduler.task.Schedulable;
import users.*;

public class ScheduleTest
{
	UserManager m;
	Scheduler s;

	@Before
	public void create() throws UserAlreadyExistsException,	InvalidNameException {
		m = new UserManager();
		m.createNurse("Jenny");
		m.createNurse("Jill");
		m.createNurse("Johanne");
		m.createNurse("Janet");
		m.createDoctor("Jasper");
		s = new Scheduler();
	}

	@Test
	public void schedule1Nurse() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		Collection<Collection<Schedulable>> t =  new ArrayList<Collection<Schedulable>>();
		Collection<Schedulable> t2 = m.getAllNurses();
		t.add(t2);
		
		//assertTrue(true);

	}
	
	public void schedule2Nurses() {
		
	}
	
	
}
