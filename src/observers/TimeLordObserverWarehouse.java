package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler.HospitalDate;
import users.WarehouseAdmin;

/**
 * Use this observer to notify a warehouse to update its stock.
 */
public class TimeLordObserverWarehouse implements Observer
{
	private WarehouseAdmin myWarehouseAdmin;
	
	/**
	 * Default constructor.
	 * 
	 * @param w
	 *            The WarehouseAdmin this observer should notify, should it get
	 *            notified.
	 */
	public TimeLordObserverWarehouse (WarehouseAdmin w) {
		this.myWarehouseAdmin = w;
	}
	
	@Override
	public void update(Observable o, Object newDate) {
		if (!(newDate instanceof HospitalDate))
			throw new IllegalArgumentException(
					"Object given to TimeLordObserver was not a hospital date!");
		this.myWarehouseAdmin.update((HospitalDate)newDate);
	}

}
