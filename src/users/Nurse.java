package users;

import controllers.interfaces.NurseIN;
import scheduler.HospitalDate;
import scheduler.TimeSlot;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a Nurse in the hospital.
 */
public class Nurse extends SchedulableUser implements NurseIN
{

	// private HospitalDate startsWorkAt = new HospitalDate(0, 0, 0, 8, 0, 0);
	// private HospitalDate stopsWorkAt = new HospitalDate(0, 0, 0, 17, 0, 0);

	private static final int STARTHOUR = 8;
	private static final int STOPHOUR = 17;

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
		return this.timeTable.hasFreeSlotAt(startDate, stopDate);
	}

	@Override
	public TimeSlot getFirstFreeSlotBetween(HospitalDate startDate,
			HospitalDate stopDate, long duration)
			throws InvalidSchedulingRequestException, InvalidTimeSlotException, InvalidHospitalDateArgument {
		HospitalDate tmpStartWorkingHour = new HospitalDate(1, 1, 1,
				Nurse.STARTHOUR, 1, 1);
		HospitalDate tmpStopWorkingHour = new HospitalDate(1, 1, 1,
				Nurse.STOPHOUR, 1, 1);
		HospitalDate tmpStartDate = this.createDummyDate(startDate);
		HospitalDate tmpStopDate = this.createDummyDate(stopDate);
		if (tmpStartDate.before(tmpStartWorkingHour)) {
			startDate = new HospitalDate(startDate.getYear(),
					startDate.getMonth(), startDate.getDay(), Nurse.STARTHOUR,
					1, 1);
		} else if (tmpStopWorkingHour.before(tmpStartDate)
				|| tmpStopWorkingHour.equals(tmpStartDate)) {
			startDate = new HospitalDate(startDate.getYear(),
					startDate.getMonth(), startDate.getDay() + 1,
					Nurse.STARTHOUR, 1, 1);
		}
		if (tmpStopDate.before(tmpStartWorkingHour)
				|| tmpStopDate.equals(tmpStartWorkingHour)) {
			stopDate = new HospitalDate(stopDate.getYear(),
					stopDate.getMonth(), stopDate.getDay() - 1,
					Nurse.STARTHOUR, 1, 1);
		} else if (tmpStopWorkingHour.before(tmpStopDate)) {
			stopDate = new HospitalDate(stopDate.getYear(),
					stopDate.getMonth(), stopDate.getDay(), Nurse.STARTHOUR, 1,
					1);
		}
		if (stopDate.before(startDate)) {
			throw new InvalidSchedulingRequestException(
					"No more free slots available between the given Start -and EndDate!");
		}
		return this.getTimeTable().getFirstFreeSlotBetween(startDate, stopDate,
				duration);
	}

	private HospitalDate createDummyDate(HospitalDate hospitalDate) throws InvalidHospitalDateArgument {
		return new HospitalDate(1, 1, 1, hospitalDate.getHour(),
				hospitalDate.getMinute(), hospitalDate.getSecond());
	}
}