package ui.ordermedicaltest;

import controllers.MedicalTestController;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;

public class OrderMedicalTest extends OrderMedicalTestSuperClass
{

	public OrderMedicalTest(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		MedicalTestController orderMedTestController ;
		try {
			orderMedTestController= new MedicalTestController(
					data.getLoginController(),
					data.getPatientFileOpenController(), data.getDataPasser());
		} catch (IllegalArgumentException e) {
			System.out.println("");
			return new SelectUsecase(data);
		} catch (InvalidLoginControllerException e) {
			System.out.println("Not allowed to do this !");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileException e) {
			System.out.println("No patientfile open.");
			return new SelectUsecase(data);
		}
		chaindata.setMedTestController(orderMedTestController);
		return new SelectMedicalTest(data,chaindata);
	}
}
