package ui.consultpatientfile;

import ui.UserinterfaceData;
import ui.Usecase;

public class ConsultPatientFile extends ConsultPatientFileSuperclass
{

	public ConsultPatientFile(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		return new ListUndischargedPatients(data,chaindata);
	}

}
