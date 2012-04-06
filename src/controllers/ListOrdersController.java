package controllers;

import java.util.Collection;
import java.util.LinkedList;
import system.Campus;
import users.User;
import users.WarehouseAdmin;
import warehouse.item.WarehouseItemType;
import controllers.interfaces.StockOrderIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Use to list the pending orders from a warehouse.
 */
@controllers.PUBLICAPI
public class ListOrdersController extends NeedsLoginController
{
	/**************************************************
	 * Some fields to improve legibility of the code. *
	 *************************************************/
	private WarehouseAdmin admin = (WarehouseAdmin) (loginController_.getUser());
	private Campus campus = (Campus) (admin.getLocation());

	/**
	 * Default constructor.
	 * 
	 * @param lc
	 *            The login controller of the user that wants to list the orders
	 *            of a warehouse in the hospital.
	 * @throws InvalidLoginControllerException
	 *             If the user that owns the given login controller is not a
	 *             warehouse administrator or if the given controller is invalid
	 *             in any other way.
	 * @throws InvalidHospitalException
	 * @see NeedsLoginController
	 * @see HospitalController
	 */
	@controllers.PUBLICAPI
	public ListOrdersController(LoginController lc) throws InvalidLoginControllerException, InvalidHospitalException {
		super(lc);
	}

	/**
	 * @return All warehouse item types.
	 */
	@controllers.PUBLICAPI
	public Collection<WarehouseItemType> getCategories() {
		return campus.getWarehouse().getAvailableItemTypes();
	}

	/**
	 * Use to get all pending undelivered stock orders for the specified item
	 * type.
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

	/**
	 * @return True if the given user is a warehouse administrator.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}
}