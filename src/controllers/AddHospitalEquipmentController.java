package controllers;

import java.util.Collection;
import machine.MachineBuilder;
import machine.MachinePool;
import system.HospitalState;
import users.HospitalAdmin;
import users.User;
import exceptions.ControllerException;
import exceptions.InvalidHospitalStateException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSerialException;

public class AddHospitalEquipmentController extends NeedsLoginController
{
	

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
			HospitalState state) throws InvalidLoginControllerException,
			InvalidHospitalStateException {
		super(state, loginController);

	}

	public Collection<MachineBuilder> getAllMachines() {
		return hospitalState.getMachinePool().getAllBuilders();
	}
	//TODO : shit wut
	public void createMachine(MachineBuilder b, int serial, String location)
			throws InvalidLocationException,
			InvalidSerialException {
		hospitalState.getMachinePool().addMachine(b.build(serial, location));
	}
	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}