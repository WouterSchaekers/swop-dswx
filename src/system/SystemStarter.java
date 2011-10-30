package system;

import controllers.DataPasser;
import patient.PatientFileManager;
import scheduler.Scheduler;
import ui.UserInterface;
import users.UserAlreadyExistsException;
import users.UserManager;

public class SystemStarter
{
	// things that are needed in the system
	static PatientFileManager patientfmanager = new PatientFileManager();
	static UserManager usermanager = new UserManager();
	static Scheduler scheduler = new Scheduler();
	public static void main(String[] args) throws UserAlreadyExistsException {
		usermanager.CreateDoctor("jonathan");
		usermanager.CreateNurse("jenny");
		DataPasser data = new DataPasser(usermanager, patientfmanager, scheduler);
		UserInterface T = new UserInterface(data);
		T.start();
	}
}
