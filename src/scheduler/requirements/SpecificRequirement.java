package scheduler.requirements;

public class SpecificRequirement implements Requirement
{
	private Requirable requirable_;
	public SpecificRequirement(Requirable requirable)
	{
		requirable_ = requirable;
	}
	
	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable_.equals(requirable);
	}
}