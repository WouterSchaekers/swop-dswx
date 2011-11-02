package task.requirement;

import java.util.ArrayList;
import java.util.Collection;
import task.resource.Resource;

public abstract class Requirement
{

	public abstract boolean isMetBy(Collection<Resource> availableNow);

	public abstract ArrayList<ArrayList<Resource>> removeUsedResoursesFrom(Collection<Resource> availableNow, Collection<Resource> scheduledElements);

}
