package ui.openpatientfile;

import ui.DataBlob;
import ui.usecase;

public class ConsultPatientFile extends usecase
{

	public ConsultPatientFile(DataBlob data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		ConsutlPatientFileData d= new ConsutlPatientFileData();
		return new ListUndischargedPatients(data,d);
	}

}
