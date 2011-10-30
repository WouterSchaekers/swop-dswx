package system;

import controllers.DataPasser;
import patient.PatientFileManager;
import ui.UserInterface;
import users.UserAlreadyExistsException;
import users.UserManager;

public class SystemStarter
{
	// things that are needed in the system
	static PatientFileManager patientfmanager = new PatientFileManager();
	static UserManager usermanager = new UserManager();

	public static void main(String[] args) throws UserAlreadyExistsException {
		usermanager.CreateDoctor("jonathan");
		DataPasser data = new DataPasser(usermanager, patientfmanager);
		UserInterface T = new UserInterface(data);
		T.start();
	}
}
