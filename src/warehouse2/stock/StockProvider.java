package warehouse2.stock;

import java.util.ArrayList;
import java.util.Collection;
import exceptions.WarehouseOverCapacityException;
import warehouse2.item.WarehouseItemType;

public class StockProvider
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
	public void deliverOrders() throws WarehouseOverCapacityException
	{
		for(StockOrder<?extends WarehouseItemType> type:_orders)
		{
			type.deliver();
		}
	}
}
