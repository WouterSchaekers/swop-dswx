package task.requirement;

import java.util.Collection;
import task.Schedulable;

public abstract class Requirement
{
	/**
	 * Returns the resources needed for this requirement if the available
	 * resources in resourcesAV meet the requirements.
	 * 
	 * @param resourcesAv
	 * @return
	 */
	public abstract Collection<Schedulable> resourcesNeededFrom(
			Collection<Schedulable> resourcesAv);

	public abstract boolean isMetBy(Collection<Schedulable> availableNow);

}
