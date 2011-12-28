package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;
import warehouse.Warehouse;

/**
 * Use this observer to notify a warehouse to update its stock.
 */
public class TimeLordObserverWarehouse implements Observer
{
	private Warehouse myWarehouse;
	
	/**
	 * Default constructor.
	 * 
	 * @param w
	 *            The Warehouse this observer should notify, should it get
	 *            notified.
	 */
	public TimeLordObserverWarehouse(Warehouse w) {
		this.myWarehouse = w;
	}
	
	@Override
	public void update(Observable o, Object newDate) {
		if (!(newDate instanceof HospitalDate))
			throw new IllegalArgumentException(
					"Object given to TimeLordObserver was not a hospital date!");
		this.myWarehouse.update((HospitalDate)newDate);
	}

}
