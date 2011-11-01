package system;

import controllers.DataPasser;
import patient.PatientFileManager;
import resources.MachinePool;
import scheduler.Scheduler;
import ui.UserInterface;
import users.UserAlreadyExistsException;
import users.UserManager;

public class SystemStarter
{
	// things that are needed in the system
	static PatientFileManager patientfmanager = new PatientFileManager();
	static UserManager usermanager = new UserManager();
	static MachinePool machinepool= new MachinePool();
	static Scheduler scheduler = new Scheduler(usermanager, machinepool);
	public static void main(String[] args) throws UserAlreadyExistsException {
		usermanager.CreateDoctor("jonathan");
		usermanager.CreateNurse("jenny");
		DataPasser data = new DataPasser(usermanager, patientfmanager, scheduler);
		UserInterface T = new UserInterface(data);
		T.start();
	}
}
