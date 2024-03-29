package ui.registerpatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.interfaces.AppointmentIN;

public class DisplayAppointment extends Usecase
{
	private AppointmentIN app;

	public DisplayAppointment(UserinterfaceData data, AppointmentIN app) {
		super(data);
		this.app = app;
	}

	@Override
	public Usecase Execute() {
		System.out.println(app);
		return new SelectUsecase(data);
	}

}
