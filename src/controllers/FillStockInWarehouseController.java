package controllers;

import system.HospitalState;
import users.User;
import users.WarehouseAdmin;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

public class FillStockInWarehouseController extends NeedsLoginController
{

	public FillStockInWarehouseController(HospitalState hospitalState,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, controller);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}

	

}
