package ui.registerpatientchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import scheduler.Appointment;
import ui.DataBlob;
import ui.usecase;
import users.User.usertype;
import controllers.AppointmentDTO;
import controllers.PatientFileDTO;
import controllers.RegisterPatientController;
import controllers.UserDTO;

public class SelectDoctor extends usecase
{
	PatientFileDTO pfile;
	RegisterPatientController rpc;

	public SelectDoctor(DataBlob data, PatientFileDTO patientFileDTO,
			RegisterPatientController rpc) {
		super(data);
		this.pfile = patientFileDTO;
		this.rpc = rpc;
	}

	@Override
	public usecase Execute() {
		ArrayList<UserDTO> docters = new ArrayList<UserDTO>();
		System.out.println("Select a doctor from  the list of doctors:");
		for (UserDTO u : data.getLoginController().getAllUsers2()) {
			if (u.type() == usertype.Doctor)
				docters.add(u);
		}
		Map<String, UserDTO> map = new HashMap<String, UserDTO>();
		for (UserDTO doctor : docters) {
			System.out.println(doctor.getName());
			map.put(doctor.getName(), doctor);
		}
		String name = input.nextLine();
		if(!map.containsKey(name))
		{
			System.out.println(name+" is not a valid doctor.");
			return this;
		}

		AppointmentDTO app= rpc.CreateAppointMent(map.get(name),pfile);
		return new displayAppointment(data,app);
	}

}
