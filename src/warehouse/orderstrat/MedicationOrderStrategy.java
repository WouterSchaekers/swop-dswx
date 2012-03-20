package warehouse.orderstrat;

import help.Filter;
import java.util.Observable;
import warehouse.Warehouse;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;
/**
 * Class representing the generic order strategy for most warehouseitems.
 */
public class MedicationOrderStrategy extends OrderStrategy
{
	
	public MedicationOrderStrategy(WarehouseItemType type, Warehouse warehouse,
			StockProvider provider) {
		super(type, warehouse, provider);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		int maxCount = warehouse_.getMaxCount(_type);
		int futureCount = currentCount();
		int orderCount = maxCount/2-futureCount;
		while(orderCount>0)
		{
			provider_.add(new StockOrder<WarehouseItemType>(warehouse_, _type));
			orderCount--;
		}
	}
	/**
	 * Calculates the amount of items that are in the warehouse of this type 
	 * added to the amount of items of that type that are ordered.
	 * @return
	 */
	private int currentCount() {
		int futureCount = warehouse_.getCurrentCount(_type);
		futureCount+=help.Collections.filter(provider_.getOrders(), new Filter()
		{
			
			@Override
			public <T> boolean allows(T arg) {
				if(arg instanceof StockOrder)
					return ((StockOrder<?>)arg).getType().equals(_type);
				return false;
			}
		}).size();
		return futureCount;
	}
	
}
