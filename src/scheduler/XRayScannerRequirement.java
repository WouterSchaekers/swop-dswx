package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import resources.Resource;
import resources.XRayScanner;

//XXX; fix de naam
public class XRayScannerRequirement extends Requirement
{

	@Override
	public boolean isMetBy(Collection<Resource> availableNow) {
		for (Resource r : availableNow)
			if (r instanceof XRayScannerResource)
				return true;
		return false;
	}

	@Override
	public void removeUsedResoursesFrom(Collection<Resource> availableNow,
			Collection<Resource> scheduledElements) {
		Collection<Resource> newAvailableNow = new ArrayList<Resource>();
		Collection<Resource> newScheduledElements = new ArrayList<Resource>();
		for (Resource r : availableNow)
			if (r instanceof XRayScannerResource) {
				newScheduledElements.add(r);
			}
			else{
				newAvailableNow.add(r);
			}
		availableNow = newAvailableNow;
		scheduledElements = newScheduledElements;
	}

}
