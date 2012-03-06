package ui.ordermedicaltest;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.OrderMedicalTestController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

public class OrderMedicalTest extends OrderMedicalTestSuperClass
{

	public OrderMedicalTest(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		OrderMedicalTestController orderMedTestController = null;
		try {
			orderMedTestController = new OrderMedicalTestController(
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
		} catch (InvalidHospitalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPatientFileOpenController e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chaindata.setMedTestController(orderMedTestController);
		return new SelectMedicalTest(data, chaindata);
	}
}
