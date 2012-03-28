package ui.approvediagnosis;

import java.util.Collection;
import controllers.ApproveDiagnoseController;
import controllers.EnterDiagnoseController;
import controllers.interfaces.DiagnoseIN;
import controllers.interfaces.DoctorIN;

public class ApproveDiagnosisData
{

	private ApproveDiagnoseController approveDiagnosisController;
	private Collection<DiagnoseIN> d;
	private DiagnoseIN diagnose;
	private EnterDiagnoseController enterDiagController;
	private String diagnoseString;
	private DoctorIN otherDoctor;

	public void setOtherDoctor(DoctorIN doc) {
		this.otherDoctor = doc;
	}

	public void add(ApproveDiagnoseController controller) {
		this.approveDiagnosisController = controller;

	}

	public ApproveDiagnoseController getController() {
		return approveDiagnosisController;
	}

	public void setUnapprovedDiagnoses(Collection<DiagnoseIN> d) {
		this.d = d;

	}

	public Collection<DiagnoseIN> getAllTheDiagnosis() {
		return d;
	}

	public void setCreatedDiagnose(DiagnoseIN diagnose) {
		this.diagnose = diagnose;

	}

	public DiagnoseIN getDiagnose() {
		return diagnose;
	}

	public void setController(EnterDiagnoseController c) {
		this.enterDiagController = c;

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
