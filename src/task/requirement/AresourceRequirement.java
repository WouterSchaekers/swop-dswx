package task.requirement;

import java.util.ArrayList;
import java.util.Collection;
import task.Resource;

public class AresourceRequirement extends Requirement
{
	private Class<? extends Resource> type;

	public AresourceRequirement(Class<? extends Resource> type) {
		this.type = type;
	}

	@Override
	public Collection<Resource> resourcesNeededFrom(Collection<Resource> resourcesAv) {
		Collection<Resource> res = new ArrayList<Resource>(resourcesAv);
		Collection<Resource> usedResources=new ArrayList<Resource>();
		for (Resource r : res) {
			if (satifies(r)) {
				usedResources.add(r);
				return usedResources;
			}
		}
		return usedResources;
		

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
