package task.requirement;

import java.util.Collection;
import task.resource.Resource;

public abstract class Requirement
{

	public abstract boolean isMetBy(Collection<Resource> availableNow);

	public abstract void removeUsedResoursesFrom(Collection<Resource> availableNow, Collection<Resource> scheduledElements);

}
