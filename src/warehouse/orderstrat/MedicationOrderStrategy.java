package warehouse.orderstrat;

import warehouse.Warehouse;
import warehouse.item.MedicationType;
import warehouse.stock.StockProvider;

/**
 * Class representing the order strategy for all medication warehouseitems.
 */
public class MedicationOrderStrategy extends OrderStrategy
{
	public MedicationOrderStrategy(MedicationType type, Warehouse warehouse,
			StockProvider provider) {
		super(type, warehouse, provider);
	}

	protected int getNeeded() {
		int curCount = warehouse_.getCurrentCount(type_);
		int maxCount = warehouse_.getMaxCount(type_);
		if (curCount > maxCount / 2)
			return 0;
		return maxCount - curCount;
	}
}
