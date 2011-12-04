package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import task.Schedulable;
import task.requirement.Requirement;


public class ConstraintProcessor
{

	//XXX: fix... de methode is maar effe gecopy-paste zodat scheduler half gefixt kon worde ^^
	public Collection<Schedulable> satisfied(Collection<Schedulable> availableNow, Collection<Requirement> required) {
		Collection<Schedulable> avResHere = new ArrayList<Schedulable>(availableNow);
		Collection<Schedulable> scheduledElementsTemp = new ArrayList<Schedulable>();
		for (Requirement r : required) {
			if (r.isMetBy(avResHere)) {
				for (Schedulable s : r.resourcesNeededFrom(avResHere)) {
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
