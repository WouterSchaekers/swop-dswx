package scheduler;

import java.util.LinkedList;
import org.junit.Before;
import scheduler.task.Schedulable;
import users.UserManager;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class SchedulerTest
{
	UserManager m;
	Scheduler s = new Scheduler();
	LinkedList<LinkedList<Schedulable>> t;
	LinkedList<LinkedList<Schedulable>> t4;
	LinkedList<Schedulable> t2;
	LinkedList<Schedulable> t3;

	Schedulable n1;
	Schedulable n2;
	Schedulable d1;
	Schedulable d2;
	Schedulable d3;
	Schedulable d4;

	@Before
	public void create() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException {
		m = new UserManager();
		m.createNurse("Jenny");
		m.createNurse("Jill");
		m.createDoctor("Jonathan");
		m.createDoctor("Jeffrey");
		m.createDoctor("Jack");
		m.createDoctor("Jonas");
		s = new Scheduler();
		Scheduler.setNewSystemTime(HospitalDate.START_OF_TIME);

		t = new LinkedList<LinkedList<Schedulable>>();
		t4 = new LinkedList<LinkedList<Schedulable>>();
		t2 = (LinkedList<Schedulable>) m.getAllNurses();
		t3 = (LinkedList<Schedulable>) m.getAllDoctors();

		t.add(t2);
		t4.add(t3);
		t4.add(t2);

		n1 = t2.get(0);
		n2 = t2.get(1);
		d1 = t3.get(0);
		d2 = t3.get(1);
		d3 = t3.get(2);
		d4 = t3.get(3);

	}
	// XXX
}
