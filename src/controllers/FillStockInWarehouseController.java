package controllers;

import users.WarehouseAdmin;
import exceptions.InvalidLoginControllerException;

public class FillStockInWarehouseController
{

	private LoginController loginController;
	public FillStockInWarehouseController(LoginController loginController,
			DataPasser dataPasser) throws InvalidLoginControllerException {
		if(!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		// TODO Auto-generated constructor stub
	}
	private boolean isValidLoginController(LoginController loginController) {
		if(loginController==null)
			return false;
		if(!(loginController.getUser() instanceof WarehouseAdmin))
			return false;
		if(this.loginController!=null && this.loginController.equals(loginController))
			return false;
		return true;
	}

}
