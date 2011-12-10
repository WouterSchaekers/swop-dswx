package controllers;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import patient.PatientFile;
import users.UserType;

public class PatientFileOpenController
{
	DataPasser data;
	DoctorIN doctor;
	LoginController lc;

	public PatientFileOpenController(DataPasser data,
			LoginController loginController) {
		this.data = data;
		if (loginController == null)
			throw new NullPointerException("Logincontroller is null");

		if (loginController.getUser() == null)
			throw new NullPointerException("User is null");

		if (loginController.getUser().type() == null)
			throw new NullPointerException("Type of user is null");

		if (loginController.getUser().type() != UserType.Doctor)
			throw new IllegalArgumentException("This user is not a doctor!");

		this.lc = loginController;
		doctor = (DoctorIN) loginController.getUser();
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

	public boolean validLoginController(LoginController loginController) {
		if (loginController == null)
			return false;

		if (loginController.getUser() == null)
			return false;

		if (loginController.getUser().type() == null)
			return false;

		if (loginController.getUser().type() != UserType.Doctor)
			return false;

		if (loginController.getUser() != doctor)
			return false;

		return true;
	}

	PatientFile pf;

	public void openPatientFile(PatientFileIN pfdto) {
		if(pfdto instanceof PatientFile)
			this.pf = (PatientFile) pfdto;
		else
			throw new IllegalArgumentException(pfdto+" is not a valid patient file");
	}

	public PatientFileIN getPatientFile() {
		return this.pf;
	}

	public UserIN getDocIN() {
		return this.doctor;
	}

}
