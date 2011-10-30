package scheduler;

import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;

public class Scheduler
{
	private TreeMap<Date, Collection<Appointment>> appointments;
	
	public Scheduler() {
		appointments = new TreeMap<Date, Collection<Appointment>>();
	}
	
}