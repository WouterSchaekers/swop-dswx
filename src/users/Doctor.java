package users;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import controllers.interfaces.DoctorIN;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

public class Doctor extends SchedulableUser implements DoctorIN
{
	public Doctor(String name) throws InvalidNameException {
		super(name);
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(HospitalDate startDate,
			HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate,
				duration);
	}

}