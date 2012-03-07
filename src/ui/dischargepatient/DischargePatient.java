package ui.dischargepatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.DischargePatientController;
import exceptions.DischargePatientException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;

public class DischargePatient extends DischargePatientSuperClass
{

	public DischargePatient(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		DischargePatientController controller = null;
		try {
			try {
				controller = new DischargePatientController(
						data.getDataPasser(), data.getLoginController(),
						data.getPatientFileOpenController());
			} catch (InvalidHospitalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPatientFileOpenController e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do this");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileException e) {
			System.out.println("invalid patienfile");
			return new SelectUsecase(data);
		}
		try {
			controller.dischargePatient(data.getLoginController(),
					data.getPatientFileOpenController(), data.getDataPasser());
		} catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do this");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileException e) {
			System.out.println("invalid patienfile");
			return new SelectUsecase(data);
		} catch (DischargePatientException e) {
			System.out.println("Patient cant be discharged yet ");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileOpenController e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Patient was succesfully discharged !");
		return new SelectUsecase(data);
	}

}
