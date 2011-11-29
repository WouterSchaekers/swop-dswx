package ui.openpatientfile;

import ui.UserinterfaceData;
import ui.Usecase;

public class ConsultPatientFile extends Usecase
{

	public ConsultPatientFile(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		ConsutlPatientFileData d = new ConsutlPatientFileData();
		return new ListUndischargedPatients(data, d);
	}

}
