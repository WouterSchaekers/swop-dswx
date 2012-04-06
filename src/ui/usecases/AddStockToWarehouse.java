package ui.usecases;

import controllers.FillStockInWarehouseController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import ui.UIData;
import ui.UseCase;

public class AddStockToWarehouse extends UseCase
{

	private FillStockInWarehouseController controller;

	public AddStockToWarehouse(UIData data) throws InvalidLoginControllerException, InvalidHospitalException {
		super(data, 12);//magic numbers <3
		controller =new  FillStockInWarehouseController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		
		// TODO Auto-generated method stub
		return null;
	}

}
