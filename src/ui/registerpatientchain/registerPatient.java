package ui.registerpatientchain;

import ui.UserinterfaceData;
import ui.usecase;

public class registerPatient extends usecase
{

	public registerPatient(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		// TODO Auto-generated method stub
		return new CreateRegisterController(data);
	}

}
