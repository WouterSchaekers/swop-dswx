package task.requirement;

import java.util.ArrayList;
import java.util.Collection;
import task.Schedulable;

public class AspecificResourceRequirement extends Requirement
{
	Schedulable r;

	public AspecificResourceRequirement(Schedulable r) {
		this.r = r;
	}

	@Override
	public boolean isMetBy(Collection<Schedulable> availableNow) {
		for (Schedulable r : availableNow)
			if (r.equals(this.r))
				return true;
		return false;
	}

	@Override
	public Collection<Schedulable> resourcesNeededFrom(
			Collection<Schedulable> resourcesAv) {
		Collection<Schedulable> res = new ArrayList<Schedulable>(resourcesAv);
		Collection<Schedulable> usedResources = new ArrayList<Schedulable>();
		for (Schedulable r : res) {
			if (r.equals(this.r)) {
				usedResources.add(r);
				return usedResources;
			}
		}
		return usedResources;
	}

}
