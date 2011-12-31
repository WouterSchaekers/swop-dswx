package system;

import ui.UCHandler;
import ui.UserinterfaceData;
import controllers.DataPasser;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidNameException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeLordException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;

public class StJennySystem_hot
{
	 static StJennySystem_hot instance;
	 final HospitalState state;
	public static StJennySystem_hot instance() throws InvalidTimeLordException
	{
		if(instance==null)
			try {
				instance = new StJennySystem_hot();
			} catch (InvalidHospitalDateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return instance;
	}
	private StJennySystem_hot() throws InvalidTimeLordException, InvalidHospitalDateException
	{
		this.state=new HospitalState();
		try {
			state.userManager.createHospitalAdmin("Wouter");
			state.userManager.createNurse("jenny");
			state.userManager.createDoctor("stef");
	state.userManager.createDoctor("abra");
	state.machinePool.addMachine(state.machinePool.createUltraSoundScanner(234, "jonathan"));
			state.patientFileManager.registerPatient("jos");
		} catch (UserAlreadyExistsException e) {
			System.out.println("Fatal error at system startup, Wouter already exists.");
		} catch (InvalidNameException e) {
			System.out.println("Fatal error at system startup, Wouter is cool.");
		} catch (InvalidTimeSlotException e) {
			System.out.println("Fatal error at system startup.");
		} catch (InvalidSerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InvalidTimeLordException {
	StJennySystem_hot t = StJennySystem_hot.instance();
	UserinterfaceData data = new UserinterfaceData(new DataPasser(t.state.userManager,t.state.patientFileManager, t.state.scheduler,t.state.machinePool, t.state.taskManager,t.state.systemTime,t.state.warehouse));
	UCHandler handler = new UCHandler(data);

		handler.start();
	
	}
	
}
