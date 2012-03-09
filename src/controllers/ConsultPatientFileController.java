package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import system.Hospital;
import users.Doctor;
import users.User;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * A controller that allows you to open patientfiles.
 */
public class ConsultPatientFileController extends NeedsLoginController
{
	private Hospital hospital;
	private DoctorIN doctor;
	private PatientFile pf;

	public ConsultPatientFileController(LoginController loginController)
			throws InvalidHospitalException,
			InvalidLoginControllerException {
		super(loginController.hospital, loginController);
		this.hospital = hospital;
		doctor = (DoctorIN) loginController.getUser();
	}
	
	public Collection<PatientFileIN> getActivePatientFiles() {
		ArrayList<PatientFileIN> pfs = new ArrayList<PatientFileIN>(hospital.getPatientFileManager()
				.getAllPatientFiles());
		//TODO: filterfix		
		return null;
	}

	public void openPatientFile(PatientFileIN pfdto) {
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

	public void closePatientFile() {
		this.hospital = null;
		this.doctor = null;
		this.pf = null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
