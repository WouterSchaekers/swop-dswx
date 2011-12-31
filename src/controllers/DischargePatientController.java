package controllers;

import patient.PatientFile;
import users.Doctor;
import exceptions.DischargePatienException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
public class DischargePatientController
{

	private LoginController logincontroller;
	public DischargePatientController(LoginController loginController,
			PatientFileOpenController patienfile) throws InvalidLoginControllerException, InvalidPatientFileException {
		if(!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		if(!isValidPatientFileOpenController(patienfile, loginController))
			throw new InvalidPatientFileException();
		this.logincontroller =loginController;
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
	
	public void dischargePatient(LoginController loginc,PatientFileOpenController pfc,DataPasser dataPasser) throws InvalidLoginControllerException, InvalidPatientFileException, DischargePatienException
	{
		if(!isValidLoginController(loginc))
			throw new InvalidLoginControllerException("");
		if(!isValidPatientFileOpenController(pfc, loginc))
			throw new InvalidPatientFileException();
		dataPasser.getPatientFileManager().checkOut((PatientFile) pfc.getPatientFile());
		
	}
}
