package ui.ordermedicaltestchain;

import controllers.MedicalTestController;
import scheduler.Appointment;
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
		MedicalTestController mc = medData.getMedTestController();

		Appointment app = mc.orderUltraSound(medData.getFocus(),
				medData.getVid(), medData.getImg());
		System.out.println("The appointment was made at " + app.toString());
		return new SelectUsecase(data);
	}

}
