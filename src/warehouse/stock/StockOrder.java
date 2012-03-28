package warehouse.stock;

import scheduler.HospitalDate;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidOrderStateException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents a stock order that can be manually placed by a
 * warehouse administrator or can be automatically created by the system when it
 * notices the stock of the warehouse is running low.
 * 
 * @param <T>
 *            The warehouse item type that this stock order is for.
 */
public class StockOrder<T extends WarehouseItemType>
{
	private boolean delivered_;
	private T type_;
	private HospitalDate creationDate_;
	private Warehouse warehouse_;
	private int amount_;

	/**
	 * Initialises a new stock order.
	 * 
	 * @param amount
	 *            The amount of items needed of the type of this order.
	 */
	public StockOrder(Warehouse warehouse, T type, HospitalDate creationDate,
			int amount) {
		type_ = type;
		delivered_ = false;
		warehouse_ = warehouse;
		creationDate_ = creationDate;
		amount_ = amount;
	}

	/**
	 * @return True if this stockorder has been delivered.
	 */
	public boolean hasBeenDelivered() {
		return this.delivered_;
	}

	/**
	 * Delivers this stock order to the warehouse associated with this
	 * stockorder.
	 * 
	 * @param expiryDate
	 *            The expiry date of the warehouse item to be delived by this
	 *            stock order.
	 */
	public void deliver(HospitalDate expiryDate)
			throws WarehouseOverCapacityException, InvalidOrderStateException {
		if (this.hasBeenDelivered())
			throw new InvalidOrderStateException(
					"This order was already delivered!");
		if (!this.canBeDelivered())
			throw new InvalidOrderStateException(
					"This order is not ready for delivery yet!");

		for (int i = 0; i < amount_; i++)
			warehouse_.add(type_.create(expiryDate));
		delivered_ = true;
	}

	/**
	 * @return True if this order can be delivered now.
	 */
	public boolean canBeDelivered() {
		return warehouse_.getCampus().getSystemTime().getSystemTime()
				.after(this.earliestDeliveryTime())
				&& !this.hasBeenDelivered();
	}

	/**
	 * @return 6AM of the 2 days after this stock order was placed.
	 */
	private HospitalDate earliestDeliveryTime() {
		return new HospitalDate(creationDate_.getYear(),
				creationDate_.getMonth(), creationDate_.getDay() + 2, 6, 0, 0);
	}

	public T getType() {
		return type_;
	}
}
