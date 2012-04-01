package scheduler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import system.Location;

public class LocationTimeTablePreference
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

	public LocationTimeTablePreference(LinkedList<LocationTimeSlot> locationTimeSlots) {
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>();
		for (LocationTimeSlot locationTimeSlot : locationTimeSlots) {
			this.locationTimeSlots_.add(makeDummyTimeSlot(locationTimeSlot));
		}
		sortLocationTimeSlots();
	}
	
	public Location getLocationAt(HospitalDate hospitalDate){
		for(LocationTimeSlot locationTimeSlot : this.locationTimeSlots_){
			if(locationTimeSlot.contains(hospitalDate)){
				return locationTimeSlot.getLocation();
			}
		}
		return null;
	}

	private LocationTimeSlot makeDummyTimeSlot(LocationTimeSlot locationTimeSlot) {
		return new LocationTimeSlot(new StartTimePoint(createDummyDate(locationTimeSlot.getStartDate())),
				new StopTimePoint(createDummyDate(locationTimeSlot.getStopDate())), locationTimeSlot.getLocation());
	}

	private HospitalDate createDummyDate(HospitalDate hospitalDate) {
		return new HospitalDate(0, 0, 0, hospitalDate.getHour(), hospitalDate.getMinute(), hospitalDate.getSecond());
	}
}