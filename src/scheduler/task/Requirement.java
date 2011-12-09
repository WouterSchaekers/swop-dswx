package scheduler.task;

/**
 * The reason some Tasks can't be scheduled straight away is that there can be
 * another class that isn't ready yet. All those kinds of classes implement this
 * interface. The first class that comes to mind that has to implement this
 * interface is Diagnose.
 */
public interface Requirement
{
	public boolean isReady();	
}
