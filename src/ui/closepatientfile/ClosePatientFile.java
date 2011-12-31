package ui.closepatientfile;

import exceptions.InvalidLoginControllerException;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class ClosePatientFile extends Usecase
{

	public ClosePatientFile(UserinterfaceData data) {
		super(data);
	}

	@Override
	public Usecase Execute() {
		try {
			data.getPatientFileOpenController().closePatientFile(data.getLoginController());
			data.setRegpatctrl(null);
		} catch (InvalidLoginControllerException e) {
			System.out.println("not allowed to do this");
			return new SelectUsecase(data);
		}
		return new SelectUsecase(data);
	}
	
	
}
