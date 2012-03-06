package ui.listorders;

import java.util.LinkedList;
import system.Hospital;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import warehouse.StockOrder;
import warehouse.StockProvider;
import controllers.ListOrdersController;
import exceptions.InvalidCategoryNameException;
import exceptions.InvalidLoginControllerException;

public class ListOrders extends ListOrdersSuper
{
	Hospital dataPasser;

	public ListOrders(UserinterfaceData data) {
		super(data);
		dataPasser = data.getDataPasser();
	}

	@Override
	public Usecase Execute() {
		ListOrdersController listOrdersController;
		try {
			listOrdersController = new ListOrdersController(
					data.getLoginController());
		} catch (InvalidLoginControllerException e) {
			return null;
		}
		StockProvider stockProvider = null;
		// TODO: make this StockProvider stockProvider =
		// this.dataPasser.getWarehouseAdmin().getStockProvider();
		LinkedList<String> stockItemNames = listOrdersController
				.getStockItemNames(stockProvider);
		System.out.println("Please choose a category.");
		for (int i = 0; i < stockItemNames.size(); i++) {
			System.out.println(i + ". " + stockItemNames.size());
		}
		LinkedList<? extends StockOrder> orderedItems = null;
		boolean isValidInput = false;
		while (!isValidInput) {
			String itemName = input.nextLine();
			try {
				orderedItems = listOrdersController
						.getCorrespondingOrderedItems(stockProvider, itemName);
				isValidInput = true;
			} catch (InvalidCategoryNameException e) {
				System.out.println(e.toString());
			}
		}
		System.out
				.println("The 20 latest placed orders of the selected category:");
		for (int i = orderedItems.size() - 1; i > 0
				&& i >= orderedItems.size() - 20; i++) {
			System.out.println(orderedItems.get(i).toString());
		}
		return new SelectUsecase(data);
	}
}
