package ui.registerpatient;

import ui.Usecase;
import ui.UserinterfaceData;

/**
 * This class registers a patient for the use case and proceeds to the next step
 * in the chain.
 * 
 */
public class RegisterPatient extends Usecase
{

	public RegisterPatient(UserinterfaceData data) {
		super(data);
	}

	/**
	 * Returns a new CreateRegisterController, goes to the next step in the
	 * chain.
	 */
	@Override
	public Usecase Execute() {
		return new CreateRegisterController(data);
	}

}
