package task.requirement;

import java.util.Collection;
import task.Resource;

public abstract   class Requirement
{
	/**
	 * Returns the resources needed for this requirement if the available resources in resourcesAV 
	 * meet the requirements.
	 * @param resourcesAv
	 * @return
	 */
	public  abstract Collection<Resource> resourcesNeededFrom(Collection<Resource> resourcesAv);

	public abstract boolean isMetBy(Collection<Resource> availableNow) ;

}
