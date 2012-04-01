package scheduler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class LocationTimeTableBackAndForth
{
	private LinkedList<LocationTimeSlot> locationTimeSlots_;

	private static Comparator<LocationTimeSlot> c = new Comparator<LocationTimeSlot>()
	{
		public int compare(LocationTimeSlot o1, LocationTimeSlot o2) {
			if (o1.getStartPoint().getTime() < o2.getStartPoint().getTime())
				return -1;
			else if (o1.getStartPoint().getTime() == o2.getStartPoint().getTime())
				return 0;
			else
				return 1;
		};
	};

	private void sortLocationTimeSlots() {
		LocationTimeSlot[] toSort = new LocationTimeSlot[locationTimeSlots_.size()];
		Iterator<LocationTimeSlot> timeIt = locationTimeSlots_.iterator();
		for (int i = 0; i < toSort.length; i++)
			toSort[i] = timeIt.next();
		Arrays.sort(toSort, c);
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>(Arrays.asList(toSort));
	}

	public LocationTimeTableBackAndForth() {
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>();
	}

	public void addLocationTimeSlot(LocationTimeSlot locationTimeSlot) {
		this.locationTimeSlots_.add(locationTimeSlot);
		sortLocationTimeSlots();
	}
}