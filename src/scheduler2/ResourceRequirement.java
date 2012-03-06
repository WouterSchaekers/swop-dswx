package scheduler2;

import scheduler.task.Schedulable;

public class ResourceRequirement<T extends Schedulable> implements Requirement
{
	private Class<T> _type;

	public ResourceRequirement(Class<T> type)
	{
		_type=type;
	}

	@Override
	public boolean isMetBy(Schedulable schedulable) {
		return schedulable.getClass().equals(_type);
	}
	
}
