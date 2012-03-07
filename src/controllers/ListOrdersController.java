package controllers;

import java.util.LinkedList;
import system.Hospital;
import users.User;
import users.WarehouseAdmin;
import warehouse.stock.StockOrder;
import warehouse.stock.StockProvider;
import exceptions.InvalidCategoryNameException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class ListOrdersController extends NeedsLoginController
{

	public ListOrdersController(LoginController loginController,
			Hospital hospital) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, loginController);
	}

	public String getCategories() {
		// TODO: zelfde commentaar als bij de andere string dingen
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