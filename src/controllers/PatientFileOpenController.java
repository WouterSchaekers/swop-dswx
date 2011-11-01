package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import users.Doctor;
import users.User.usertype;

public class PatientFileOpenController
{
	DataPasser data;
	DTOUser doctor;
	
	public PatientFileOpenController(DataPasser data, LoginController loginController) {
		this.data = data;
		if (loginController == null)
			throw new NullPointerException("Logincontroller is null");

		if (loginController.getUserDTO() == null)
			throw new NullPointerException("User is null");

		if (loginController.getUserDTO().type() == null)
			throw new NullPointerException("Type of user is null");

		if (loginController.getUserDTO().type() != usertype.Doctor)
			throw new IllegalArgumentException("This user is not a doctor!");

		doctor = loginController.getUserDTO();
	}

	public Collection<DTOPatientFile> getAllPatientFiles(
			LoginController loginController) {
		ArrayList<DTOPatientFile> RV = new ArrayList<DTOPatientFile>();
		for (PatientFile file : data.getPatientFileManager()
				.getAllPatientFiles())
			RV.add(new DTOPatientFile(file));
		return RV;
	}

	public boolean validLoginController(LoginController loginController) {
		if (loginController == null)
			return false;

		if (loginController.getUserDTO() == null)
			return false;

		if (loginController.getUserDTO().type() == null)
			return false;

		if (loginController.getUserDTO().type() != usertype.Doctor)
			return false;

		if (loginController.getUserDTO() != doctor)
			return false;

		return true;
	}

	PatientFile pf;
	
	public void openPatientFile(DTOPatientFile pfdto) {
		this.pf = pfdto.getPatientFile();
	}

	public DTOPatientFile getPatientFile() {
		return new DTOPatientFile(this.pf);
	}
	
	public DTOUser getDocDTO() {
		return this.doctor;
	}

}
