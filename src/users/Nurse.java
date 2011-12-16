package users;

import scheduler.HospitalDate;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a Nurse in the hospital.
 */
public class Nurse extends SchedulableUser
{

	// private HospitalDate startsWorkAt = new HospitalDate(0, 0, 0, 8, 0, 0);
	// private HospitalDate stopsWorkAt = new HospitalDate(0, 0, 0, 17, 0, 0);

	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param name
	 *            The name of this Nurse.
	 * @throws InvalidNameException
	 * @throws InvalidTimeSlotException
	 */
	public Nurse(String name) throws InvalidNameException,
			InvalidTimeSlotException {
		super(name);
	}

	@Override
	@Basic
	public UserType type() {
		return UserType.Nurse;
	}

	/**
	 * This method will see if this Nurse can be scheduled in the given time
	 * interval. It will also check the necessary constraints.
	 * 
	 * @return True if this Nurse can be scheduled in the given time interval.
	 * @throws InvalidTimeSlotException
	 */
	@Override
	public boolean canBeScheduledOn(HospitalDate startDate,
			HospitalDate stopDate) throws InvalidSchedulingRequestException,
			InvalidTimeSlotException {
//		return startDate.getHour() >= this.startsWorkAt.getHour()
//				&& startDate.getHour() < this.stopsWorkAt.getHour()
//				&& stopDate.getHour() > this.startsWorkAt.getHour()
//				&& stopDate.getHour() <= this.stopsWorkAt.getHour()
//				&& this.timeTable.hasFreeSlotAt(startDate, stopDate);
		return this.timeTable.hasFreeSlotAt(startDate,stopDate);
	}
}