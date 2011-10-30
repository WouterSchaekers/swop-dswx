package ui.registerpatientchain;

import ui.DataBlob;
import ui.SelectUsecase;
import ui.usecase;
import controllers.AppointmentDTO;

public class displayAppointment extends usecase
{
	private AppointmentDTO app;

	public displayAppointment(DataBlob data, AppointmentDTO app) {
		super(data);
		this.app=app;
	}

	@Override
	public usecase Execute() {
		System.out.println(app);
		return new SelectUsecase(data);
	}

}
