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
import treatment.SurgeryFactory;
import treatment.TreatmentFactory;
import users.Doctor;
import controllers.ConsultPatientFileController;
import controllers.LoginController;
import controllers.OrderMedicalTestController;
import controllers.PrescribeTreatmentController;
import controllers.interfaces.CampusIN;
import controllers.interfaces.DiagnoseIN;
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
		advanceTime(HospitalDate.ONE_MINUTE * 30);
		letJoanneOrderDieterTreatment();
	}

	private void letJenniferOrderStefTest() throws Exception {
		LoginController lc = loginUser("Jennifer", hospital.getCampus("Campus 2"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Stefaan", lc);
		OrderMedicalTestController omtc = new OrderMedicalTestController(lc, cpfc);
		BloodAnalysisFactory bloodFac = getBloodFactory(omtc, "Adrenaline", 3, (PatientFile) cpfc.getPatientFile());
		orderMedicalTestFor(omtc, (PatientFile) cpfc.getPatientFile(), bloodFac);
		logoutUser(lc, cpfc);
	}
	
	private void letJoanneOrderDieterTreatment() throws Exception {
		LoginController lc = loginUser("Joanne", hospital.getCampus("Campus 1"));
		ConsultPatientFileController cpfc = openPatientFile((Doctor) lc.getUserIN(), "Dieter", lc);
		PrescribeTreatmentController ptc = new PrescribeTreatmentController(lc, cpfc);

		// should have just one diagnose, so this is fine
		DiagnoseIN diag = ptc.getAllPossibleDiagnosis().iterator().next(); 
		SurgeryFactory surgFac = getSurgeryFactory(ptc, "Brain surgery", (PatientFile) cpfc.getPatientFile(), diag);
		prescribeTreatmentFor(ptc, diag, surgFac);
		logoutUser(lc, cpfc);
	}

	private LoginController loginUser(String name, Location location) throws Exception {
		LoginController lc = new LoginController(this.hospital);
		lc.logIn(lc.getSpecificDoctor(name), (CampusIN) location);
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
		ConsultPatientFileController cpfc = new ConsultPatientFileController(lc);
		PatientFileIN patient = PatientFileFilter(cpfc.getAllPatientFiles(), patientName);
		cpfc.openPatientFile(patient);
		return cpfc;
	}

	private void orderMedicalTestFor(OrderMedicalTestController omtc, PatientFile pf, MedicalTestFactory factory) throws Exception{
		omtc.addMedicaltest(factory);
	}
	
	private void prescribeTreatmentFor(PrescribeTreatmentController ptc, DiagnoseIN diag, TreatmentFactory factory) throws Exception{
		ptc.addTreatment(diag, factory);
	}

	private BloodAnalysisFactory getBloodFactory(OrderMedicalTestController omtc, String focus, int number,
			PatientFile pf) {
		Collection<MedicalTestFactory> facs = omtc.getMedicalTestFactories();
		for (MedicalTestFactory curFac : facs) {
			if (curFac instanceof BloodAnalysisFactory) {
				((BloodAnalysisFactory) curFac).setFocus(focus);
				((BloodAnalysisFactory) curFac).setNumberOfAnalysis(number);
				curFac.setPatientFile(pf);
				return (BloodAnalysisFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no BloodAnalysisFactory exists?");
	}
	
	private SurgeryFactory getSurgeryFactory(PrescribeTreatmentController ptc, String description, PatientFile patientFile, DiagnoseIN diagnose) {
		Collection<TreatmentFactory> facs = ptc.getTreatmentFactories();
		for(TreatmentFactory curFac : facs) {
			if(curFac instanceof SurgeryFactory) {
				((SurgeryFactory) curFac).setDescription(description);
				curFac.setDiagnose(diagnose);
				return (SurgeryFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no SurgeryFactory exists?");
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
				return (UltraSoundScanFactory) curFac;
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
				return (XRayScanFactory) curFac;
			}
		}
		throw new IllegalStateException("Apparently... no XRayScanFactory exists?");
	}
	
	private void advanceTime(long amountOfTimeToAdvance) throws Exception{
		LoginController lc = new LoginController(hospital);
		//getAdmin(hospital.getUserManager().getAllUsers());
		//lc.logIn(, at)
	}
	
	public PatientFileIN PatientFileFilter(Collection<PatientFileIN> patients, String name) {
		for (PatientFileIN u : patients)
			if (u.getPatientIN().getName().equals(name))
				return u;
		return null;
	}

}