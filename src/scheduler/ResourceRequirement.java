package scheduler;

public class ResourceRequirement<T extends Schedulable> implements Requirement
{
	private Class<T> type_;

	public ResourceRequirement(Class<T> type) {
		type_ = type;
	}

	@Override
	public boolean isMetBy(Schedulable schedulable) {
		return schedulable.getClass().equals(type_);
	}
}