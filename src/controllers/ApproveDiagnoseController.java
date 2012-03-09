package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.Diagnose;
import users.Doctor;
import users.User;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.PatientFileIN;
import exceptions.ApproveDiagnoseException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class ApproveDiagnoseController extends NeedsLoginAndPatientFileController
{

	public ApproveDiagnoseController(LoginController lc, ConsultPatientFileController cpfc) throws InvalidLoginControllerException,
			InvalidHospitalException, InvalidPatientFileOpenController {
		super(lc, cpfc);
	}

	public Collection<PatientFileIN> getAllPatienFiles() {
		Collection<PatientFileIN> f = new ArrayList<PatientFileIN>();
		f.addAll(hospital.getPatientFileManager().getAllPatientFiles());
		return f;
	}

	public void approveDiagnose(DiagnoseIN selected) throws ApproveDiagnoseException {
		if (selected instanceof Diagnose)
			((Diagnose) selected).approve();
		else throw new ApproveDiagnoseException("Invalid Diagnose given to ApproveDiagnoseController!");
	}
	
	public void disapproveDiagnose(DiagnoseIN selected, DiagnoseIN replacement)
			throws  ApproveDiagnoseException {
		if (selected instanceof Diagnose)
			((Diagnose) selected).disaprove(replacement);

		//TODO: discard original treatment
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
