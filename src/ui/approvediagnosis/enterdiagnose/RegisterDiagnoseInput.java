
package ui.approvediagnosis.enterdiagnose;
import ui.Usecase;
import ui.UserinterfaceData;
import ui.approvediagnosis.ApproveDiagnosisData;
import ui.approvediagnosis.ApproveDiagnosisSuper;
import ui.approvediagnosis.ShowResultingTreatment;
import controllers.interfaces.DiagnoseIN;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidDoctorException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileOpenController;

public class RegisterDiagnoseInput extends ApproveDiagnosisSuper
{

	public RegisterDiagnoseInput(UserinterfaceData data,
			ApproveDiagnosisData chaindata) {
		super(data,chaindata);
	}

	@Override
	public Usecase Execute() {
		DiagnoseIN diagnose=null;
		try {
	diagnose=	this.chaindata.getEnterDiagnoseController().enterDiagnose(data.getLoginController(), data.getPatientFileOpenController(), chaindata.getDiagnoseString(), chaindata.getOtherDoctor(),data.getDataPasser());
		} catch (InvalidLoginControllerException e) {
			e.printStackTrace();
		} catch (InvalidPatientFileOpenController e) {
			e.printStackTrace();
		} catch (InvalidDiagnoseException e) {
			e.printStackTrace();
		} catch (InvalidDoctorException e) {
			e.printStackTrace();
		}
		chaindata.setDiagnose(diagnose);
		return new ShowResultingTreatment(data,chaindata);
		
	}

}
