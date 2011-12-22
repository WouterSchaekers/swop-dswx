package system;

import patient.PatientFileManager;
import controllers.DataPasser;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;
import scheduler.Scheduler;
import ui.UCHandler;
import ui.UserinterfaceData;
import users.UserManager;

public class StJennySystem_hot
{
	public static void main(String[] args) {
	UserManager m = new UserManager();
	try {
		m.createHospitalAdmin("jeffry");
	} catch (UserAlreadyExistsException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidNameException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidTimeSlotException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	UserinterfaceData data = new UserinterfaceData(new DataPasser(m, new PatientFileManager(), new Scheduler()));
	UCHandler handler = new UCHandler(data);
	handler.start();
	}
	
}
