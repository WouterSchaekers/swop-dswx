package warehouse.item;

import scheduler.HospitalDate;
import scheduler.requirements.Requirable;

/**
 * Represents an item that can be stored in a warehouse.
 */
public abstract class WarehouseItem implements Requirable
{
	protected HospitalDate expiryDate;

	public WarehouseItem(){}
	
	/**
	 * @param date
	 *            The expiry date, of this warehouse item.
	 */
	public WarehouseItem(HospitalDate date) {
		if (date != null)
			this.expiryDate = date;
		else
			throw new IllegalArgumentException(
					"Invalid expiry date given to new warehouse item!");
	}

	/**
	 * @return True if the expiry date, given at creation time, is before date.
	 */
	public abstract boolean isExpired(HospitalDate date);

	/**
	 * @return The type of this warehouse item.
	 */
	public abstract WarehouseItemType getType();

}
