package ui.openpatientfile;

import controllers.PatientFileOpenController;
import ui.DataBlob;
import ui.SelectUsecase;
import ui.usecase;
import users.User.usertype;

public class ListUndischargedPatients extends usecase
{
	DataBlob data;
	ConsutlPatientFileData patfiledata;
	public ListUndischargedPatients(DataBlob data, ConsutlPatientFileData d) {
		super(data);
	}

	@Override
	public usecase Execute() {
		if(data.getLoginController().getUserDTO().type()!=usertype.Doctor)
		{
			System.out.println(data.getLoginController().getUserDTO().getName()+" is not a Doctor");
			return new SelectUsecase(data);
		
		}
		patfiledata.setPatientFileOpenController(new PatientFileOpenController(data.getLoginController()));
		
		patfiledata.getPatientfileOpenController();
		
		return null;
	}

}
