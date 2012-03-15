package scheduler;

import schedulerold.task.Schedulable;

public interface Requirement
{
	public boolean isMetBy(Schedulable schedulable);
}
