package warehouse.orderstrat;

import help.Filter;
import java.util.Observable;
import java.util.Observer;
import exceptions.InvalidWarehouseItemException;
import scheduler.HospitalDate;
import scheduler.TimeLord;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;

/**
 * Object that manages an order strategy for a warehouse & stockprovider.
 */
public abstract class OrderStrategy implements Observer
{
	protected WarehouseItemType type_;
	protected final Warehouse warehouse_;
	protected final StockProvider provider_;

	/**
	 * @param timeLord
	 *            To observe.
	 * @throws InvalidWarehouseItemException
	 */
	public OrderStrategy(WarehouseItemType type, Warehouse warehouse, StockProvider provider, TimeLord timeLord)
			 {
		if (!validType(type))
			throw new NullPointerException("itemtype null");
		if (!validWarehouse(warehouse))
			throw new NullPointerException("provided warehouse is null");
		if (!validprovider(provider))
			throw new NullPointerException("provided stockprovider is null");
		if (!validTimelord(timeLord))
			throw new NullPointerException("provided timelord is null");
		type_ = type;
		warehouse_ = warehouse;
		provider_ = provider;
		warehouse.addObserver(this);
		timeLord.addObserver(this);
	}

	private boolean validTimelord(TimeLord timeLord) {
		return timeLord != null;
	}

	private boolean validprovider(StockProvider provider) {
		return provider != null;
	}

	private boolean validWarehouse(Warehouse warehouse) {
		return warehouse != null;
	}

	private boolean validType(WarehouseItemType type) {
		return type != null;
	}

	@Override
	public void update(Observable o, Object arg) {
		int needed = this.getNeeded() - amountOfOpenOrders();
		HospitalDate time = warehouse_.getCampus().getSystemTime().getSystemTime();
	
		if(needed>0)
			provider_.add(new StockOrder<WarehouseItemType>(warehouse_, type_, time, needed));
	}

	private int amountOfOpenOrders() {
		int futureCount = help.Collections.filter(provider_.getOrders(), new Filter()
		{
			@Override
			public <T> boolean allows(T arg) {
				if (arg instanceof StockOrder)
					return ((StockOrder<?>) arg).getType().equals(type_);
				return false;
			}
		}).size();
		return futureCount;
	}

	protected abstract int getNeeded();
}
