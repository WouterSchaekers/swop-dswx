package controllers;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import system.Campus;
import users.User;
import users.WarehouseAdmin;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;
import warehouse.item.WarehouseItemTypes;
import warehouse.stock.StockOrder;
import controllers.interfaces.StockOrderIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidOrderStateException;
import exceptions.WarehouseOverCapacityException;

/**
 * Use to fill the stock of a warehouse.
 */
@controllers.PUBLICAPI
public class FillStockInWarehouseController extends NeedsLoginController
{
	/**
	 * The campus of the warehouse admin that created this controller. Only
	 * declared to improve legibility.
	 */
	private Campus campus = (Campus) (((WarehouseAdmin) (loginController_.getUser())).getLocation());

	/**
	 * Default constructor.
	 * 
	 * @param loginController
	 *            The login controller of the user that wants to fill the stock
	 *            in a warehouse.
	 * @throws InvalidLoginControllerException
	 *             If the given login controller is not from a warehouse
	 *             administrator, or is invalid in any other way.
	 * @throws InvalidHospitalException
	 * @see NeedsLoginController
	 * @see HospitalController
	 */
	@controllers.PUBLICAPI
	public FillStockInWarehouseController(LoginController loginController) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(loginController);
	}

	/**
	 * Gets the pending stock orders from the warehouse of the admin that has
	 * logged in.
	 */
	@controllers.PUBLICAPI
	public Collection<StockOrderIN> getAllStockOrders() {
		return campus.getStockprovider().getOrderINs();
	}

	/**
	 * Delivers the given stock orders to the warehouse of the admin who has
	 * logged in.
	 * 
	 * @param expiryDate
	 *            The desired expiry date for the selected items.
	 * @throws WarehouseOverCapacityException
	 *             If the warehouse's capacity has been exceeded by adding the
	 *             given orders.
	 * @throws InvalidOrderStateException
	 *             If one or more of the given orders cannot be delivered yet.
	 */
	@controllers.PUBLICAPI
	@SuppressWarnings("unchecked")
	public <T extends WarehouseItemType> void deliverItems(Collection<StockOrderIN> selectedItems,
			HospitalDate expiryDate) throws WarehouseOverCapacityException, InvalidOrderStateException {
		Collection<StockOrder<T>> items = new LinkedList<StockOrder<T>>();
		for (StockOrderIN order : selectedItems)
			items.add((StockOrder<T>) order);
		for (StockOrder<T> order : items) {
			order.deliver(expiryDate);
		}
	}

	/**
	 * Use to get all items currently in the warehouse to get an accurate count
	 * of the current stock pile.
	 * 
	 * @return All warehouse items in the warehouse of the warehouse admin that
	 *         has created this controller.
	 */
	public Collection<WarehouseItem> getAllWarehouseItems() {
		return new LinkedList<WarehouseItem>(campus.getWarehouse().getAllItems());
	}

	/**
	 * Use to get the different kinds of item types that can be added and stored
	 * in the warehouse of the admin that created this controller.
	 * 
	 * @return All warehouse item types.
	 */
	public Collection<WarehouseItemType> getAllWarehouseItemTypes() {
		return WarehouseItemTypes.itemTypes();
	}

	/**
	 * @return True if the given user is a warehouse administrator.
	 */
	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}

}
