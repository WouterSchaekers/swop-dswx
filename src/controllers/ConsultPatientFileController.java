package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import users.Doctor;
import users.User;
import be.kuleuven.cs.som.annotate.Basic;
import controllers.interfaces.DoctorIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.PatientIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * A controller that allows you to open patientfiles.
 */
public class ConsultPatientFileController extends NeedsLoginController
{
	private DoctorIN doctor;
	private PatientFile pf;

	/**
	 * Default constructor.
	 */
	public ConsultPatientFileController(LoginController lc)
			throws InvalidHospitalException, InvalidLoginControllerException {
		super(lc);
		doctor = (DoctorIN) lc.getUser();
	}

	/**
	 * Use to list all patient files of patients who have not yet been
	 * discharged.
	 */
	public Collection<PatientFileIN> getAllPatientFiles() {
		return new ArrayList<PatientFileIN>(hospital.getPatientFileManager()
				.getAllPatientFiles());
	}

	/**
	 * Use to open a patient file you may have selected from the
	 * getActivePatientFiles() method.
	 */
	public void openPatientFile(PatientFileIN pfdto) {
		if (pfdto instanceof PatientFile)
			this.pf = (PatientFile) pfdto;
		else
			throw new IllegalArgumentException(pfdto
					+ " is not a valid patient file");
	}

	/**
	 * @return The patient file that is opened with this ConsultPatientFileController.
	 */
	public PatientFileIN getPatientFile() {
		return this.pf;
	}
	
	/** 
	 * @return The patient to whom the patient file in this ConsultPatientFileController belongs to.
	 */
	public PatientIN getPatient() {
		return (PatientIN) (this.pf.getPatient());
	}
	
	/**
	 * @return The doctor to whom this controller belongs to.
	 */
	@Basic
	public UserIN getDocIN() {
		return this.doctor;
	}
	
	/**
	 * Closes the patient file this doctor has opened.
	 */
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
