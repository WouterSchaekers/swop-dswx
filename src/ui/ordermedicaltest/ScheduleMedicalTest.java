package ui.ordermedicaltest;

import medicaltest.MedicalTest;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class ScheduleMedicalTest extends OrderMedicalTestSuperClass
{

	public ScheduleMedicalTest(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		try {
			MedicalTest t = null;
			t = chaindata.getFactory().create();
			chaindata.getMedTestController().addMedicaltest(t);
			System.out.println("Your medicaltest has now been scheduled !");
			//System.out.println(t.appointmentInfo());
			return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
