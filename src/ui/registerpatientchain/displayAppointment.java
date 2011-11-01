package ui.registerpatientchain;

import ui.UserinterfaceData;
import ui.SelectUsecase;
import ui.usecase;
import controllers.DTOAppointment;

public class displayAppointment extends usecase
{
	private DTOAppointment app;

	public displayAppointment(UserinterfaceData data, DTOAppointment app) {
		super(data);
		this.app=app;
	}

	@Override
	public usecase Execute() {
		System.out.println(app);
		return new SelectUsecase(data);
	}

}
