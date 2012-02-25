package controllers;

import java.util.Collection;
import controllers.interfaces.HospitalStateI;
import system.HospitalState;
import users.HospitalAdmin;
import users.User;
import machine.MachineBuilder;
import machine.MachinePool;
import exceptions.ControllerException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSerialException;

public class AddHospitalEquipmentController extends NeedsLoginController
{
	MachinePool machinePool;

	/**
	 * Default constructor to add hospital equipment to the hospital system All
	 * calls on this controller have to happen with the same loginController
	 * 
	 * @param loginController
	 * @throws ControllerException
	 * @throws InvalidLoginControllerException
	 */
	public AddHospitalEquipmentController(LoginController loginController,
			HospitalStateI state) throws InvalidLoginControllerException {
		super(loginController);
		this.machinePool = ((HospitalState) state).machinePool;

	}

	public AddHospitalEquipmentController(LoginController loginController,
			DataPasser dataPasser) throws InvalidLoginControllerException {
		super(loginController);
		this.machinePool = dataPasser.getMachinePool();
	}

	public Collection<MachineBuilder> getAllMachines(
			LoginController loginController)
			throws InvalidLoginControllerException {
		if (!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		return machinePool.getAllBuilders();
	}

	public void createMachine(MachineBuilder b, int serial, String location,
			LoginController loginController)
			throws InvalidLoginControllerException, InvalidLocationException,
			InvalidSerialException {
		//TODO: remove invalid timeslot exception
		if (!isValidLoginController(loginController))
			throw new InvalidLoginControllerException("");
		machinePool.addMachine(b.build(serial, location));

	}
	
	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}

}
