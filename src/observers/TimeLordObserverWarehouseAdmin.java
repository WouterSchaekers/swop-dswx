package observers;

import java.util.Observable;
import java.util.Observer;
import scheduler2.HospitalDate;
import users.WarehouseAdmin;

/**
 * Use this observer to notify a warehouse to update its stock.
 */
public class TimeLordObserverWarehouseAdmin implements Observer
{
	private WarehouseAdmin warehouseAdmin;

	/**
	 * Default constructor.
	 * 
	 * @param w
	 *            The WarehouseAdmin this observer should notify, should it get
	 *            notified.
	 */
	public TimeLordObserverWarehouseAdmin(WarehouseAdmin warehouseAdmin) {
		this.warehouseAdmin = warehouseAdmin;
	}

	@Override
	public void update(Observable o, Object newDate) {
		if (!(newDate instanceof HospitalDate))
			throw new IllegalArgumentException(
					"Object given to TimeLordObserver was not a hospital date!");
		try {
			this.warehouseAdmin.updateTime((HospitalDate) newDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}