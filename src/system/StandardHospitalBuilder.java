package system;

import patient.PatientFileManager;
import scheduler.TimeLord;
import users.HospitalAdmin;
import users.User;
import users.UserManager;

public class StandardHospitalBuilder
{
	private HospitalAdmin getAdmin(UserManager userm)
	{
		for(User u:userm.getAllUsers())
			if(u instanceof HospitalAdmin)
				return (HospitalAdmin)u;
		return null;
	}
	public Hospital build() {
		TimeLord timeKeeper = new TimeLord();
		StandarUserManagerBuilder sum = new StandarUserManagerBuilder();
		UserManager userm=sum.create();
		HospitalAdmin admin = getAdmin(userm);
		Hospital hospital =new Hospital(timeKeeper, sum.create(), new PatientFileManager(), admin, new TaskManagerBuilder());// new Hospital(timeKeeper, sum.create(), new PatientFileManager() ,new TaskManagerBuilder());
		CampusBuilder campusb = new CampusBuilder("Campus 1",hospital);
		CampusBuilder campusb2 = new CampusBuilder("Campus 2",hospital);
		campusb.create();
		campusb2.create();
		return hospital;
	}

}
