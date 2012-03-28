package users;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import system.Location;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class Doctor extends SchedulableUser implements DoctorIN
{
	//TODO: preferences implementeren.
	private TimeTable preference_;
	
	Doctor(String name, Location preference) throws InvalidNameException {
		super(name, preference);
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location,
			HospitalDate startDate, HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate,
				duration);
	}

	public void updateLocation(Location newLocation) {
		if (!isValidLocation(newLocation))
			throw new IllegalArgumentException(
					"Illegal new location for patient!");
		this.location_ = newLocation;
	}

	private boolean isValidLocation(Location l) {
		return l != null;
	}

	@Override
	public boolean canTravel() {
		return true;
	}

	@Override
	public UserFactory getType() {
		return new DocotorFactory();
	}
}