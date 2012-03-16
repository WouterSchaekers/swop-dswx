package scheduler.requirements;

public interface Requirement
{
	public boolean isMetBy(Requirable schedulable);
}