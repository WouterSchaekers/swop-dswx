package warehouse.stock;

import scheduler.HospitalDate;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidOrderStateException;
import exceptions.WarehouseOverCapacityException;

public class StockOrder<T extends WarehouseItemType>
{
	private boolean delivered_;
	private T type_;
	private HospitalDate creationDate_;
	private Warehouse warehouse_;

	StockOrder(Warehouse warehouse, T type, HospitalDate creationDate) {
		type_ = type;
		delivered_ = false;
		warehouse_ = warehouse;
		creationDate_ = creationDate;
		creationDate_.setHour(6);
		creationDate_.setMinute(0);
		creationDate_.setSecond(0);
		
	}
	
	boolean hasBeenDelivered() {
		return this.delivered_;
	}
	
	boolean isReadyForDeliveryAt(HospitalDate time) {
		return time.after(new HospitalDate(creationDate_.getTimeSinceStart() + HospitalDate.ONE_DAY * 2));
	}

	/**
	 * Delivers this stock order to the warehouse.
	 */
	void deliver(HospitalDate expiryDate) throws WarehouseOverCapacityException,
			InvalidOrderStateException {
		if (this.hasBeenDelivered())
			throw new InvalidOrderStateException(
					"This order was already delivered!");
		warehouse_.add(type_.create(expiryDate));
		delivered_ = true;
	}

	T getType() {
		return type_;
		
	}
}
