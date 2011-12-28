package ui.closepatientfile;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class ClosePatientFile extends Usecase
{

	public ClosePatientFile(UserinterfaceData data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		data.getPatientFileOpenController().closePatientFile();
		return new SelectUsecase(data);
	}
	
}
