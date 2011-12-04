package scheduler;

import java.util.Collection;
import task.Schedulable;

public abstract class Requirement
{

	public abstract boolean isMetBy(Collection<Schedulable> availableNow);

	public abstract void removeUsedResoursesFrom(
			Collection<Schedulable> availableNow,
			Collection<Schedulable> scheduledElements);

}
