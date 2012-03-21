package scheduler.requirements;

public class SpecificRequirement implements Requirement
{
	private Requirable requirable_;
	private boolean backToBack_;

	public SpecificRequirement(Requirable requirable, boolean backToBack) {
		requirable_ = requirable;
		backToBack_ = backToBack;
	}

	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable_.equals(requirable);
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