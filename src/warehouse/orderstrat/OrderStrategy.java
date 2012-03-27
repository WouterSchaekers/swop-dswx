package warehouse.orderstrat;

import help.Filter;
import java.util.Observable;
import java.util.Observer;
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
	 * To observe.
	 */
	public OrderStrategy(WarehouseItemType type, Warehouse warehouse,
			StockProvider provider, TimeLord timeLord) {
		type_ = type;
		warehouse_ = warehouse;
		provider_ = provider;
		warehouse.addObserver(this);
		timeLord.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		int needed = this.getNeeded() - amountOfOpenOrders()-warehouse_.getCurrentCount(type_);
		while(needed>0)
		{
			provider_.add(new StockOrder<WarehouseItemType>(warehouse_, type_, warehouse_.getCampus().getSystemTime().getSystemTime()));
			needed--;
		}
		}

	private int amountOfOpenOrders() {
		int futureCount = help.Collections.filter(provider_.getOrders(),
				new Filter()
				{
					@Override
					public <T> boolean allows(T arg) {
						if (arg instanceof StockOrder)
							return ((StockOrder<?>) arg).getType()
									.equals(type_);
						return false;
					}
				}).size();
		return futureCount;
	}

	protected abstract int getNeeded();
}
