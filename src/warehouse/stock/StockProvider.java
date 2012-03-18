package warehouse.stock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import warehouse.item.WarehouseItemType;
import exceptions.InvalidOrderStateException;
import exceptions.WarehouseOverCapacityException;

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
		orders_ = new ArrayList<StockOrder<? extends WarehouseItemType>>();
	}
	public Collection<StockOrder<? extends WarehouseItemType>> getOrders()
	{
		return new ArrayList<StockOrder<? extends WarehouseItemType>>(orders_);
	}
	/**
	 * Adds new stock orders to the stock order pool of this stock provider.
	 */
	public void add(StockOrder<? extends WarehouseItemType> order) {
		orders_.add(order);
	}

	/**
	 * Delivers the pooled stock orders to the warehouse.
	 */
	public void deliverOrders() {
		for (StockOrder<? extends WarehouseItemType> order : orders_) {
			try {
				order.deliver();
			} catch (WarehouseOverCapacityException e) {
				;// do nothing
			} catch (InvalidOrderStateException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		deliverOrders();
	}
}
