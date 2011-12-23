package system;

import patient.PatientFileManager;
import scheduler.Scheduler;
import ui.UCHandler;
import ui.UserinterfaceData;
import users.UserManager;
import controllers.DataPasser;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class StJennySystem_hot
{
	private static StJennySystem_hot instance;
	private final HospitalState state;
	public static StJennySystem_hot instance()
	{
		if(instance==null)
			instance = new StJennySystem_hot();
		return instance;
	}
	private StJennySystem_hot()
	{
		this.state=new HospitalState();
		try {
			state.userManager.createHospitalAdmin("Wouter");
		} catch (UserAlreadyExistsException e) {
			System.out.println("Fatal error at system startup, wouter already exists");
		} catch (InvalidNameException e) {
			System.out.println("Fatal error at system startup, wouter is ");
		} catch (InvalidTimeSlotException e) {
			// seriously we didnt fuck up this hard :p
		}
	}
	
	public static void main(String[] args) {
	StJennySystem_hot t = StJennySystem_hot.instance();
	UserinterfaceData data = new UserinterfaceData(new DataPasser(t.state.userManager,t.state.patientFileManager, t.state.scheduler));
	UCHandler handler = new UCHandler(data);
	handler.start();
	}
	
}
