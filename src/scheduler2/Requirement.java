package scheduler2;

import schedulerold.task.Schedulable;

public interface Requirement
{
	public boolean isMetBy(Schedulable schedulable);
}
