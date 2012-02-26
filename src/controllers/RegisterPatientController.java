package controllers;

import java.util.ArrayList;
import java.util.Collection;
import patient.PatientFile;
import scheduler.task.scheduled.Appointment;
import scheduler.task.unscheduled.UnscheduledAppointment;
import system.HospitalState;
import users.Doctor;
import users.Nurse;
import users.User;
import controllers.interfaces.AppointmentIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidRequirementException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;
import exceptions.QueueException;


//TODO: check usecase register patient
public class RegisterPatientController extends NeedsLoginController
{

	public RegisterPatientController(LoginController loginController,
			HospitalState state) throws InvalidLoginControllerException, InvalidHospitalStateException {
		super(state,loginController);
	}

	public Collection<PatientFileIN> getAllPatients(LoginController loginc) throws InvalidLoginControllerException {
		checkValidity(loginc);
		Collection<PatientFileIN> RV = new ArrayList<PatientFileIN>();
		for (PatientFile file : hospitalState.getPatientFileManager()
				.getAllPatientFiles())
			RV.add(file);
		return RV;
	}

	public void registerPatient(PatientFileIN file) {
		PatientFile f;
		if(file instanceof PatientFile)
			f=(PatientFile)file;
		else
			throw new IllegalArgumentException(file + " is not a valid patientfile");
		
		this.hospitalState.getPatientFileManager().checkIn(f);
	}

	public AppointmentIN CreateAppointMent(UserIN user,
			PatientFileOpenController  pfile, HospitalState state,LoginController loginc) throws InvalidLoginControllerException{
	checkValidity(loginc);
	//TODO fix this shit ffs
	//	return new Appointment(hospitalState.getTaskManager().addTask(new UnscheduledAppointment((PatientFile)pfile.getPatientFile(),loginControler.getu,dataPasser.getTimeLord().getSystemTime())));
		return null;
	}

	public void createNewPatient(HospitalState dataPasser2, String name) throws InvalidNameException {
		dataPasser2.getPatientFileManager().registerPatient(name);

	}

	@Override
	boolean validUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

}
