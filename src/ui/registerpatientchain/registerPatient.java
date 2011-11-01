package ui.registerpatientchain;

import ui.UserinterfaceData;
import ui.usecase;

/**
 * This class registers a patient for the use case and proceeds to the next step in the chain.
 *
 */
public class registerPatient extends usecase
{

	public registerPatient(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns a new CreateRegisterController, goes to the next step in the chain.
	 */
	@Override
	public usecase Execute() {
		// TODO Auto-generated method stub
		return new CreateRegisterController(data);
	}

}
