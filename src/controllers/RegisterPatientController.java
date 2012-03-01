package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import system.HospitalState;
import users.Nurse;
import users.User;
import controllers.interfaces.AppointmentIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;

//TODO: check usecase register patient
public class RegisterPatientController extends NeedsLoginController
{

	public RegisterPatientController(LoginController loginController,
			HospitalState state) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(state, loginController);
	}

	public Collection<PatientFileIN> getAllPatients(LoginController loginc)
			throws InvalidLoginControllerException {
		return new ArrayList<PatientFileIN>(hospitalState
				.getPatientFileManager().getAllPatientFiles());
	}

	public void registerPatient(PatientFileIN file) {
		PatientFile f;
		if (file instanceof PatientFile)
			f = (PatientFile) file;
		else
			throw new IllegalArgumentException(file
					+ " is not a valid patientfile");

		this.hospitalState.getPatientFileManager().checkIn(f);
	}

	public AppointmentIN CreateAppointMent(UserIN user, HospitalState state)
			throws InvalidLoginControllerException {
		// TODO fix this shit ffs
		// return new Appointment(hospitalState.getTaskManager().addTask(new
		// UnscheduledAppointment((PatientFile)pfile.getPatientFile(),loginControler.getu,dataPasser.getTimeLord().getSystemTime())));
		return null;
	}

	public void createNewPatient(HospitalState dataPasser2, String name)
			throws InvalidNameException {
		dataPasser2.getPatientFileManager().registerPatient(name);

	}

	@Override
	boolean validUser(User u) {
		return u instanceof Nurse;
	}

}
