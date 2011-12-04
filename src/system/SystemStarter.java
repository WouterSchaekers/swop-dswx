package system;

import java.util.Date;
import machine.MachinePool;
import controllers.DataPasser;
import exceptions.UserAlreadyExistsException;
import patient.PatientFileManager;
import scheduler.Scheduler;
import ui.UserInterface;
import users.UserManager;

public class SystemStarter
{
//	// things that are needed in the system
//	static PatientFileManager patientfmanager = new PatientFileManager();
//	static UserManager usermanager = new UserManager();
//	static MachinePool machinepool = new MachinePool();
//	static DietersDikkeTettenSchedulerBackup scheduler = new DietersDikkeTettenSchedulerBackup(usermanager, machinepool);
//
//	public static void main(String[] args) throws UserAlreadyExistsException {
//		usermanager.CreateDoctor("jonathan");
//		usermanager.CreateNurse("jenny");
//		//TODO: fix
//		// DataPasser data = new DataPasser(usermanager, patientfmanager,scheduler);
//				DataPasser data = new DataPasser(usermanager, patientfmanager,
//						new Scheduler());
//		UserInterface T = new UserInterface(data);
//		T.start();
//	}
}
