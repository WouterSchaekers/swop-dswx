package controllers;

import help.Collections;
import help.Filter;
import java.util.Collection;
import patient.Diagnose;
import patient.PatientFile;
import patient.PatientFileManager;
import users.Doctor;
import controllers.DataPasser;
import controllers.LoginController;
import exceptions.InvalidLoginControllerException;

public class ApproveDiagnosisController
{
	LoginController loginController;
	
	public ApproveDiagnosisController(LoginController loginc) throws InvalidLoginControllerException {
		if(!isValidLoginController(loginc))
			throw new InvalidLoginControllerException("invalid");
				this.loginController=loginc;
	}

	private boolean isValidLoginController(LoginController loginc) {
		if(loginc==null)
			return false;
		if(!loginc.loggedIn())
			return false;
		if(!(loginc.getUserIN() instanceof Doctor))
			return false;
		return true;
	}

	public Collection<PatientFile> getAllPatienFiles(DataPasser dataPasser) {
		return dataPasser.getPatientFileManager().getAllPatientFiles();
		
	}

}
