package scheduler;

import system.Location;

public class LocationTimeSlot extends TimeSlot
{
	private Location location_;
	
	public LocationTimeSlot(TimePoint t1, TimePoint t2, Location location) {
		super(t1, t2);
		this.location_ = location;
	}
	
	public Location getLocation(){
		return this.location_;
	}
}