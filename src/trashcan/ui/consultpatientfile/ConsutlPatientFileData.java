package ui.consultpatientfile;

import controllers.ConsultPatientFileController;

class ConsutlPatientFileData
{
	ConsultPatientFileController pfoc;

	public void setPatientFileOpenController(
			ConsultPatientFileController patientFileOpenController) {
		this.pfoc = patientFileOpenController;
	}

	public ConsultPatientFileController getPatientfileOpenController() {
		return pfoc;
	}

}
