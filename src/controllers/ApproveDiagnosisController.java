package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.Diagnose;
import users.Doctor;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;
import exceptions.ApproveDiagnoseException;
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
		if(this.loginController!=null&&!this.loginController.equals(loginc))
			return false;
		return true;
	}

	public Collection<PatientFileIN> getAllPatienFiles(DataPasser dataPasser) {
		Collection<PatientFileIN> f = new ArrayList<PatientFileIN>();
		f.addAll(dataPasser.getPatientFileManager().getAllPatientFiles());
		return f;
	}

	public DiagnoseIN approveDiagnose(LoginController loginController2,
			DiagnoseIN selected) throws InvalidLoginControllerException, ApproveDiagnoseException {
		if(!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if(selected instanceof Diagnose)
			((Diagnose)selected).approve();
		return selected;
		
	}

	public void disApproveDiagnose(LoginController loginController2,
			DiagnoseIN selected,DiagnoseIN replacement) throws InvalidLoginControllerException {
		if(!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if(selected instanceof Diagnose)
			try {
				((Diagnose)selected).disaprove(replacement);
			} catch (ApproveDiagnoseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
