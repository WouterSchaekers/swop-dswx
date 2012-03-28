package users;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import system.Location;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class Doctor extends SchedulableUser implements DoctorIN
{
	Doctor(String name, Location preference) throws InvalidNameException {
		super(name, preference);
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate,
			HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate,
				duration);
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