package ui.closepatientfile;

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
			data.getPatientFileOpenController().closePatientFile();
			data.setRegpatctrl(null);
			return new SelectUsecase(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
