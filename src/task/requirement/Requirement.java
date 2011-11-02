package task.requirement;

import java.util.Collection;
import task.Resource;

public abstract   class Requirement
{

	public  abstract void removeUsedResoursesFrom(Collection<Resource> resourcesAv,
			Collection<Resource> scheduledElements);

	public abstract boolean isMetBy(Collection<Resource> availableNow) ;

}
