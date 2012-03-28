package ui.consultpatientfile;

import ui.Usecase;
import ui.UserinterfaceData;

abstract class ConsultPatientFileSuperclass extends Usecase
{

	protected ConsutlPatientFileData chaindata;

	public ConsultPatientFileSuperclass(UserinterfaceData data) {
		this(data, new ConsutlPatientFileData());
	}

	public ConsultPatientFileSuperclass(UserinterfaceData data,
			ConsutlPatientFileData chaindata) {
		super(data);
		this.chaindata = chaindata;
	}
}
