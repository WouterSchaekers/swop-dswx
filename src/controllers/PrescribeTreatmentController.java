package controllers;

import javax.naming.OperationNotSupportedException;
import system.Hospital;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.TreatmentIN;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidTreatmentException;

public class PrescribeTreatmentController extends NeedsLoginAndPatientFileController
{
	
	public PrescribeTreatmentController(LoginController loginc,
			PatientFileOpenController pfcontroller, Hospital state, PatientFileOpenController pfoc)
			throws InvalidLoginControllerException,
			InvalidPatientFileException, OperationNotSupportedException, InvalidHospitalStateException, InvalidPatientFileOpenController {
		super(state, loginc, pfoc);
	}

	//TODO: loziteit!!
	public TreatmentIN prescribeCast(DiagnoseIN diagnose, TreatmentIN treatment)
			throws InvalidTreatmentException, OperationNotSupportedException {
		throw new OperationNotSupportedException();
		// ((Diagnose) diagnose).assignTreatment((Treatment)treatment);
		// return null;
		//
		// Waarom is een DiagnoseIN een DiagnoseIN? Er kan een Diagnose in!! :D:D:D
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}
}
