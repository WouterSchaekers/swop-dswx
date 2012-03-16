package DietersSandbox;

import java.util.Collection;
import users.User;
import users.WarehouseAdmin;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;
import warehouse.stock.StockOrder;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class FillStockInWarehouseController extends NeedsLoginController
{

	public FillStockInWarehouseController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	public Collection<WarehouseItemType> getItemTypes() {
		return null;
		//TODO: fix
	}

	public Collection<WarehouseItem> getWarehouseItems() {
		return null;
		// TODO: fix
	}

	public Collection<StockOrder<?>> getAllStockOrders() {
		return null;
		//TODO: fix
	}
	
	public void addWarehouseItems(Collection<WarehouseItem> items) {
		//TODO: fix
	}
	
	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}

}
