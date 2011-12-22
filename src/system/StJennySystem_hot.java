package system;

import patient.PatientFileManager;
import scheduler.Scheduler;
import ui.UCHandler;
import ui.UserinterfaceData;
import users.UserManager;
import controllers.DataPasser;

public class StJennySystem_hot
{
	public static void main(String[] args) {
	UserManager m = new UserManager();
	PatientFileManager pf= new PatientFileManager();
	Scheduler s = new Scheduler();
	try {
		m.createHospitalAdmin("jeffry");
		m.createNurse("jenny");
		m.createDoctor("vinc");
		pf.registerPatient("simon");
		pf.registerPatient("jasper");
		
	} catch (Exception e) {}
	UserinterfaceData data = new UserinterfaceData(new DataPasser(m,pf, s));
	UCHandler handler = new UCHandler(data);
	handler.start();
	}
	
}
