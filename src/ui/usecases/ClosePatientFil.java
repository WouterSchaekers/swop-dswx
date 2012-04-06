package ui.usecases;

import ui.UIData;
import ui.UseCase;

public class ClosePatientFil extends UseCase
{

	public ClosePatientFil(UIData data) throws Exception {
		super(data, 45);
		if(data.getConsultPatientFileopenController()==null)
			throw new Exception();
	}

	@Override
	public UseCase execute() {
		printLn("Patientfile for:"+data.getConsultPatientFileopenController().getPatientFile().getPatientIN().getName()+" succesfully closed");
		data.getConsultPatientFileopenController().closePatientFile();
		data.setConsultPatientFileopenController(null);
		return mm();
	}

}
