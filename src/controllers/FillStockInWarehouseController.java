package controllers;

import system.Hospital;
import users.User;
import users.WarehouseAdmin;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

public class FillStockInWarehouseController extends NeedsLoginController
{

	public FillStockInWarehouseController(Hospital hospitalState,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, controller);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}

}
