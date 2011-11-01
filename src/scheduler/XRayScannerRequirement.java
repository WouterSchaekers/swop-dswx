package scheduler;

import java.util.Collection;
import resources.Resource;
import resources.XRayScanner;

//XXX; fix de naam
public class XRayScannerRequirement extends Requirement
{

	@Override
	public boolean isMetBy(Collection<Resource> availableNow) {
		for (Resource r : availableNow)
			if (r instanceof XrayScannerResource)
				return true;
		return false;
	}

	@Override
	public void removeUsedResoursesFrom(Collection<Resource> availableNow,
			Collection<Resource> scheduledElements) {

		for (Resource r : availableNow)
			if (r instanceof XrayScannerResource) {
				scheduledElements.add(r);
				availableNow.remove(r);
			}
	}

}
