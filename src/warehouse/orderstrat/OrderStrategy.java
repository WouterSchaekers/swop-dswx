package warehouse.orderstrat;

import java.util.Collection;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockOrder;

public abstract class OrderStrategy
{
	protected WarehouseItemType _type;

	public OrderStrategy(WarehouseItemType type) {
		_type = type;
	}

	public abstract Collection<StockOrder<? extends WarehouseItemType>> getOrders(
			Warehouse _warehouse);

}
