package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import system.HospitalState;
import users.Doctor;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidLoginControllerException;

public class PatientFileOpenController
{
	HospitalState data;
	DoctorIN doctor;
	LoginController lc;

	public PatientFileOpenController(HospitalState data,
			LoginController loginController)
			throws InvalidLoginControllerException {
		this.data = data;
		if (!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		this.lc = loginController;
		doctor = (DoctorIN) loginController.getUser();
	}

	public boolean isValidLoginController(LoginController loginController) {
		if (loginController == null)
			return false;

		if (loginController.getUser() == null)
			return false;

		if (!(loginController.getUser() instanceof Doctor))
			return false;

		if (this.doctor!=null&&!loginController.getUser().equals(doctor))
			return false;

		return true;
	}

	public LoginController getLoginController() {
		return this.lc;
	}

	public Collection<PatientFileIN> getAllPatientFiles(
			LoginController loginController) {
		ArrayList<PatientFileIN> RV = new ArrayList<PatientFileIN>();
		for (PatientFile file : data.getPatientFileManager()
				.getAllPatientFiles())
			RV.add(file);
		return RV;
	}


	PatientFile pf;

	public void openPatientFile(PatientFileIN pfdto,LoginController loginc) throws InvalidLoginControllerException {
		if(!isValidLoginController(loginc))
			throw new InvalidLoginControllerException("");
		if (pfdto instanceof PatientFile)
			this.pf = (PatientFile) pfdto;
		else
			throw new IllegalArgumentException(pfdto
					+ " is not a valid patient file");
	}

	public PatientFileIN getPatientFile() {
		return this.pf;
	}

	public UserIN getDocIN() {
		return this.doctor;
	}

	public void closePatientFile(LoginController loginc)
			throws InvalidLoginControllerException {
		if (!isValidLoginController(loginc))
			throw new InvalidLoginControllerException("");
		this.data = null;
		this.doctor = null;
		this.lc = null;
	}

}
