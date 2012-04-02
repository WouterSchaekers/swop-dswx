package patient;

import scheduler.HospitalDate;
import scheduler.LocationTimeSlot;
import scheduler.LocationTimeTable;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import system.Location;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a patient. Patients are linked to any number of patient
 * files depending on which hospital they go to. Patients are Schedulables which
 * means they have TimeTables.
 */
class Patient implements Schedulable
{
	private String name;
	private TimeTable timeTable_;
	private LocationTimeTable locationTimeTable_;
	
	Patient(String name) throws InvalidNameException {
		if(!isValidName(name))
			throw new InvalidNameException("Invalid patient name given to Patient constructor!");
		this.name = name;
		timeTable_ = new TimeTable();
	}

	@Basic
	public String getName() {
		return this.name;
	}

	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
		return timeTable_.hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public TimeTable getTimeTable() throws InvalidTimeSlotException {
		return this.timeTable_;
	}

	@Override
	public void scheduleAt(TimeSlot timeSlot, Location location)
			throws InvalidSchedulingRequestException {
		this.timeTable_.addTimeSlot(timeSlot);
		this.locationTimeTable_.addLocationTimeSlot(new LocationTimeSlot(timeSlot, location));
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(Location location,
			HospitalDate startDate, HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException,
			InvalidHospitalDateArgument {
		return timeTable_.getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	@Override
	public void updateTimeTable(HospitalDate newDate) {
		timeTable_.updateTimeTable(newDate);
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
	
	/**
	 * @return True if d is a valid name.
	 */
	private boolean isValidName(String n) {
		return !n.equals("");
	}

	@Override
	public Location getLocationAt(HospitalDate hospitalDate) {
		return locationTimeTable_.getLocationAt(hospitalDate);
	}

}
