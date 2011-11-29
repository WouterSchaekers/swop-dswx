package ui.openpatientfile;

import controllers.PatientFileOpenController;

class ConsutlPatientFileData
{
	PatientFileOpenController pfoc;

	public void setPatientFileOpenController(
			PatientFileOpenController patientFileOpenController) {
		this.pfoc = patientFileOpenController;
	}

	public PatientFileOpenController getPatientfileOpenController() {
		return pfoc;
	}

}
