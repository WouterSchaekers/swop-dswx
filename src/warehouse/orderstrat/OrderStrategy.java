package warehouse.orderstrat;

import java.util.Observer;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockProvider;

/**
 * Object that manages a orderstrategy for one warehouse& stockprovider.
 */
public abstract class OrderStrategy implements Observer
{
	protected WarehouseItemType _type;
	protected final Warehouse warehouse_;
	protected final StockProvider provider_;

	public OrderStrategy(WarehouseItemType type,Warehouse warehouse,StockProvider provider) {
		_type = type;
		warehouse_=warehouse;
		provider_=provider;
		warehouse.addObserver(this);
	}

}
