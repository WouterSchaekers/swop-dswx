package ui.registerpatientchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.Usecase;
import controllers.DTOAppointment;

public class DisplayAppointment extends Usecase
{
	private DTOAppointment app;

	public DisplayAppointment(UserinterfaceData data, DTOAppointment app) {
		super(data);
		this.app = app;
	}

	@Override
	public Usecase Execute() {
		System.out.println(app);
		return new SelectUsecase(data);
	}

}
