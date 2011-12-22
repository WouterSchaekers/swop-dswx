package ui.registerpatientchain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ui.UserinterfaceData;
import ui.Usecase;
import users.Doctor;
import controllers.RegisterPatientController;
import controllers.interfaces.AppointmentIN;
import controllers.interfaces.PatientFileIN;
import controllers.interfaces.UserIN;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidRequirementException;
import exceptions.InvalidResourceException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidTimeSlotException;

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
			app = rpc.CreateAppointMent(map.get(name), pfile,data.getDataPasser());
		} catch (InvalidTimeSlotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSchedulingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidOccurencesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequirementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidHospitalDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DisplayAppointment(data, app);
	}

}
