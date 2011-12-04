package task.requirement;

import java.util.ArrayList;
import java.util.Collection;
import task.Schedulable;

public class AresourceRequirement extends Requirement
{
	private Class<? extends Schedulable> type;

	public AresourceRequirement(Class<? extends Schedulable> type) {
		this.type = type;
	}

	@Override
	public Collection<Schedulable> resourcesNeededFrom(
			Collection<Schedulable> resourcesAv) {
		Collection<Schedulable> res = new ArrayList<Schedulable>(resourcesAv);
		Collection<Schedulable> usedResources = new ArrayList<Schedulable>();
		for (Schedulable r : res) {
			if (satifies(r)) {
				usedResources.add(r);
				return usedResources;
			}
		}
		return usedResources;

	}

	private boolean satifies(Schedulable r) {
		if (r.getClass().equals(type))
			return true;
		return false;
	}

	@Override
	public boolean isMetBy(Collection<Schedulable> availableNow) {
		for (Schedulable r : availableNow) {
			if (satifies(r))
				return true;
		}
		return false;
	}

}
