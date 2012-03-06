package scheduler2;

import scheduler.task.Schedulable;

public interface Requirement
{
	public boolean isMetBy(Schedulable schedulable);
}
