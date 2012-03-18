package warehouse.stock;

import java.util.Collection;
import java.util.LinkedList;
import warehouse.Warehouse;
import warehouse.orderstrat.OrderStrategy;

public class OrderManager
{
	private Warehouse warehouse_;
	private StockProvider provider_;
	private Collection<OrderStrategy> strategies_;
	public OrderManager(Warehouse warehouse,StockProvider provider)
	{
		this.warehouse_=warehouse;
		this.provider_=provider;
		strategies_=new LinkedList<OrderStrategy>();
	}
	public void add(OrderStrategy strategy)
	{
		this.strategies_.add(strategy);
		warehouse_.addObserver(strategy);
	}
}
