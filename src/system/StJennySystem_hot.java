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
	private static boolean extendd;
	 final HospitalState state;
	public static StJennySystem_hot instance() throws InvalidTimeLordException
	{
		if(instance==null)
			try {
				instance = new StJennySystem_hot();
			} catch (InvalidHospitalDateException e) {
			}
		return instance;
	}
	private StJennySystem_hot() throws InvalidTimeLordException, InvalidHospitalDateException
	{
		this.state=new HospitalState();
		try {
			state.userManager.createHospitalAdmin("Wouter");
			if(extendd)
				extraInfo();
		} catch (UserAlreadyExistsException e) {
			System.out.println("Fatal error at system startup, Wouter already exists.");
		} catch (InvalidNameException e) {
			System.out.println("Fatal error at system startup, Wouter is cool.");
		} catch (InvalidTimeSlotException e) {
			System.out.println("Fatal error at system startup.");
		} catch (InvalidSerialException e) {
		} catch (InvalidLocationException e) {
		}
	}
	private void extraInfo() throws UserAlreadyExistsException,
			InvalidNameException, InvalidTimeSlotException,
			InvalidSerialException, InvalidLocationException {
		state.userManager.createNurse("jenny");
		state.userManager.createDoctor("stef");
		state.userManager.createDoctor("abra");
		state.machinePool.addMachine(state.machinePool.createUltraSoundScanner(234, "jonathan"));
		state.patientFileManager.registerPatient("jos");
	}
	
	public static void main(String[] args) throws InvalidTimeLordException {
	if(args.length!=0)
		if(args[0].equals("-e"))
			StJennySystem_hot.extendd=true;
	StJennySystem_hot t = StJennySystem_hot.instance();
	UserinterfaceData data = new UserinterfaceData(new DataPasser(t.state.userManager,t.state.patientFileManager, t.state.scheduler,t.state.machinePool, t.state.taskManager,t.state.systemTime,t.state.warehouse));
	UCHandler handler = new UCHandler(data);
		handler.start();
	
	}
	
}
