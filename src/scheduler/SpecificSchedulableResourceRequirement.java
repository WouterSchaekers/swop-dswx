package scheduler;

public class SpecificSchedulableResourceRequirement implements Requirement
{
	private Schedulable schedulable_;
	public SpecificSchedulableResourceRequirement(Schedulable schedulable)
	{
		schedulable_ = schedulable;
	}
	
	@Override
	public boolean isMetBy(Schedulable schedulable) {
		return schedulable_.equals(schedulable);
	}
}