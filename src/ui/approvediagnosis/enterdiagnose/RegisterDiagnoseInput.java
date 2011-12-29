
package ui.approvediagnosis.enterdiagnose;
import controllers.interfaces.DiagnoseIN;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import ui.approvediagnosis.ApproveDiagnosisData;
import ui.approvediagnosis.ApproveDiagnosisSuper;
import ui.approvediagnosis.ShowResultingTreatment;

public class RegisterDiagnoseInput extends ApproveDiagnosisSuper
{

	public RegisterDiagnoseInput(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data,chaindata);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		DiagnoseIN diagnose=null;
		try {
	diagnose=	this.chaindata.getEnterDiagnoseController().enterDiagnose(data.getLoginController(), data.getPatientFileOpenController(), chaindata.getDiagnoseString(), chaindata.getOtherDoctor());
		} catch (InvalidLoginControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPatientFileOpenController e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDiagnoseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDoctorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chaindata.setDiagnose(diagnose);
		return new ShowResultingTreatment(data,chaindata);
		
	}

}
