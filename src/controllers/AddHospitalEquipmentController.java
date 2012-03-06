package controllers;

import java.util.Collection;
import machine.MachineBuilder;
import system.HospitalState;
import users.HospitalAdmin;
import users.User;
import exceptions.*;

/**
 * Allows you to add new equipment to the hospital.
 */
public class AddHospitalEquipmentController extends NeedsLoginController
{

	/**
	 * Default constructor to add hospital equipment to the hospital.
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

	/**
	 * @return All the machines currently in the hospital.
	 */
	public Collection<MachineBuilder> getAllMachines() {
		return hospitalState.getMachinePool().getAllBuilders();
	}

	
	/**
	 * Creates a new machine from the given machine builder and adds it to the
	 * hospital.
	 */
	// TODO : Fix these parameters
	public void createMachine(MachineBuilder b, int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		hospitalState.getMachinePool().addMachine(b.build(serial, location));
	}

	@Override
	boolean validUser(User u) {
		return u instanceof HospitalAdmin;
	}
}