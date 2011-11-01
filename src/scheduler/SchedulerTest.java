package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import resources.MachinePool;
import resources.XRayScanner;
import users.UserManager;


public class SchedulerTest
{
	MachinePool machinepool;
	UserManager usermanager;
	Scheduler scheduler;
	@Before
	public  void intialize(){
		 machinepool= new MachinePool();
		 usermanager = new UserManager();
		machinepool.addMachine(new XRayScanner("main hall"));
		 scheduler = new Scheduler(usermanager,machinepool);
	}
	@Test
	public void ScheduleOneThing() throws ImpossibleToScheduleException{
		Collection<Requirement> requirements = new ArrayList<Requirement>();
		requirements.add(new XRayScannerRequirement());
		ScheduledElement s = scheduler.findFreeSlot(requirements, 10);
		System.out.println("done");
	}
}
