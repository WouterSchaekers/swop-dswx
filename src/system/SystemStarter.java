package system;

import java.util.Date;
import machine.MachinePool;
import controllers.DataPasser;
import patient.PatientFileManager;
import scheduler.Scheduler;
import scheduler.SchedulerBackup;
import ui.UserInterface;
import users.UserAlreadyExistsException;
import users.UserManager;

public class SystemStarter
{
	// things that are needed in the system
	static PatientFileManager patientfmanager = new PatientFileManager();
	static UserManager usermanager = new UserManager();
	static MachinePool machinepool = new MachinePool();
	static SchedulerBackup scheduler = new SchedulerBackup(usermanager, machinepool);

	public static void main(String[] args) throws UserAlreadyExistsException {
		usermanager.CreateDoctor("jonathan");
		usermanager.CreateNurse("jenny");
		//TODO: fix
		// DataPasser data = new DataPasser(usermanager, patientfmanager,scheduler);
				DataPasser data = new DataPasser(usermanager, patientfmanager,
						new Scheduler());
		UserInterface T = new UserInterface(data);
		T.start();
	}
}
