package ui.listorders;

import ui.Usecase;
import ui.UserinterfaceData;
import users.WarehouseAdmin;
import warehouse.StockProvider;
import controllers.DataPasser;
import controllers.ListOrdersController;
import exceptions.InvalidLoginControllerException;

public class ListOrders extends ListOrdersSuper
{
	DataPasser dataPasser;
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
		StockProvider stockProvider = this.dataPasser.getWarehouseAdmin().getStockProvider();
		return null;
	}
}
