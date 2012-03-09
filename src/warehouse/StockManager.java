package warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import warehouse.item.MealType;
import warehouse.item.WarehouseItemType;
import warehouse.orderstrat.OrderStrategy;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;

/**
 * Class that manages the orders that need to be made to keep the warehouse sufficiently filled.
 *
 */
public class StockManager implements Observer
{
	private Collection<OrderStrategy> _orderStrategies;
	private Warehouse _warehouse;
	private StockProvider _provider;
	public StockManager(Warehouse warehouse,StockProvider provider)
	{
		warehouse.addObserver(this);
		_warehouse = warehouse;
		_provider = provider;
		_orderStrategies = defaultOrderStrategies();
	}

	private Collection<OrderStrategy> defaultOrderStrategies() {
		ArrayList<OrderStrategy> strat = new ArrayList<OrderStrategy>();
		strat.add(new PatientMealStrategy(new MealType()));
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(! (o instanceof Warehouse))
			return;
		for(OrderStrategy strategy:_orderStrategies)
		{
			for(StockOrder<? extends WarehouseItemType>  order : strategy.getOrders(_warehouse))
			{
				_provider.add(order);
			}
		}
	}
}
