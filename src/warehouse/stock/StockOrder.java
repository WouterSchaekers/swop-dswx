package warehouse.stock;

import scheduler.HospitalDate;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidOrderStateException;
import exceptions.WarehouseOverCapacityException;

public class StockOrder<T extends WarehouseItemType>
{
	private boolean delivered_;
	private Warehouse warehouse_;
	private T type_;
	private HospitalDate creationDate_;

	/**
	 * Initialises this stock order.
	 * 
	 * @param warehouse
	 * @param type
	 *            The warehouse item type that can be ordered with this stock
	 *            order.
	 * @param creationDate
	 *            The date this order was created
	 */
	public StockOrder(Warehouse warehouse, T type) {
		warehouse_ = warehouse;
		type_ = type;
		delivered_ = false;
		creationDate_ = warehouse.getCampus().getSystemTime();
	}

	/**
	 * Delivers this stock order to the warehouse.
	 */
	public void deliver() throws WarehouseOverCapacityException,
			InvalidOrderStateException {
		if (delivered_)
			throw new InvalidOrderStateException(
					"This order was already delivered!");
		warehouse_.add(type_.create(type_.getExpirationDate(creationDate_)));
		delivered_ = true;
	}

	public T getType() {
		return type_;
		
	}
}
