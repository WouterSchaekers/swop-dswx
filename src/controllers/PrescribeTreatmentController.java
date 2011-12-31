package controllers;

import javax.naming.OperationNotSupportedException;
import patient.Diagnose;
import treatment.Treatment;
import users.Doctor;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidTreatmentException;
public class PrescribeTreatmentController
{

	private LoginController loginc;
	private PatientFileOpenController pfcontroller;

	public PrescribeTreatmentController(LoginController loginc,PatientFileOpenController pfcontroller) throws InvalidLoginControllerException, InvalidPatientFileException, OperationNotSupportedException {
		throw new OperationNotSupportedException();
//		if(!isValidLoginController(loginc))
//			throw new InvalidLoginControllerException("");
//		if(!isValidPatienFileOpenController(pfcontroller))
//			throw new InvalidPatientFileException();
//		this.loginc=loginc;
//		this.pfcontroller=pfcontroller;
	}

	private boolean isValidPatienFileOpenController(
			PatientFileOpenController pfcontroller) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isValidLoginController(LoginController loginController) {
		if(loginController==null)
			return false;
		if(!(loginController.getUser() instanceof Doctor))
			return false;
		if(this.loginc!=null && this.loginc.equals(loginController))
			return false;
		return true;
	}

	public TreatmentIN prescribeCast(DiagnoseIN diagnose,TreatmentIN  treatment) throws InvalidTreatmentException, OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
//		((Diagnose) diagnose).assignTreatment((Treatment)treatment);
//		return null;
		
	}
}
