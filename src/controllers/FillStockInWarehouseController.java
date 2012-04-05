package controllers;

import java.util.Collection;
import java.util.LinkedList;
import scheduler.HospitalDate;
import system.Campus;
import users.User;
import users.WarehouseAdmin;
import warehouse.item.WarehouseItemType;
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
	private Campus campus = (Campus)(((WarehouseAdmin)(loginController_.getUser())).getLocation());

	@controllers.PUBLICAPI
	public FillStockInWarehouseController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}
	
	/**
	 * Gets the stock orders from the warehouse of the admin that has logged in.
	 */
	@controllers.PUBLICAPI
	public Collection<StockOrderIN> getAllStockOrders() {
		return campus.getStockprovider().getOrderINs();
	}

	/**
	 * Delivers the given items to the warehouse of the admin who has logged in.
	 * 
	 * @param expiryDate
	 *            The expiry date for the selected items.
	 * @throws WarehouseOverCapacityException
	 * @throws InvalidOrderStateException
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

	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}

}
