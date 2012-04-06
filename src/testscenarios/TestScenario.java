package testscenarios;

import java.util.Collection;
import system.Hospital;
import system.Location;
import users.Doctor;
import controllers.ConsultPatientFileController;
import controllers.LoginController;
import controllers.interfaces.CampusIN;
import controllers.interfaces.PatientFileIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

/**
 * Executes our testing scenario by only using the controllers.
 */
public class TestScenario
{

	private Hospital hospital;

	public TestScenario(Hospital initialisedHospital) throws InvalidHospitalException, InvalidLoginControllerException {
		hospital = initialisedHospital;
		runScenario();
	}

	private void runScenario() throws InvalidHospitalException, InvalidLoginControllerException {
		LoginController lc = loginUser("Jennifer", hospital.getCampus("Campus 2"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor)lc.getUserIN(), "Stefaan", lc);		
		
	}
	
	private LoginController loginUser(String name, Location location) throws InvalidHospitalException {
		System.out.print("Logging in user " + name + " on " + location + "... ");
		LoginController lc = new LoginController(this.hospital);
		lc.logIn(lc.getSpecificDoctor(name), (CampusIN) location);
		System.out.println("Success!");
		return lc;
	}
	
	private ConsultPatientFileController openPatientFile(Doctor doc, String patientName, LoginController lc) throws InvalidHospitalException, InvalidLoginControllerException {
		System.out.print("Letting Doctor " +doc.getName() + " consult patient file of " + patientName + "... ");
		ConsultPatientFileController cpfc = new ConsultPatientFileController(lc);
		PatientFileIN patient = null;
		Collection<PatientFileIN> pfs = cpfc.getAllPatientFiles();
		for(PatientFileIN pf : pfs)
			if(pf.getPatientIN().getName().equals(patientName))
				patient = pf;
		cpfc.openPatientFile(patient);
		System.out.println("Success!");
		return cpfc;
	}
}