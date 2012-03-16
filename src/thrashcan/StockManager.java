package thrashcan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import warehouse.item.MealType;
import warehouse.item.WarehouseItemType;
import warehouse.orderstrat.OrderStrategy;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;

/**
 * This class manages the orders that need to be made to keep the warehouse
 * sufficiently filled. It observes a warehouse and makes sure that, when the
 * warehouse is running low on any warehouse item, a stock provider is given new
 * stock orders. Therefore, a stock manager should be added to the list of
 * observers of each warehouse.
 */
public class StockManager implements Observer
{
	private Collection<OrderStrategy> orderStrategies_;
	private Warehouse warehouse_;
	private StockProvider provider_;

	/**
	 * Initialises a new stock manager.
	 * @param provider
	 * It is vital you specify the same provider 
	 */
	public StockManager(Warehouse warehouse, StockProvider provider) {
		warehouse.addObserver(this);
		warehouse_ = warehouse;
		provider_ = provider;
		orderStrategies_ = defaultOrderStrategies();
	}
	
	/**
	 * Initialises a collection of default ordering strategies.
	 */
	private Collection<OrderStrategy> defaultOrderStrategies() {
		Collection<OrderStrategy> strat = new ArrayList<OrderStrategy>();
		strat.add(new PatientMealStrategy(new MealType()));
		//TODO: add more strategies?
		return strat;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (!(o instanceof Warehouse))
			throw new RuntimeException("Unlikely exception when notifying observers of warehouse: ! o instanceof Warehouse");

		for (OrderStrategy strategy : orderStrategies_) {
			for (StockOrder<? extends WarehouseItemType> order : strategy.getOrders(warehouse_)) {
				provider_.add(order);
			}
		}
	}
}
