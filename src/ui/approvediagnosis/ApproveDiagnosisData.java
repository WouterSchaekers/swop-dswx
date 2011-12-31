package ui.approvediagnosis;

import java.util.Collection;
import controllers.ApproveDiagnosisController;
import controllers.EnterDiagnoseController;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;

public class ApproveDiagnosisData
{

	private ApproveDiagnosisController approveDiagnosisController;
	private Collection<DiagnoseIN> d;
	private DiagnoseIN diagnose;
	private EnterDiagnoseController enterDiagController;
	private String diagnoseString;
	private DoctorIN otherDoctor;
	public void setOtherDoctor(DoctorIN doc)
	{
		this.otherDoctor=doc;
	}
	public void add(ApproveDiagnosisController controller) {
		this.approveDiagnosisController = controller;
		
	}

	public ApproveDiagnosisController getController() {
	return approveDiagnosisController;
}

	public void setUnapprovedDiagnoses(Collection<DiagnoseIN> d) {
		this.d=d;
		
	}
	public Collection<DiagnoseIN> getAllTheDiagnosis()
	{
		return d;
	}

	public void setCreatedDiagnose(DiagnoseIN diagnose) {
		this.diagnose=diagnose;
		
	}

	public DiagnoseIN getDiagnose() {
		return diagnose;
	}

	public void setController(EnterDiagnoseController c) {
		this.enterDiagController=c;
		
	}

	public void setDiagnose(String in) {
		this.setDiagnoseString(in);
		
	}

	public EnterDiagnoseController getEnterDiagnoseController() {
		return this.enterDiagController;
		
	}

	public void setDiagnoseString(String diagnoseString) {
		this.diagnoseString = diagnoseString;
	}

	public String getDiagnoseString() {
		return diagnoseString;
	}

	public DoctorIN getOtherDoctor() {
		return otherDoctor;
	}
	public void setDiagnose(DiagnoseIN diagnose2) {
	}

}
