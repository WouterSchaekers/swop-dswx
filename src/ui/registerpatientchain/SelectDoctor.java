package ui.registerpatientchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ui.UserinterfaceData;
import ui.Usecase;
import users.UserType;
import controllers.RegisterPatientController;
import controllers.interfaces.AppointmentIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;

public class SelectDoctor extends Usecase
{
	PatientFileIN pfile;
	RegisterPatientController rpc;

	public SelectDoctor(UserinterfaceData data, PatientFileIN patientFileDTO,
			RegisterPatientController rpc) {
		super(data);
		this.pfile = patientFileDTO;
		this.rpc = rpc;
	}

	@Override
	public Usecase Execute() {
		ArrayList<UserIN> docters = new ArrayList<UserIN>();
		System.out.println("Select a doctor from  the list of doctors:");
		for (UserIN u : data.getLoginController().getAllUsers()) {
			if (u.type() == UserType.Doctor)
				docters.add(u);
		}
		Map<String, UserIN> map = new HashMap<String, UserIN>();
		for (UserIN doctor : docters) {
			System.out.println(doctor.getName());
			map.put(doctor.getName(), doctor);
		}
		String name = input.nextLine();
		if (!map.containsKey(name)) {
			System.out.println(name + " is not a valid doctor.");
			return this;
		}

		AppointmentIN app = rpc.CreateAppointMent(map.get(name), pfile);
		return new DisplayAppointment(data, app);
	}

}
