package scheduler;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.Date;
import org.junit.Test;
import users.Doctor;
import users.Nurse;
import users.User;
import users.UserAlreadyExistsException;
import users.UserManager;

public class SchedulerTest
{
	Scheduler scheduler;
	UserManager usm;
	Date date;
	public SchedulerTest() throws UserAlreadyExistsException{
		usm = new UserManager();
		usm.CreateDoctor("Retarded Idiot");
		date = new Date(0);
		scheduler = new Scheduler(date, usm);
	}
	@Test
	public void test() {
		Date date2 = new Date(1);
		scheduler.changeNextScheduledDate(date2);
		System.out.println(scheduler.getNextScheduledDate().getTime());
		assertTrue(scheduler.getNextScheduledDate().getTime() == 60000);
	}
	
	public void test2() throws UserAlreadyExistsException{
		Collection<User> users = usm.getAllUsers();
		for(User user : users){
			scheduler.addHospitalStaff(user);
		}
		for(User user : users){
			//TODO
		}
	}

}
