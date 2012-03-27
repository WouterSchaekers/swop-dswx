package machine;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import scheduler.Schedulable;
import scheduler.TimeSlot;
import scheduler.TimeTable;
import scheduler.tasks.ScheduledTask;
import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a machine in the hospital.
 * 
 * @Invar serial is unique for every machine object
 */

public abstract class Machine implements Schedulable
{

	private final int serial_;
	private final Location location_;
	private final TimeTable timeTable_;
	protected final Collection<ScheduledTask> scheduledTasks_;

	/**
	 * Default constructor.
	 * 
	 * @param serial
	 *            The serial of this machine.
	 * @throws InvalidSerialException
	 *             If the serial provided is already contained in the system.
	 * @throws InvalidLocationException
	 *             If the location provided is null or an empty string.
	 * @throws InvalidTimeSlotException
	 */
	Machine(int serial, Location location) throws InvalidLocationException,
			InvalidSerialException {
		if (location == null) {
			throw new InvalidLocationException("Location is not set or empty.");
		}
		this.serial_ = serial;
		this.location_ = location;
		this.timeTable_ = new TimeTable();
		this.scheduledTasks_ = new LinkedList<ScheduledTask>();
	}

	/**
	 * @return The serial of this machine.
	 */
	public final int getSerial() {
		return this.serial_;
	}

	/**
	 * @return The location of this machine.
	 */
	public final Location getLocation() {
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

	@Override
	public final TimeTable getTimeTable() {
		return this.timeTable_;
	}

	@Override
	public final TimeSlot getFirstFreeSlotBetween(Location location,
			HospitalDate startDate, HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException {
		if (location != this.location_) {
			throw new InvalidSchedulingRequestException(
					"The machine is not available at this location");
		}
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate,
				duration);
	}

	@Override
	public final void updateTimeTable(HospitalDate newDate) {
		this.timeTable_.updateTimeTable(newDate);
	}

	@Override
	public final void scheduleAt(TimeSlot t) throws InvalidSchedulingRequestException {
		this.getTimeTable().addTimeSlot(t);
	}

	@Override
	public final  boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) {
		return this.getTimeTable().hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public final boolean canTravel() {
		return false;
	}

}
