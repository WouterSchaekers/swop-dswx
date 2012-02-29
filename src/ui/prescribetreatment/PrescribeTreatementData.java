package ui.prescribetreatment;

import controllers.PrescribeTreatmentController;
import controllers.interfaces.DiagnoseIN;

public class PrescribeTreatementData
{
	PrescribeTreatmentController c;
	DiagnoseIN DiagnoseToTreat;

	public DiagnoseIN getDiagnoseToTreat() {
		return DiagnoseToTreat;
	}

	public void setDiagnoseToTreat(DiagnoseIN diagnoseIN) {
		this.DiagnoseToTreat = diagnoseIN;
	}

	public PrescribeTreatmentController getPrescribeTreatmentController() {
		return c;
	}

	public void setPrescribeTreatmentController(PrescribeTreatmentController c) {
		this.c = c;
	}

}
