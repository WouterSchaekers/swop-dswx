package controllers;

import users.WarehouseAdmin;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;

public class PrescribeTreatmentController
{

	private LoginController loginc;
	private PatientFileOpenController pfcontroller;

	public PrescribeTreatmentController(LoginController loginc,PatientFileOpenController pfcontroller) throws InvalidLoginControllerException, InvalidPatientFileException {
		if(!isValidLoginController(loginc))
			throw new InvalidLoginControllerException("");
		if(!isValidPatienFileOpenController(pfcontroller))
			throw new InvalidPatientFileException();
		this.loginc=loginc;
		this.pfcontroller=pfcontroller;
	}

	private boolean isValidPatienFileOpenController(
			PatientFileOpenController pfcontroller) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isValidLoginController(LoginController loginController) {
		if(loginController==null)
			return false;
		if(!(loginController.getUser() instanceof WarehouseAdmin))
			return false;
		if(this.loginc!=null && this.loginc.equals(loginController))
			return false;
		return true;
	}
}
