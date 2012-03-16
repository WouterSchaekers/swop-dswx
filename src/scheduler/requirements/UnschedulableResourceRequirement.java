package scheduler.requirements;

import scheduler.Schedulable;

public class UnschedulableResourceRequirement<T extends Schedulable> implements Requirement
{
	private Class<T> type_;

	public UnschedulableResourceRequirement(Class<T> type) {
		type_ = type;
	}

	@Override
	public boolean isMetBy(Schedulable schedulable) {
		return schedulable.getClass().equals(type_);
	}
}