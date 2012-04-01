package users;

import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.LocationTimeTablePreference;
import scheduler.TimeSlot;
import system.Location;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class Doctor extends SchedulableUser implements DoctorIN
{
	private PreferenceState prefState_;
	private LocationTimeTablePreference locationTimeTable_;

	Doctor(String name, Location preference) throws InvalidNameException {
		super(name, preference);
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	public void updateLocation(Location newLocation) {
		if (!isValidLocation(newLocation))
			throw new IllegalArgumentException("Illegal new location for patient!");
		this.location_ = newLocation;
	}

	private boolean isValidLocation(Location l) {
		return l != null;
	}

	public Location getLocationAt(HospitalDate date) {
		return prefState_.getLocationAt(date);
	}

	public void changePreferenceToBackAndForth() {

	}

	@Override
	public boolean canTravel() {
		return true;
	}

	@Override
	public UserFactory getType() {
		return new DocotorFactory();
	}
	
	void nextState(PreferenceState preferenceState){
		this.prefState_ = preferenceState;
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot, Location location) throws InvalidSchedulingRequestException {
		this.timeTable_.addTimeSlot(timeSlot);
		// Dit mag en kan zo niet!!! Er moet een opsplitsing gemaakt worden
		// tussen de states! -> Oplossing = locationTimeTable in State steken.
		//this.locationTimeTable_.addLocationTimeSlot(new LocationTimeSlot(timeSlot, location));
	}
}