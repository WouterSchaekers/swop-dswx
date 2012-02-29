package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.Diagnose;
import system.HospitalState;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;
import exceptions.ApproveDiagnoseException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

public class ApproveDiagnosisController extends NeedsLoginController
{

	public ApproveDiagnosisController(HospitalState hospitalState,
			LoginController loginc) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, loginc);
	}

	public Collection<PatientFileIN> getAllPatienFiles() {
		Collection<PatientFileIN> f = new ArrayList<PatientFileIN>();
		f.addAll(hospitalState.getPatientFileManager().getAllPatientFiles());
		return f;
	}

	public DiagnoseIN approveDiagnose(LoginController loginController2,
			DiagnoseIN selected) throws InvalidLoginControllerException,
			ApproveDiagnoseException {
		if (!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if (selected instanceof Diagnose)
			((Diagnose) selected).approve();
		return selected;

	}

	public void disApproveDiagnose(LoginController loginController2,
			DiagnoseIN selected, DiagnoseIN replacement)
			throws InvalidLoginControllerException, ApproveDiagnoseException {
		if (!isValidLoginController(loginController2))
			throw new InvalidLoginControllerException("");
		if (selected instanceof Diagnose)
			((Diagnose) selected).disaprove(replacement);

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
