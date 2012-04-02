package controllers;

import java.util.Collection;
import system.Campus;
import users.User;
import users.WarehouseAdmin;
import warehouse.Warehouse;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;
import warehouse.item.WarehouseItemTypes;
import warehouse.stock.StockOrder;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class FillStockInWarehouseController extends NeedsLoginController
{
	private WarehouseAdmin ad = (WarehouseAdmin)(lc.getUser());
	private Campus camp = (Campus)(ad.getLocation());
	private Warehouse w = camp.getWarehouse();
	
	public FillStockInWarehouseController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	public Collection<WarehouseItemType> getItemTypes() {
		return WarehouseItemTypes.itemTypes();
	}

	public Collection<WarehouseItem> getWarehouseItems() {
		return w.getAllItems();
	}

	public Collection<StockOrder<?>> getAllStockOrders() {
		//Warehouse w = 
		camp.getWarehouse();
		//TODO fix
		return null;
	}
	
	public void addWarehouseItems(Collection<StockOrder<? extends WarehouseItemType>> items) {
		//TODO: fix
	}
	
	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}

}
