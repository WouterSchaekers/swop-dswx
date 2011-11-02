package ui.ordermedicaltestchain;

import controllers.MedicalTestController;
import scheduler.Appointment;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.usecase;

public class scheduleXRay extends MedicalTestCommand
{

	public scheduleXRay(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public usecase Execute() {
		MedicalTestController mc = medData.getMedTestController();
		
		Appointment app = mc.orderXRay(medData.getBodypart(), medData.getAmount(), medData.getZoomlevel());
		System.out.println("The appointment was made at " + app.toString());
		return new SelectUsecase(data);
	}

}
