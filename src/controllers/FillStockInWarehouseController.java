package controllers;

import system.Hospital;
import users.User;
import users.WarehouseAdmin;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class FillStockInWarehouseController extends NeedsLoginController
{

	public FillStockInWarehouseController(Hospital hospital,
			LoginController controller) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(hospital, controller);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof WarehouseAdmin;
	}

}
