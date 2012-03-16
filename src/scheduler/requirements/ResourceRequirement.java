package scheduler.requirements;

public class ResourceRequirement<T extends Requirable> implements Requirement
{
	private Class<T> type_;

	public ResourceRequirement(Class<T> type) {
		type_ = type;
	}

	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable.getClass().equals(type_);
	}
}