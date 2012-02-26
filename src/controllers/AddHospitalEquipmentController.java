package controllers;

import java.util.Collection;
import system.HospitalState;
import users.HospitalAdmin;
import users.User;
import machine.MachineBuilder;
import machine.MachinePool;
import exceptions.ControllerException;
import exceptions.InvalidHospitalStateException;
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
	 * @throws InvalidHospitalStateException 
	 */
	public AddHospitalEquipmentController(LoginController loginController,
			HospitalState state) throws InvalidLoginControllerException, InvalidHospitalStateException {
		super(state, loginController);
		this.machinePool = state.getMachinePool();

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
