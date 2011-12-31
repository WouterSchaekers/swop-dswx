package ui.dischargepatient;

import controllers.DischargePatientController;
import exceptions.DischargePatienException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class DischargePatient extends DischargePatientSuperClass
{

	public DischargePatient(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		DischargePatientController controller = null;
		try {
			controller	 = new DischargePatientController(data.getLoginController(), data.getPatientFileOpenController());
		} catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do this");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileException e) {
			System.out.println("invalid patienfile");
			return new SelectUsecase(data);
		}
		try {
			controller.dischargePatient(data.getLoginController(), data.getPatientFileOpenController(), data.getDataPasser());
		}  catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do this");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileException e) {
			System.out.println("invalid patienfile");
			return new SelectUsecase(data);
		} catch (DischargePatienException e) {
			System.out.println("Patient cant be discharged yet ");
			return new SelectUsecase(data);
		}
		System.out.println("Patient was succesfully discharged !");
		return new SelectUsecase(data);
	}

}
