package ui.ordermedicaltest;

import ui.SelectUsecase;
import ui.UserinterfaceData;
import ui.Usecase;
import users.Doctor;

public class OrderMedicalTest extends MedicalTestCommand
{

	public OrderMedicalTest(UserinterfaceData data) {
		super(data);
		this.medData = new MedicalTestData();
	}

	@Override
	public Usecase Execute() {
		// check if the person initiating this chain is allowed to.
		if (data.getLoginController().getUserIN() instanceof Doctor) {
			return new PatientFileOpenChecker(data, medData);
		} else {
			System.out.println(data.getLoginController().getUserIN().getName()
					+ " is not a doctor in this hospital");
			return new SelectUsecase(data);
		}
	}

}
