package scheduler;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import exceptions.*;
import scheduler.task.Schedulable;
import users.*;

public class ScheduleTest
{
	UserManager m;

	@Before
	public void create() throws UserAlreadyExistsException,	InvalidNameException {
		m = new UserManager();
		m.CreateNurse("Jenny");
		m.CreateNurse("Jill");
		m.CreateNurse("Johanne");
		m.CreateNurse("Janet");
	}

	@Test
	public void schedule1thing() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		Scheduler s = new Scheduler();
		Collection<Collection<Schedulable>> t =  new ArrayList<Collection<Schedulable>>();
		Collection<Schedulable> t2 = m.getAllNurses();
		t.add(t2);
		s.schedule(20, t);
		System.out.println("wut the fuuu");

	}
}
