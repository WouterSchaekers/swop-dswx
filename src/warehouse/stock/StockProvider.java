package warehouse.stock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import exceptions.WarehouseOverCapacityException;
import warehouse.item.WarehouseItemType;

public class StockProvider implements Observer
{
	
	private Collection<StockOrder<?extends WarehouseItemType>> _orders;
	public StockProvider()
	{
		_orders = new ArrayList<StockOrder<?extends WarehouseItemType>>();
	}
	public void add(StockOrder<? extends WarehouseItemType> order)
	{
		_orders.add(order);
	}
	public void deliverOrders()  
	{
		for(StockOrder<? extends WarehouseItemType> order:_orders)
		{
			try {
				order.deliver();
			} catch (WarehouseOverCapacityException e) {
				// do nothing
				
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
			deliverOrders();
	}
}
