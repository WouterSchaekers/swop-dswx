package ui.ordermedicaltestchain;

import controllers.MedicalTestController;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;

public class ScheduleXRay extends MedicalTestCommand
{

	public ScheduleXRay(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		@SuppressWarnings("unused")
		MedicalTestController mc = medData.getMedTestController();

		// TODO
		return new SelectUsecase(data);
	}

}
