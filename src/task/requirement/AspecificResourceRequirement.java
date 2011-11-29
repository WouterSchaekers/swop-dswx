package task.requirement;

import java.util.ArrayList;
import java.util.Collection;
import task.Resource;

public class AspecificResourceRequirement extends Requirement
{
	Resource r;

	public AspecificResourceRequirement(Resource r) {
		this.r = r;
	}

	@Override
	public boolean isMetBy(Collection<Resource> availableNow) {
		for (Resource r : availableNow)
			if (r.equals(this.r))
				return true;
		return false;
	}

	@Override
	public Collection<Resource> resourcesNeededFrom(
			Collection<Resource> resourcesAv) {
		Collection<Resource> res = new ArrayList<Resource>(resourcesAv);
		Collection<Resource> usedResources = new ArrayList<Resource>();
		for (Resource r : res) {
			if (r.equals(this.r)) {
				usedResources.add(r);
				return usedResources;
			}
		}
		return usedResources;
	}

}
