package controllers;

import java.util.Collection;
import java.util.LinkedList;
import system.Campus;
import users.User;
import users.WarehouseAdmin;
import warehouse.item.WarehouseItemType;
import warehouse.item.WarehouseItemTypes;
import controllers.interfaces.StockOrderIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Use to list the orders from a warehouse.
 */
@controllers.PUBLICAPI
public class ListOrdersController extends NeedsLoginController
{
	private WarehouseAdmin admin = (WarehouseAdmin) (loginController_.getUser());
	private Campus campus = (Campus) (admin.getLocation());

	@controllers.PUBLICAPI
	public ListOrdersController(LoginController lc) throws InvalidLoginControllerException, InvalidHospitalException {
		super(lc);
	}

	/**
	 * @return The categories of warehouse items.
	 */
	@controllers.PUBLICAPI
	public Collection<WarehouseItemType> getCategories() {
		return WarehouseItemTypes.itemTypes();
	}

	/**
	 * Use to get all open undelivered stock orders for a specified item type.
	 */
	@controllers.PUBLICAPI
	public LinkedList<StockOrderIN> getStockOrders(WarehouseItemType selected) {
		LinkedList<StockOrderIN> rv = new LinkedList<StockOrderIN>();
		Collection<StockOrderIN> orders = campus.getStockprovider().getOrderINs();
		for (StockOrderIN order : orders) {
			if (!order.hasBeenDelivered())
				rv.add(order);
		}
		return rv;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}
}