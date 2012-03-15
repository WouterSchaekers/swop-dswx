package scheduler;

import schedulerold.task.Schedulable;

public class SpecificResourceRequirement implements Requirement
{

	private Schedulable _sched;
	public SpecificResourceRequirement(Schedulable sched)
	{
		_sched=sched;
	}
	@Override
	public boolean isMetBy(Schedulable schedulable) {
		return _sched.equals(schedulable);
	}

}
