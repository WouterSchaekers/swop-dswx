package scheduler.requirements;

import scheduler.Schedulable;

public interface Requirement
{
	public boolean isMetBy(Schedulable schedulable);
}