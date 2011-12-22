package controllers;

import java.util.ArrayList;
import java.util.Collection;
import controllers.interfaces.AppointmentIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidNameException;
import patient.PatientFile;
import scheduler.Scheduler;
import users.Nurse;
import users.User;
import users.Doctor;

public class RegisterPatientController
{
	PatientFile openPatientFile;
	DataPasser dataPasser;

	public RegisterPatientController(LoginController loginController,
			DataPasser dataPasser) {
		if (!(loginController.getUser() instanceof Nurse)) {
			throw new IllegalArgumentException(loginController.getUser()
					.getName() + " is not a Nurse.");
		}
		this.dataPasser = dataPasser;
	}

	public Collection<PatientFileIN> getAllPatients() {
		Collection<PatientFileIN> RV = new ArrayList<PatientFileIN>();
		for (PatientFile file : dataPasser.getPatientFileManager()
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
		
		this.dataPasser.getPatientFileManager().checkIn(f);
		this.openPatientFile = f;
	}

	public AppointmentIN CreateAppointMent(UserIN user,
			PatientFileIN pfile) {
		User u;
		PatientFile f;
		if(user instanceof User)
			u=(User)user;
		else
			throw new IllegalArgumentException();
		if(pfile instanceof PatientFile)
			f=(PatientFile)pfile;
		else
			throw new IllegalArgumentException();
		
			
		if (u == null || f == null)
			throw new IllegalArgumentException("null objects are illegal");
		if (!(u instanceof Doctor))
			throw new IllegalArgumentException(u.getName()
					+ "is not a doctor");
		if (this.openPatientFile == null)
			throw new IllegalStateException(
					"No patientfile has been opened yet");
		if (f.isDischarged())
			throw new IllegalArgumentException(f.getName()
					+ " is not checked in");
		return null;//  new Scheduler().schedule(duration, startDate, neededSchedulables, occurences);
	}

	public void createNewPatient(DataPasser dataPasser2, String name) throws InvalidNameException {
		dataPasser2.getPatientFileManager().registerPatient(name);

	}

}
