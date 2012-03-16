package scheduler.requirements;

public class SpecificResourceRequirement implements Requirement
{
	private Requirable requirable_;
	public SpecificResourceRequirement(Requirable requirable)
	{
		requirable_ = requirable;
	}
	
	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable_.equals(requirable);
	}
}