package scheduler.requirements;

import scheduler.HospitalDate;
import system.Location;
import warehouse.Warehouse;

/**
 * Class representing a RequirementType for a description.
 */
public class RequirementType<T extends Requirable> implements Requirement
{
	private Class<T> type_;
	private int amount_;

	/**
	 * Default constructor.
	 * 
	 * @param type
	 *            The type of the Requirement.
	 * @param amount
	 *            The amount of resources of this type is needed.
	 */
	public RequirementType(Class<T> type, int amount) {
		this.type_ = type;
		this.amount_ = amount;
	}

	/**
	 * Checks whether the given Requirable can meet this Requirement.
	 * 
	 * @param requirable
	 *            The requirable that has to be checked.
	 * @return True if the given Requirable can meet this Requirement.
	 */
	@Override
	public boolean isMetBy(Requirable requirable) {
		return requirable.getClass().equals(type_);
	}

	/**
	 * @return True.
	 */
	@Override
	public boolean isCrucial() {
		return true;
	}

	/**
	 * Checks whether this Requirement is met on a specific HospitalDate and
	 * Location.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate that has to be checked.
	 * @param location
	 *            The Location that has to be checked.
	 * @return False.
	 */
	@Override
	public boolean isMetOn(HospitalDate hospitalDate, Location location) {
		return false;
	}

	/**
	 * No op.
	 */
	@Override
	public void collect(Warehouse warehouse) {
		;
	}

	/**
	 * @return The amount needed of this type.
	 */
	public int getAmount() {
		return this.amount_;
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isMarkedForDeletion() {
		return false;
	}
}