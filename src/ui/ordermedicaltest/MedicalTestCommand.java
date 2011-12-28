package ui.ordermedicaltest;

import ui.UserinterfaceData;
import ui.Usecase;

public abstract class MedicalTestCommand extends Usecase
{
	MedicalTestData medData;

	public MedicalTestCommand(UserinterfaceData data) {
		super(data);
	}

	@Override
	public abstract Usecase Execute();

	public MedicalTestCommand(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData);
		this.medData = medData;
	}

}
