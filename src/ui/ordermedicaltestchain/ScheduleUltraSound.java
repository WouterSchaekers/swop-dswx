package ui.ordermedicaltestchain;

import controllers.MedicalTestController;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;

public class ScheduleUltraSound extends MedicalTestCommand
{
	public ScheduleUltraSound(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		@SuppressWarnings("unused")
		MedicalTestController mc = medData.getMedTestController();

		//XXX
		return new SelectUsecase(data);
	}

}
