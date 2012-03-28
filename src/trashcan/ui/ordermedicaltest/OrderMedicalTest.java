package ui.ordermedicaltest;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.OrderMedicalTestController;

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
					data.getPatientFileOpenController());
			chaindata.setMedTestController(orderMedTestController);
			return new SelectMedicalTest(data, chaindata);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}
}
