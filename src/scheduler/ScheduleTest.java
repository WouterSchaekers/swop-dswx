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
		m.CreateNurse("lynne");
		m.CreateNurse("Lisa");
		m.CreateNurse("Lovely");
		
		Collection<User> uc = new ArrayList<User>(); 
				
		assertTrue()
	}

	@Test
	public void test() {
		Scheduler s = new Scheduler();
		Collection<Collection<Schedulable>> t = new ArrayList<Collection<Schedulable>>();
		Collection<Schedulable> nurses = new NurseView(m.getAllUsers());
		t.add(nurses);
		try {

			s.schedule(20, t);
			System.out.println("wut the fuuu");
		} catch (QueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSchedulingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImpossibleToScheduleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
