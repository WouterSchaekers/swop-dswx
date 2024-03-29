package ui.registerpatient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import users.Doctor;
import controllers.interfaces.AppointmentIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;

public class SelectDoctor extends Usecase
{
	PatientFileIN pfile;

	public SelectDoctor(UserinterfaceData data, PatientFileIN patientFileDTO) {
		super(data);
		this.pfile = patientFileDTO;
	}

	@Override
	public Usecase Execute() {
		ArrayList<UserIN> docters = new ArrayList<UserIN>();
		System.out.println("Select a doctor from  the list of doctors:");
		for (UserIN u : data.getLoginController().getAllUsers()) {
			if (u instanceof Doctor)
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

		AppointmentIN app = null;
		try {
			app = null;// rpc.CreateAppointMent(map.get(name),
						// pfile,data.getDataPasser());
		} catch (Exception e) {
			System.out.println("Internal system error");
			return new SelectUsecase(data);
		}
		return new DisplayAppointment(data, app);
	}

}
