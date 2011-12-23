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
			e.printStackTrace();
		} catch (InvalidTimeSlotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	UserManager m = new UserManager();
	PatientFileManager pf= new PatientFileManager();
	Scheduler s = new Scheduler();
	try {
		m.createHospitalAdmin("jeffry");
		m.createNurse("jenny");
		m.createDoctor("vinc");
		pf.registerPatient("simon");
		pf.registerPatient("jasper");
		
	} catch (Exception e) {}
	UserinterfaceData data = new UserinterfaceData(new DataPasser(m,pf, s));
	UCHandler handler = new UCHandler(data);
	handler.start();
	}
	
}
