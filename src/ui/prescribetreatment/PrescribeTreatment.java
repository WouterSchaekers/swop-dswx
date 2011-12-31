package ui.prescribetreatment;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class PrescribeTreatment extends PrescribeTreatmentSuper
{

	public PrescribeTreatment(UserinterfaceData data) {	
		super(data);
	}

	@Override
	public Usecase Execute() {
		//PrescribeTreatmentController controller ;
		
//		try {
//			controller= new PrescribeTreatmentController(data.getLoginController(), data.getPatientFileOpenController());
//		} catch (InvalidLoginControllerException e) {
//			System.out.println("invalid login");
//			return new SelectUsecase(data);
//		} catch (InvalidPatientFileException e) {
//			System.out.println("invalid patient");
//			return new SelectUsecase(data);
//		}
//		//YEEY we can do things now
		//chaindata.setPrescribeTreatmentController(controller);
		return new SelectUsecase(data) ;
	}

}
