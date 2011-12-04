package ui.ordermedicaltestchain;

import controllers.MedicalTestController;
import scheduler.Appointment;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;

public class ScheduleXRay extends MedicalTestCommand
{

	public ScheduleXRay(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@SuppressWarnings("null")
	@Override
	public Usecase Execute() {
		@SuppressWarnings("unused")
		MedicalTestController mc = medData.getMedTestController();

		// TODO
		//Appointment app = mc.orderXRay(medData.getBodypart(),medData.getAmount(), medData.getZoomlevel());
		Appointment app = null;
		System.out.println("The appointment was made at " + app.toString());
		return new SelectUsecase(data);
	}

}
