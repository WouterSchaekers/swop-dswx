package scheduler.requirements;

import patient.Diagnose;
import scheduler.HospitalDate;
import system.Location;
import warehouse.Warehouse;

/**
 * Class representing a DiagnoseCondition for a description.
 */
public class DiagnoseCondition implements Requirement
{
	private Diagnose diagnose_;

	/**
	 * Default constructor.
	 * 
	 * @param diagnose
	 *            The Diagnose of the Requirement.
	 */
	public DiagnoseCondition(Diagnose diagnose) {
		this.diagnose_ = diagnose;
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isMetBy(Requirable requirable) {
		return false;
	}

	/**
	 * Checks whether this Requirement is met on a specific HospitalDate and
	 * Location.
	 * 
	 * @param hospitalDate
	 *            The HospitalDate that has to be checked.
	 * @param location
	 *            The Location that has to be checked.
	 * @return True if the diagnose is approved.
	 */
	@Override
	public boolean isMetOn(HospitalDate hospitalDate, Location location) {
		return this.diagnose_.isApproved();
	}

	/**
	 * No op.
	 */
	@Override
	public void collect(Warehouse warehouse) {
		;
	}

	/**
	 * @return 1.
	 */
	public int getAmount() {
		return 1;
	}

	/**
	 * Returns true if the Diagnose is marked for deletion.
	 */
	@Override
	public boolean isMarkedForDeletion() {
		return diagnose_.mustBeDeleted();
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isCrucial() {
		return false;
	}
}