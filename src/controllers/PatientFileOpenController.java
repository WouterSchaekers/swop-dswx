package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import system.HospitalState;
import users.Doctor;
import users.User;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;

/**
 * A controller that allows you to open patientfiles.
 */
public class PatientFileOpenController extends NeedsLoginController
{
	private HospitalState data;
	private DoctorIN doctor;
	private PatientFile pf;

	public PatientFileOpenController(HospitalState data,
			LoginController loginController)
			throws InvalidHospitalStateException,
			InvalidLoginControllerException {
		super(data, loginController);
		this.data = data;
		doctor = (DoctorIN) loginController.getUser();
	}

	public Collection<PatientFileIN> getAllPatientFiles() {
		return new ArrayList<PatientFileIN>(data.getPatientFileManager()
				.getAllPatientFiles());
	}

	// TODO: heeft pfdto nodig?
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
		this.data = null;
		this.doctor = null;
		this.pf = null;
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Doctor;
	}

}
