package controllers;

import java.util.LinkedList;
import users.WarehouseAdmin;
import warehouse.StockOrder;
import warehouse.StockProvider;
import exceptions.InvalidCategoryNameException;
import exceptions.InvalidLoginControllerException;

public class ListOrdersController
{
	LoginController loginController;

	public ListOrdersController(LoginController loginController)
			throws InvalidLoginControllerException {
		this.loginController = loginController;
		if (!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		this.loginController = loginController;
	}

	private boolean isValidLoginController(LoginController loginController) {
		if (loginController == null)
			return false;
		if (!(loginController.getUser() instanceof WarehouseAdmin))
			return false;
		if (this.loginController != null
				&& this.loginController.equals(loginController))
			return false;
		return true;
	}

	public LinkedList<String> getStockItemNames(StockProvider stockProvider) {
		return stockProvider.getStockItemNames();
	}

	public LinkedList<? extends StockOrder> getCorrespondingOrderedItems(
			StockProvider stockProvider, String itemName)
			throws InvalidCategoryNameException {
		return stockProvider.getCorrespondingOrderedItems(itemName);
	}
}