package warehouse.stock;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import scheduler.TimeLord;
import warehouse.Warehouse;
import warehouse.item.WarehouseItem;

/**
 * Removes expired items from the warehouse once the system time has been
 * changed.
 */
public class ExpiredItemRemover implements Observer
{
	private Warehouse warehouse_;
	private TimeLord timeLord_;

	public ExpiredItemRemover(Warehouse warehouse, TimeLord systemTime) {
		this.warehouse_ = warehouse;
		this.timeLord_ = systemTime;
	}

	@Override
	public void update(Observable o, Object arg) {
		LinkedList<WarehouseItem> whItems = this.warehouse_.getAllItems();
		for (WarehouseItem i : whItems)
			if (i.isExpiredAt(this.timeLord_.getSystemTime()))
				this.warehouse_.removeItem(i);
	}
}
