package ui.ordermedicaltestchain;

import controllers.MedicalTestController;
import scheduler.Appointment;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.usecase;

public class scheduleBloodAnalysis extends MedicalTestCommand
{

	public scheduleBloodAnalysis(UserinterfaceData uiData,
			MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public usecase Execute() {
		MedicalTestController mc = medData.getMedTestController();
		
		Appointment app = mc.orderBloodAnalysis(medData.getFocus(), medData.getAmount());
		System.out.println("The appointment was made at " + app.toString());
		return new SelectUsecase(data);
	}

}
