package controllers;

import java.util.LinkedList;
import users.User;
import users.WarehouseAdmin;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;
import exceptions.InvalidCategoryNameException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class ListOrdersController extends NeedsLoginController
{

	public ListOrdersController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	public String getCategories() {
		// TODO: Gebruiken we hier een string?
		return null;
	}

	public LinkedList<? extends StockOrder<?>> getCorrespondingOrderedItems(
			StockProvider stockProvider) throws InvalidCategoryNameException {
		return null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}
}