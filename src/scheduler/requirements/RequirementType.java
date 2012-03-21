package scheduler.requirements;

public class RequirementType<T extends Requirable> implements Requirement
{
	private Class<T> type_;
	private boolean backToBack_;
	
	public RequirementType(Class<T> type, boolean backToBack) {
		type_ = type;
		backToBack_ = backToBack;
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

	@Override
	public boolean backToBack() {
		return backToBack_;
	}
}