package scheduler;

import static org.junit.Assert.*;
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
		s.setNewSystemTime(new Date(System.currentTimeMillis()));
	}

	@Test
	public void schedule1Nurse() throws QueueException, InvalidDurationException, InvalidSchedulingRequestException, ImpossibleToScheduleException {
		Collection<Collection<Schedulable>> t =  new ArrayList<Collection<Schedulable>>();
		ArrayList<Schedulable> t2 = (ArrayList<Schedulable>) m.getAllNurses();
		t.add(t2);
		long duration = Scheduler.ONE_MINUTE + Scheduler.ONE_SECOND * 5;
		Date scheduledDate = s.schedule(duration, t);
		System.out.println("Appointment scheduled on: " + scheduledDate);
		System.out.println("Nurse's timetable = " +t2.get(0).getTimeTable());
		assertFalse(t2.get(0).canBeScheduledOn(scheduledDate, new Date(scheduledDate.getTime() + 1)));
	}
	
	public void schedule2Nurses() {
		
	}
	
	
}
