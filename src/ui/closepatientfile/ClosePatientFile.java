package ui.closepatientfile;

import exceptions.InvalidLoginControllerException;
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
		try {
			data.getPatientFileOpenController().closePatientFile(data.getLoginController());
			data.setRegpatctrl(null);
		} catch (InvalidLoginControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SelectUsecase(data);
	}
	
	
}
