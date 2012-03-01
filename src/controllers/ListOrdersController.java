package controllers;

import java.util.LinkedList;
import system.HospitalState;
import users.User;
import users.WarehouseAdmin;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;
import exceptions.InvalidCategoryNameException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

/**
 * This controller allows you to get the stock orders placed by a warehouse admin.
 */
//TODO: fix
public class ListOrdersController extends NeedsLoginController
{
	/**
	 * Default constructor.
	 * @param loginController
	 * @param hospitalState
	 * @throws InvalidLoginControllerException
	 * @throws InvalidHospitalStateException
	 */
	public ListOrdersController(LoginController loginController, HospitalState hospitalState)
			throws InvalidLoginControllerException, InvalidHospitalStateException {
		super(hospitalState, loginController);
	}

	

	public LinkedList<? extends StockOrder> getCorrespondingOrderedItems(
			StockProvider stockProvider)
			throws InvalidCategoryNameException {
				return null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}
}