package scheduler.requirements;

import scheduler.HospitalDate;
import system.Campus;
import system.Location;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidWarehouseItemException;

/**
 * Class representing a WarehouseItemCondition for a description.
 */
public class WarehouseItemCondition implements Requirement
{
	private WarehouseItemType warehouseItemType_;
	private int amount_;

	/**
	 * Default constructor.
	 * 
	 * @param warehouseItemType
	 *            The type of the WarehouseItem.
	 * @param amount
	 *            The amount of resources of this type is needed.
	 */
	public WarehouseItemCondition(WarehouseItemType warehouseItemType, int amount) {
		this.warehouseItemType_ = warehouseItemType;
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
		return false;
	}

	/**
	 * @return False.
	 */
	@Override
	public boolean isCrucial() {
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
	 * @return True if the Warehouse at this Location has enough items at this
	 *         date.
	 */
	@Override
	public boolean isMetOn(HospitalDate hospitalDate, Location location) {
		if (location instanceof Campus)
			return ((Campus) location).getWarehouse().hasAt(this.warehouseItemType_, this.amount_, hospitalDate);
		return false;
	}

	/**
	 * Deletes items this Requirement needs from the given Warehouse.
	 * 
	 * @param warehouse
	 *            The Warehouse where items will be deleted from.
	 */
	@Override
	public void collect(Warehouse warehouse) {
		try {
			warehouse.take(this.warehouseItemType_);
		} catch (InvalidWarehouseItemException e) {
			throw new Error(e.getMessage());
		}
	}

	/**
	 * @return The amount needed of this type.
	 */
	public int getAmount() {
		return this.amount_;
	}

	/**
	 * Return false.
	 */
	@Override
	public boolean isMarkedForDeletion() {
		return false;
	}
}