package scheduler;


public interface Requirement
{
	public boolean isMetBy(Schedulable schedulable);
}
