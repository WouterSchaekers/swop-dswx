package task.requirement;

import java.util.ArrayList;
import java.util.Collection;
import machine.XRayScanner;
import task.Resource;

public class AresourceRequirement extends Requirement
{
	Class<? extends Resource> type;

	public AresourceRequirement(Class<? extends Resource> type) {
		this.type = type;
	}

	@Override
	public void removeUsedResoursesFrom(Collection<Resource> resourcesAv,
			Collection<Resource> scheduledElements) {
		Collection<Resource> res = new ArrayList<Resource>(resourcesAv);
		for (Resource r : res) {
			if (satifies(r)) {
				resourcesAv.remove(r);
				scheduledElements.add(r);
				return;
			}
		}

	}

	private boolean satifies(Resource r) {
		if (r.getClass().equals(type))
			return true;
		return false;
	}

	@Override
	public boolean isMetBy(Collection<Resource> availableNow) {
		for (Resource r : availableNow) {
			if (satifies(r))
				return true;
		}
		return false;
	}
	

}
