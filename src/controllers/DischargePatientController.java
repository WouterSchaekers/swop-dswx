package controllers;

import users.Doctor;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
public class DischargePatientController
{

	private LoginController logincontroller;
	private PatientFileOpenController patientfilecontroller;

	public DischargePatientController(LoginController loginController,
			PatientFileOpenController patienfile) throws InvalidLoginControllerException, InvalidPatientFileException {
		if(!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		if(!isValidPatientFileOpenController(patienfile, loginController))
			throw new InvalidPatientFileException();
		this.logincontroller =loginController;
		this.patientfilecontroller=patienfile;
	}

	private boolean isValidPatientFileOpenController(
			PatientFileOpenController patienfile,LoginController loginController) {
		return patienfile.isValidLoginController(loginController);
	}

	private boolean isValidLoginController(LoginController loginController) {
		if(loginController==null)
			return false;
		if(!(loginController.getUser()instanceof Doctor))
			return false;
		if(this.logincontroller!=null&&this.logincontroller.equals(loginController))
			return false;
		return true;
		
	}

}
