package testscenarios;

import java.util.Collection;
import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import patient.PatientFile;
import scheduler.HospitalDate;
import system.Hospital;
import system.Location;
import users.Doctor;
import users.User;
import controllers.ConsultPatientFileController;
import controllers.LoginController;
import controllers.OrderMedicalTestController;
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

	public TestScenario(Hospital initialisedHospital) throws Exception {
		hospital = initialisedHospital;
		runScenario();
	}

	private void runScenario() throws Exception {
		letJenniferOrderStefTest();
		//advanceTime(HospitalDate.ONE_MINUTE * 10);
	}

	private void letJenniferOrderStefTest() throws Exception{
		LoginController lc = loginUser("Jennifer", hospital.getCampus("Campus 2"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Stefaan", lc);
		OrderMedicalTestController omtc = getMedicalTestController(lc, cpfc);
		BloodAnalysisFactory bloodFac = getBloodFactory(omtc, "Adrenaline", 3,
				(PatientFile) PatientFileFilter(omtc.getPatientFiles(), "Stefaan"));
		orderMedicalTestFor(omtc, (PatientFile) PatientFileFilter(omtc.getPatientFiles(), "Stefaan"), bloodFac);
		logoutUser(lc, cpfc);
	}
	
	private LoginController loginUser(String name, Location location) throws Exception {
		System.out.print("Logging in user " + name + " on " + location + "... ");
		LoginController lc = new LoginController(this.hospital);
		lc.logIn(lc.getSpecificDoctor(name), (CampusIN) location);
		System.out.println("Success!");
		return lc;
	}

	/**
	 * Breaks given controllers.
	 */
	private void logoutUser(LoginController lc, ConsultPatientFileController cpfc) {
		lc = null;
		cpfc = null;
	}

	private ConsultPatientFileController openPatientFile(Doctor doc, String patientName, LoginController lc)
			throws InvalidHospitalException, InvalidLoginControllerException {
		System.out.print("Letting Doctor " + doc.getName() + " consult patient file of " + patientName + "... ");
		ConsultPatientFileController cpfc = new ConsultPatientFileController(lc);
		PatientFileIN patient = PatientFileFilter(cpfc.getAllPatientFiles(), patientName);
		cpfc.openPatientFile(patient);
		System.out.println("Success!");
		return cpfc;
	}

	private OrderMedicalTestController getMedicalTestController(LoginController lc, ConsultPatientFileController cpfc)
			throws Exception {
		return new OrderMedicalTestController(lc, cpfc);
	}
	
	private void orderMedicalTestFor(OrderMedicalTestController omtc, PatientFile pf, MedicalTestFactory factory) throws Exception{
		HospitalDate date = omtc.addMedicaltest(factory);
		System.out.println("Success! Date = " + date);
	}

	private BloodAnalysisFactory getBloodFactory(OrderMedicalTestController omtc, String focus, int number,
			PatientFile pf) {
		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof BloodAnalysisFactory) {
				((BloodAnalysisFactory) curFac).setFocus(focus);
				((BloodAnalysisFactory) curFac).setNumberOfAnalysis(number);
				curFac.setPatientFile(pf);
				return (BloodAnalysisFactory) curFac.newInstance();
			}
		}
		throw new IllegalStateException("Apparently... no BloodAnalysisFactory exists?");
	}

	private UltraSoundScanFactory getUltraSoundScanFactory(OrderMedicalTestController omtc, String focus,
			boolean recVid, boolean recImg, PatientFile pf) {
		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof UltraSoundScanFactory) {
				((UltraSoundScanFactory) curFac).setFocus(focus);
				((UltraSoundScanFactory) curFac).setRecordImages(recImg);
				((UltraSoundScanFactory) curFac).setRecordVid(recVid);
				curFac.setPatientFile(pf);
				return (UltraSoundScanFactory) curFac.newInstance();
			}
		}
		throw new IllegalStateException("Apparently... no UltraSoundScanFactory exists?");
	}

	private XRayScanFactory getXRayScanFactory(OrderMedicalTestController omtc, String bodyPart, int number,
			float zoom, PatientFile pf) {
		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof XRayScanFactory) {
				((XRayScanFactory) curFac).setBodyPart(bodyPart);
				((XRayScanFactory) curFac).setNumberOfNeededImages(number);
				((XRayScanFactory) curFac).setZoomLevel(zoom);
				curFac.setPatientFile(pf);
				return (XRayScanFactory) curFac.newInstance();
			}
		}
		throw new IllegalStateException("Apparently... no XRayScanFactory exists?");
	}
	
	private void advanceTime() throws Exception{
		LoginController lc = new LoginController(hospital);
		//getAdmin(hospital.getUserManager().getAllUsers());
		// lc.logIn(, at)
	}
	
	public PatientFileIN PatientFileFilter(Collection<PatientFileIN> patients, String name) {
		for (PatientFileIN u : patients)
			if (u.getPatientIN().getName().equals(name))
				return u;
		return null;
	}

}