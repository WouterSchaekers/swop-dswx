package warehouse.orderstrat;

import warehouse.Warehouse;
import warehouse.item.PlasterType;
import warehouse.stock.StockProvider;

public class PlasterOrderStrategy extends OrderStrategy 
{

	public PlasterOrderStrategy( Warehouse warehouse,
			StockProvider provider) {
		super(new PlasterType(), warehouse, provider);
	}

	@Override
	protected int getNeeded() {
		return warehouse_.getMaxCount(type_) - warehouse_.getCurrentCount(type_);
	}

}
