package ui.ordermedicaltestchain;

import ui.UserinterfaceData;
import ui.usecase;

public abstract class MedicalTestCommand extends usecase
{

	public MedicalTestCommand(UserinterfaceData data) {
		super(data);
	}

	@Override
	public abstract usecase Execute();

}
