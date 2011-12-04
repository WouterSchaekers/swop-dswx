package ui.ordermedicaltestchain;

import controllers.MedicalTestController;
import scheduler.Appointment;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;

public class ScheduleBloodAnalysis extends MedicalTestCommand
{

	public ScheduleBloodAnalysis(UserinterfaceData uiData,
			MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		@SuppressWarnings("unused")
		MedicalTestController mc = medData.getMedTestController();
		
		//TODO
		//Appointment app = mc.orderBloodAnalysis(medData.getFocus(),medData.getAmount());
		Appointment app = null;
		System.out.println("The appointment was made at " + app.toString());
		return new SelectUsecase(data);
	}

}
