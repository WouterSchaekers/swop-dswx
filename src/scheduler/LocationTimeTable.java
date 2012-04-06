package scheduler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import exceptions.InvalidTimeSlotException;
import system.Location;

/**
 * A class consisting of LocationTimeSlots that always have a start- and end moment and a Location.
 */
public class LocationTimeTable
{
	private LinkedList<LocationTimeSlot> locationTimeSlots_;

	/**
	 * This will allow us to keep the TimeSlots chronologically sorted.
	 */
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

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param locationTimeSlots
	 *            All TimeSlots to be stored in this TimeTable.
	 * @throws InvalidTimeSlotException
	 *             The given TimeSlots are not valid.
	 */
	public LocationTimeTable(LinkedList<LocationTimeSlot> locationTimeSlots) throws InvalidTimeSlotException {
		if (!isValidLocationTimeSlots(locationTimeSlots))
			throw new InvalidTimeSlotException("LocationTimeTable initialized with nullpointer.");
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>();
		for (LocationTimeSlot locationTimeSlot : locationTimeSlots) {
			this.locationTimeSlots_.add(makeDummyTimeSlot(locationTimeSlot));
		}
		sortLocationTimeSlots();
	}
	
	/**
	 * Constructor. Will initialise all fields.
	 */
	public LocationTimeTable(){
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>();
	}
	
	private HospitalDate createDummyDate(HospitalDate hospitalDate) {
		return new HospitalDate(0, 0, 0, hospitalDate.getHour(), hospitalDate.getMinute(), hospitalDate.getSecond());
	}
	
	public void addLocationTimeSlot(LocationTimeSlot locationTimeSlot) {
		this.locationTimeSlots_.add(locationTimeSlot);
		sortLocationTimeSlots();
	}
	
	/**
	 * Use to get location of something that does not have a preference location.
	 */
	public Location getLocationAt(HospitalDate hospitalDate){
		for(LocationTimeSlot locationTimeSlot : this.locationTimeSlots_){
			if(locationTimeSlot.contains(hospitalDate)){
				return locationTimeSlot.getLocation();
			}
		}
		return null;
	}

	/**
	 * Use for preference-location.
	 * 
	 * @return null if not at any location, otherwise the location.
	 */
	public Location getPreferedLocationAt(HospitalDate hospitalDate){
		HospitalDate date = createDummyDate(hospitalDate);
		return getLocationAt(date);
	}

	private LocationTimeSlot makeDummyTimeSlot(LocationTimeSlot locationTimeSlot) {
		return new LocationTimeSlot(new StartTimePoint(createDummyDate(locationTimeSlot.getStartDate())),
				new StopTimePoint(createDummyDate(locationTimeSlot.getStopDate())), locationTimeSlot.getLocation());
	}
	
	private void sortLocationTimeSlots() {
		LocationTimeSlot[] toSort = new LocationTimeSlot[locationTimeSlots_.size()];
		Iterator<LocationTimeSlot> timeIt = locationTimeSlots_.iterator();
		for (int i = 0; i < toSort.length; i++)
			toSort[i] = timeIt.next();
		Arrays.sort(toSort, c);
		this.locationTimeSlots_ = new LinkedList<LocationTimeSlot>(Arrays.asList(toSort));
	}

	public LinkedList<LocationTimeSlot> getSlots() {
		return new LinkedList<LocationTimeSlot>(this.locationTimeSlots_);
	}
	
	/**
	 * Checks wether all the given collection of timeslots consists of valid
	 * timeslots.
	 * 
	 * @param timeSlots
	 *            the given collection of timeslots
	 * @return true if all timeslots are valid
	 */
	public boolean isValidLocationTimeSlots(LinkedList<LocationTimeSlot> locationTimeSlots) {
		return locationTimeSlots == null;
	}
}