package warehouse.orderstrat;

import scheduler.TimeLord;
import warehouse.Warehouse;
import warehouse.item.PlasterType;
import warehouse.stock.StockProvider;

public class PlasterOrderStrategy extends OrderStrategy 
{

	public PlasterOrderStrategy( Warehouse warehouse,
			StockProvider provider, TimeLord timeLord) {
		super(new PlasterType(), warehouse, provider, timeLord);
	}

	@Override
	protected int getNeeded() {
		return warehouse_.getMaxCount(type_) - warehouse_.getCurrentCount(type_);
	}

}
