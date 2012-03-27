package patient;

import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import system.Location;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a patient.
 */
public class Patient implements Schedulable
{
	private String name;

	Patient(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot)
			throws InvalidSchedulingRequestException {
		// TODO Auto-generated method stub
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location,
			HospitalDate startDate, HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidHospitalDateArgument {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTimeTable(HospitalDate newDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canTravel() {
		return true;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Patient)
			return ((Patient)o).name.equals(name);
		return false;
	}
}
