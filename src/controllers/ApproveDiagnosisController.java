package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.Diagnose;
import sun.rmi.runtime.Log;
import system.Hospital;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;
import exceptions.ApproveDiagnoseException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

public class ApproveDiagnosisController extends NeedsLoginController
{

	public ApproveDiagnosisController(Hospital hospitalState,
			LoginController loginc) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(hospitalState, loginc);
	}

	public Collection<PatientFileIN> getAllPatienFiles() {
		Collection<PatientFileIN> f = new ArrayList<PatientFileIN>();
		f.addAll(hospitalState.getPatientFileManager().getAllPatientFiles());
		return f;
	}

	// XXX:waarom iets terug geven
	public DiagnoseIN approveDiagnose(DiagnoseIN selected)
			throws ApproveDiagnoseException {
		if (selected instanceof Diagnose)
			((Diagnose) selected).approve();
		return selected;

	}
	
	public void disApproveDiagnose(DiagnoseIN selected, DiagnoseIN replacement)
			throws  ApproveDiagnoseException {
		if (selected instanceof Diagnose)
			((Diagnose) selected).disaprove(replacement);

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
