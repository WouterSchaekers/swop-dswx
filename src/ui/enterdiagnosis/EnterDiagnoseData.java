package ui.enterdiagnosis;

import controllers.EnterDiagnoseController;

public class EnterDiagnoseData
{

	public String getDiag() {
		return diag;
	}

	public void setDiag(String diag) {
		this.diag = diag;
	}

	public EnterDiagnoseController getController() {
		return c;
	}

	public void setC(EnterDiagnoseController c) {
		this.c = c;
	}

	private String diag;
	private EnterDiagnoseController c;

	public void setController(EnterDiagnoseController c) {
		this.c = c;

	}

	public void setDiagnose(String in) {
		this.diag = in;

	}

}
