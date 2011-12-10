package machine;

import java.util.ArrayList;
import java.util.Collection;
import scheduler.Date;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;
import scheduler.TimeTable;
import scheduler.task.Schedulable;

/**
 * This class represents a machine in the hospital.
 * @Invar serial is unique for every machine object	
 */

public abstract class Machine implements Schedulable
{

	private final int serial;
	private String location = "";
	private TimeTable timeTable;
	protected static Collection<Integer> usedSerials=new ArrayList<Integer>();
	/**
	 * Default constructor.
	 * 
	 * @param serial
	 *            The serial of this machine.
	 *@throws InvalidSerialException
	 *		If the serial provided is already contained in the system.
	 *@throws InvalidLocationException
	 *		If the location provided is null or an empty string.
	 */
	public Machine(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		if (location == null || location == "") {
			throw new InvalidLocationException("Location is not set or empty.");
		}
		if (usedSerials.contains(serial)) {
			throw new InvalidSerialException(
					"Serial must be different from zero.");
		}
		usedSerials.add(serial);
		this.serial = serial;
		this.location = location;
		this.timeTable = new TimeTable();
	}

	/**
	 * @return The serial of this machine.
	 */
	public int getSerial() {
		return this.serial;
	}

	/**
	 * @return The location of this machine.
	 */
	public String getLocation() {
		return this.location;
	}
	/**
	 * There is only one machine object associated with a serial number, this is a class invariant.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Machine)
			return ((Machine) o).serial == this.serial;
		return false;

	}
	
	@Override
	public TimeTable getTimeTable(){
		return this.timeTable;
	}

	@Override
	public abstract boolean canBeScheduledOn(Date startDate, Date stopDate);
}
