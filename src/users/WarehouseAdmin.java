package users;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import warehouse.PatientMealStrategy;
import warehouse.Warehouse;
import warehouse.item.MealType;
import warehouse.item.WarehouseItemType;
import warehouse.orderstrat.OrderStrategy;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;
import controllers.interfaces.WarehouseAdminIN;
import exceptions.InvalidNameException;
import exceptions.WarehouseOverCapacityException;

/**
 * This class represents the administrator of the warehouse that is associated
 * with a campus. It manages the orders that need to be made to keep the stock
 * of the warehouse at a maintainable level. It does this by observing its
 * warehouse and by making sure that, when the warehouse is running low on any
 * warehouse item, a stock provider is given a new stock order for that specific
 * item.
 */
public class WarehouseAdmin extends User implements WarehouseAdminIN, Observer
{
	private Warehouse warehouse_;
	private StockProvider provider_;
	private Collection<OrderStrategy> orderStrategies_;

	/**
	 * Initialises this warehouse admin and appoints him to a warehouse. Also
	 * provides him with a stockprovider.
	 * 
	 * @param name
	 *            The desired name for this warehouse admin.
	 * @param warehouse
	 *            The warehouse this admin manages.
	 * @param stockProvider
	 *            The stock provider this admin can place stock orers with.
	 */
	public WarehouseAdmin(String name, Warehouse warehouse,
			StockProvider stockProvider) throws InvalidNameException {
		super(name);
		this.warehouse_ = warehouse;
		this.provider_ = stockProvider;
		warehouse_.addObserver(this);
		orderStrategies_ = defaultOrderStrategies();
	}

	/**
	 * Adds an item of the specified type to the warehouse associated with this warehouse admin.
	 */
	public void addItem(WarehouseItemType type)
			throws WarehouseOverCapacityException{
		warehouse_.add(type.create(warehouse_.getCampus().getSystemTime()));
	}

	public void addOrder(WarehouseItemType type, int count) {
		for (int i = 0; i < count; i++)
			provider_.add(new StockOrder<WarehouseItemType>(warehouse_, type));
	}
	
	/**
	 * Initialises a collection of default ordering strategies.
	 */
	private Collection<OrderStrategy> defaultOrderStrategies() {
		Collection<OrderStrategy> strat = new LinkedList<OrderStrategy>();
		strat.add(new PatientMealStrategy(new MealType()));
		//TODO: add more strategies?
		return strat;
	}

	/**
	 * Removes the expired items from his warehouse.
	 */
	private void removeExpiredItems() {
		
	}

	//TODO: wat gebeurt er bij advance time()?
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