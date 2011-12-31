package observers;

import java.util.Observable;
import java.util.Observer;
import users.WarehouseAdmin;

public class WarehouseObserverWarehouseAdmin implements Observer
{
	private WarehouseAdmin warehouseAdmin;

	public WarehouseObserverWarehouseAdmin(WarehouseAdmin warehouseAdmin) {
		this.warehouseAdmin = warehouseAdmin;
	}

	@Override
	public void update(Observable arg0, Object arg) {
		try {
			this.warehouseAdmin.updateStock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}