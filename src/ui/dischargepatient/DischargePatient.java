package ui.dischargepatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.DischargePatientController;

public class DischargePatient extends DischargePatientSuperClass
{

	public DischargePatient(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		try {
			DischargePatientController controller = null;

			controller = new DischargePatientController(
					data.getLoginController(),
					data.getPatientFileOpenController());

			controller.dischargePatient();
			System.out.println("Patient was succesfully discharged !");
			return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}

	}
}
