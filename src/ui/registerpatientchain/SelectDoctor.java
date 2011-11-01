package ui.registerpatientchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ui.UserinterfaceData;
import ui.usecase;
import users.User.usertype;
import controllers.DTOAppointment;
import controllers.DTOPatientFile;
import controllers.RegisterPatientController;
import controllers.DTOUser;

public class SelectDoctor extends usecase
{
	DTOPatientFile pfile;
	RegisterPatientController rpc;

	public SelectDoctor(UserinterfaceData data, DTOPatientFile patientFileDTO,
			RegisterPatientController rpc) {
		super(data);
		this.pfile = patientFileDTO;
		this.rpc = rpc;
	}

	@Override
	public usecase Execute() {
		ArrayList<DTOUser> docters = new ArrayList<DTOUser>();
		System.out.println("Select a doctor from  the list of doctors:");
		for (DTOUser u : data.getLoginController().getAllUsers()) {
			if (u.type() == usertype.Doctor)
				docters.add(u);
		}
		Map<String, DTOUser> map = new HashMap<String, DTOUser>();
		for (DTOUser doctor : docters) {
			System.out.println(doctor.getName());
			map.put(doctor.getName(), doctor);
		}
		String name = input.nextLine();
		if(!map.containsKey(name))
		{
			System.out.println(name+" is not a valid doctor.");
			return this;
		}

		DTOAppointment app= rpc.CreateAppointMent(map.get(name),pfile);
		return new displayAppointment(data,app);
	}

}
