package task;

import java.util.Date;

public interface Schedulable
{
	
	public boolean canBeScheduledOn(Date start, Date stop);
	
}
