package ui.ordermedicaltestchain;

import ui.UserinterfaceData;
import ui.usecase;
import ui.loginchain.LoginData;

public abstract class MedicalTestCommand extends usecase
{
	MedicalTestData medData;

	public MedicalTestCommand(UserinterfaceData data) {
		super(data);
	}

	@Override
	public abstract usecase Execute();
	
	public MedicalTestCommand(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData);
		this.medData= medData;
	}

}
