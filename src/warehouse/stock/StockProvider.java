package warehouse.stock;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import warehouse.item.WarehouseItemType;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.StockOrderIN;

/**
 * This class represents a person that manages a stock order pool.
 */
public class StockProvider implements Observer
{
	private Collection<StockOrder<? extends WarehouseItemType>> orders_;

	/**
	 * Initialises a new stock provider with an empty stock order pool.
	 */
	public StockProvider() {
		orders_ = new LinkedList<StockOrder<? extends WarehouseItemType>>();
	}

	@Basic
	public Collection<StockOrderIN> getOrderINs() {
		return new LinkedList<StockOrderIN>(orders_);
	}
	
	/**
	 * ONLY USE IN DOMAIN MODEL.
	 */
	public Collection<StockOrder<? extends WarehouseItemType>> getOrders() {
		return new LinkedList<StockOrder<? extends WarehouseItemType>>(orders_);
	}

	/**
	 * Adds new stock orders to the stock order pool of this stock provider.
	 */
	public void add(StockOrder<? extends WarehouseItemType> order) {
		orders_.add(order);
	}

	/**
	 * Delivers the pooled stock orders to the warehouse if they are ready for
	 * delivery.
	 * 
	 * @param expiryDate
	 *            The expiry date for the stock orders that will be delivered.
	 */
	public void deliverOrders(HospitalDate expiryDate) {
		HospitalDate expDate;
		LinkedList<StockOrder<? extends WarehouseItemType>> updatedOrderlist = new LinkedList<StockOrder<? extends WarehouseItemType>>();
		for (StockOrder<? extends WarehouseItemType> order : orders_) {
			if(expiryDate == null)
				expDate = new HospitalDate(order.getType().getTimeToLive());
			else expDate = expiryDate;
			try {
				if (order.canBeDelivered())
					order.deliver(expDate);
				else{
					updatedOrderlist.add(order);
				}
			} catch (Exception e) {
				throw new Error(e.getMessage());
			}
		}
		this.orders_ = updatedOrderlist;
	}

	/**
	 * Delivers orders if needed when time is updated.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof TimeLord) {
			deliverOrders(null);
		}
	}
}
