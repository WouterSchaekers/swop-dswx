package ui.usecases;

import java.util.Collection;
import controllers.FillStockInWarehouseController;
import controllers.interfaces.StockOrderIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import ui.UIData;
import ui.UseCase;
import warehouse.item.WarehouseItem;
import warehouse.item.WarehouseItemType;

public class AddStockToWarehouse extends UseCase
{

	private FillStockInWarehouseController controller;

	public AddStockToWarehouse(UIData data) throws InvalidLoginControllerException, InvalidHospitalException {
		super(data, 12);//magic numbers <3
		controller =new  FillStockInWarehouseController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		System.out.println("Add stock to warehouse.");
		
		printItemts();
		System.out.println("Do you want to add more stockorders?");
		if(Selector.yesNoSelector.get())
			
		{
			Selector<WarehouseItemType> types = new Selector<WarehouseItemType>(controller.getAllWarehouseItemTypes(), new Selector.Displayer<WarehouseItemType>()
			{

				@Override
				public void display(WarehouseItemType t) {
				print(t.name());
					
				}
			});
			WarehouseItemType selected = types.get();
			System.out.println(selected.name()+" was chosen.");
			try{
				print("amount:");
				int i = new Integer(read());
				controller.addStockOrder(selected,i);
			}catch(Exception e)
			{
				print("Invalid number");
				return this;
			}
			return mm();
		}else
		{
			System.out.println("Ok nothing orderd");
			return mm();
		}}

	private void printItemts() {

		printLn("Name\t\tcount\t\t ordered");
		for(WarehouseItemType type : controller.getAllWarehouseItemTypes())
		{
			printLn(type.name()+"\t\t"+amount(type,controller.getAllWarehouseItems())+"\t\t"+amountOrdered(type,controller.getAllStockOrders()));
		}
		
	}

	private String amountOrdered(WarehouseItemType type, Collection<StockOrderIN> allStockOrders) {
		int i = 0;
		for(StockOrderIN item:allStockOrders)
			if(type.equals(item.getType()))
				i++;
		return i+"";
	}

	private String amount(WarehouseItemType type, Collection<WarehouseItem> allWarehouseItems) {
		int i = 0;
		for(WarehouseItem item:allWarehouseItems)
			if(type.equals(item.getType()))
				i++;
		return i+"";
	}

}
