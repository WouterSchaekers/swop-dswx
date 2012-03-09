package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import system.Hospital;
import users.Nurse;
import users.User;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;

public class RegisterPatientController extends NeedsLoginController
{
	public RegisterPatientController(LoginController lc) throws InvalidLoginControllerException,
			InvalidHospitalException {
		super(lc);
	}

	public Collection<PatientFileIN> getAllPatients() {
		return new ArrayList<PatientFileIN>(hospital.getPatientFileManager()
				.getAllPatientFiles());
	}

	public void checkInPatient(PatientFileIN patientFile) {
		if (!(patientFile instanceof PatientFile))
			throw new IllegalArgumentException(patientFile
					+ " is not a valid patientfile");
		this.hospital.getPatientFileManager()
				.checkIn((PatientFile) patientFile);
	}

	public void registerNewPatient(Hospital hospital, String name)
			throws InvalidNameException {
		hospital.getPatientFileManager().registerPatient(name);
	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}
}