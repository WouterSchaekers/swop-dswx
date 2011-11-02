package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import task.resource.Resource;

public class XRayScannerRequirement extends scheduler.Requirement
{

	@Override
	public boolean isMetBy(Collection<Resource> availableNow) {
		for (Resource r : availableNow)
			if (r instanceof XRayScannerResource)
				return true;
		return false;
	}

	@Override
	public ArrayList<ArrayList<Resource>> removeUsedResoursesFrom(Collection<Resource> availableNow,
			Collection<Resource> scheduledElements) {
		ArrayList<Resource> newAvailableNow = new ArrayList<Resource>();
		ArrayList<Resource> newScheduledElements = new ArrayList<Resource>();
		boolean removed = false;
		for (Resource r : availableNow)
			if (r instanceof XRayScannerResource && !removed) {
				newScheduledElements.add(r);
				removed = true;
			}
			else{
				newAvailableNow.add(r);
			}
		availableNow = newAvailableNow;
		scheduledElements = newScheduledElements;
		ArrayList<ArrayList<Resource>> resourcesToReturn = new ArrayList<ArrayList<Resource>>();
		resourcesToReturn.add(newAvailableNow);
		resourcesToReturn.add(newScheduledElements);
		System.out.println("Available after xrayrequirement: " + availableNow.size());
		return resourcesToReturn;
	}

}
