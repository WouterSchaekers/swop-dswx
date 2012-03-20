package scheduler.requirements;

public class RequirementType<T extends Requirable> implements Requirement
{
	private Class<T> type_;

	public RequirementType(Class<T> type) {
		type_ = type;
	}

	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable.getClass().equals(type_);
	}

	@Override
	public boolean isMet() {
		return false;
	}

	@Override
	public void collect() {
	}
}