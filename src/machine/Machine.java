package machine;

import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import system.Location;
import controllers.interfaces.MachineIN;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a machine in the hospital.
 * 
 * @Invar The serial is unique for every machine object
 */

public abstract class Machine implements Schedulable, MachineIN
{

	protected final int serial_;
	private final Location campusLocation_;
	protected String location_;
	private final TimeTable timeTable_;

	/**
	 * Default constructor.
	 * 
	 * @param serial
	 *            The (unique) serial of this machine.
	 * @param location
	 *            The location of this machine inside the campus.
	 * @param campusLocation
	 *            The campus of this machine.
	 * @throws InvalidLocationException
	 *             If the location provided is null or an empty string.
	 */
	Machine(int serial, String location, Location campusLocation) {
		this.serial_ = serial;
		this.campusLocation_ = campusLocation;
		this.location_ = location;
		this.timeTable_ = new TimeTable();
	}

	/**
	 * @return The serial of this machine.
	 */
	@Override
	public final int getSerial() {
		return this.serial_;
	}

	/**
	 * @return The location of this machine.
	 */
	public final Location getCampusLocation() {
		return this.campusLocation_;
	}

	/**
	 * The location within a campus of this machine.
	 */
	@Override
	public String getLocationWithinCampus() {
		return this.location_;
	}

	/**
	 * There is only one machine object associated with a serial number, this is
	 * a class invariant.
	 */
	@Override
	public final boolean equals(Object o) {
		if (o instanceof Machine)
			return ((Machine) o).serial_ == this.serial_;
		return false;
	}

	/**
	 * Returns the timeTable of this Machine.
	 */
	@Override
	public final TimeTable getTimeTable() {
		return this.timeTable_;
	}

	/**
	 * Returns the first slot that this Machine is available in the given
	 * location and given time.
	 */
	@Override
	public final TimeSlot getFirstFreeSlotBetween(Location location, HospitalDate startDate, HospitalDate stopDate,
			long duration) throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		if (location != this.campusLocation_)
			throw new InvalidSchedulingRequestException("The machine is not available at this location");
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate, duration);
	}

	/**
	 * Updates the timeTable. All the timeslots that ended before the given date
	 * will be deleted.
	 */
	@Override
	public final void updateTimeTable(HospitalDate newDate) {
		this.timeTable_.updateTimeTable(newDate);
	}

	/**
	 * Schedules the Machine at the given timeSlot and given location.
	 */
	@Override
	public final void scheduleAt(TimeSlot t, Location location) throws InvalidSchedulingRequestException {
		this.getTimeTable().addTimeSlot(t);
	}

	/**
	 * Checks whether the machine can be scheduled on the given dates.
	 */
	@Override
	public final boolean canBeScheduledOn(HospitalDate startDate, HospitalDate stopDate) {
		return this.getTimeTable().hasFreeSlotAt(startDate, stopDate);
	}

	/**
	 * Returns whether the Machine can travel or not. Since Machines don't have
	 * legs, this will always return false.
	 */
	@Override
	public final boolean canTravel() {
		return false;
	}

	/**
	 * Returns the location of the Machine at a given date.
	 */
	@Override
	public Location getLocationAt(HospitalDate hospitalDate) {
		return this.campusLocation_;
	}

	/**
	 * Returns whether the Machine must be scheduled back to back. Always
	 * returns false.
	 */
	@Override
	public boolean mustBeBackToBack() {
		return false;
	}
}