package ui.consultpatientfile;

import ui.Usecase;
import ui.UserinterfaceData;

public class ConsultPatientFile extends ConsultPatientFileSuperclass
{

	public ConsultPatientFile(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		return new ListUndischargedPatients(data, chaindata);
	}

}
