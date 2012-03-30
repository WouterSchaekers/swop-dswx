package scheduler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import system.Location;

public class LocationTimeTable
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
	
	private void sortTimeSlots() {
		LocationTimeSlot[] toSort = new LocationTimeSlot[locationTimeSlots_.size()];
		Iterator<LocationTimeSlot> timeIt = locationTimeSlots_.iterator();
		for (int i = 0; i < toSort.length; i++)
			toSort[i] = timeIt.next();
		Arrays.sort(toSort, c);
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>(Arrays.asList(toSort));
	}

	public LocationTimeTable() {
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>();
	}

	private HospitalDate createDummyDate(HospitalDate hospitalDate) {
		return new HospitalDate(1, 1, 1, hospitalDate.getHour(), hospitalDate.getMinute(), hospitalDate.getSecond());
	}

	public void addLocationTimeSlot(LocationTimeSlot locationTimeSlot) {
		HospitalDate startDate = createDummyDate(locationTimeSlot.getStartDate());
		HospitalDate stopDate = createDummyDate(locationTimeSlot.getStopDate());
		LinkedList<LocationTimeSlot> newLocationTimeSlots = new LinkedList<LocationTimeSlot>();
		for(int i = 0; i < this.locationTimeSlots_.size(); i++){
			LocationTimeSlot curLocationTimeSlot = this.locationTimeSlots_.get(i);
			HospitalDate curStartDate = curLocationTimeSlot.getStartDate();
			HospitalDate curStopDate = curLocationTimeSlot.getStopDate();
			Location curLocation = curLocationTimeSlot.getLocation();
			if(!curStopDate.after(startDate)){
				newLocationTimeSlots.add(curLocationTimeSlot);
			}
			if(curStartDate.before(startDate) && curStopDate.after(startDate)){
				newLocationTimeSlots.add(new LocationTimeSlot(new StartTimePoint(curStartDate), new StopTimePoint(startDate), curLocation));
			}
			if(curStartDate.before(stopDate) && curStopDate.after(stopDate)){
				newLocationTimeSlots.add(new LocationTimeSlot(new StartTimePoint(stopDate), new StopTimePoint(curStopDate), curLocation));
			}
		}
		newLocationTimeSlots.add(new LocationTimeSlot(new StartTimePoint(startDate), new StopTimePoint(stopDate), locationTimeSlot.getLocation()));
		this.locationTimeSlots_ = newLocationTimeSlots;
		sortTimeSlots();
	}
}