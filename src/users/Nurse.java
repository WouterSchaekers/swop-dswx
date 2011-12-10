package users;

import java.util.Date;
import be.kuleuven.cs.som.annotate.Basic;
import exceptions.InvalidNameException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

/**
 * This class represents a Nurse in the hospital.
 */
public class Nurse extends SchedulableUser
{
	/**
	 * Default constructor. Will initialise all fields.
	 * 
	 * @param name
	 *            The name of this Nurse.
	 * @throws InvalidNameException
	 * @throws InvalidTimeSlotException 
	 */
	public Nurse(String name) throws InvalidNameException, InvalidTimeSlotException {
		super(name);
	}

	@Override
	@Basic
	public UserType type() {
		return UserType.Nurse;
	}

	private int[] workingHours = {8,17};
	
	/**
	 * This method will see if this Nurse can be scheduled in the given time
	 * interval. It will also check the necessary constraints.
	 * 
	 * @return True if this Nurse can be scheduled in the given time interval.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean canBeScheduledOn(Date startDate, Date stopDate) throws InvalidSchedulingRequestException {
		return startDate.getHours() > this.workingHours[0] && startDate.getHours() < this.workingHours[1] && stopDate.getHours() > this.workingHours[0] && stopDate.getHours() < this.workingHours[1];
	}
}