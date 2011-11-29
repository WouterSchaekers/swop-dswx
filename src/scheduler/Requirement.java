package scheduler;

import java.util.Collection;
import task.Resource;

public abstract class Requirement
{

	public abstract boolean isMetBy(Collection<Resource> availableNow);

	public abstract void removeUsedResoursesFrom(
			Collection<Resource> availableNow,
			Collection<Resource> scheduledElements);

}
