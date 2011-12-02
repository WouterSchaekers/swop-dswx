package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import task.Resource;
import task.requirement.Requirement;

public class ConstraintProcessor
{
	public Collection<Resource> satisfied(Collection<Resource> availableNow, Collection<Requirement> required) {
		Collection<Resource> avResHere = new ArrayList<Resource>(availableNow);
		Collection<Resource> scheduledElementsTemp = new ArrayList<Resource>();
		for (Requirement r : required) {
			if (r.isMetBy(avResHere)) {
				for (Resource s : r.resourcesNeededFrom(avResHere)) {
					scheduledElementsTemp.add(s);
					avResHere.remove(s);
				}
			} else {
				scheduledElementsTemp.clear();
				return scheduledElementsTemp;
			}
		}
		return scheduledElementsTemp;
	}
}
