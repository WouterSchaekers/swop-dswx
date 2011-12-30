package controllers;

import users.Nurse;
import exceptions.InvalidLoginControllerException;

public class EnterMedicaltestResultController
{
	LoginController logincontroller;
	public EnterMedicaltestResultController(LoginController loginController,
			DataPasser dataPasser) throws InvalidLoginControllerException {
		if(!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		this.logincontroller=loginController;
	}
	private boolean isValidLoginController(LoginController loginController2) {
		if(loginController2==null)
			return false;
		if(logincontroller!=null&&!logincontroller.equals(loginController2))
			return false;
		if(loginController2.getUser()==null)
			return false;
		if(!(loginController2.getUser() instanceof Nurse))
			return false;
		
		return true;
	}

}
