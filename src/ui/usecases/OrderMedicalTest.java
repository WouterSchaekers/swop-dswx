package ui.usecases;

import medicaltest.BloodAnalysisFactory;
import medicaltest.MedicalTestFactory;
import medicaltest.UltraSoundScanFactory;
import medicaltest.XRayScanFactory;
import scheduler.HospitalDate;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.OrderMedicalTestController;
import controllers.interfaces.PatientFileIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;

public class OrderMedicalTest extends UseCase
{

	private OrderMedicalTestController controller;
	private PatientFileIN selectedPatientFile;
	private Displayer<MedicalTestFactory> medTestDisplayer = new Displayer<MedicalTestFactory>()
	{

		@Override
		public void display(MedicalTestFactory t) {
			print(fromFact(t).display());

		}
	};

	public OrderMedicalTest(UIData data) throws InvalidLoginControllerException, InvalidHospitalException,
			InvalidConsultPatientFileController, InvalidPatientFileException {
		super(data, 3);
		controller = new OrderMedicalTestController(data.getLoginController(),
				data.getConsultPatientFileopenController());
	}

	@Override
	public UseCase execute() {
		printLn("Create a medical test  v1.0");
		printLn("Medical test for:"
				+ data.getConsultPatientFileopenController().getPatientFile().getPatientIN().getName());
		selectedPatientFile = data.getConsultPatientFileopenController().getPatientFile();
		// patietnfile is set now.
		Selector<MedicalTestFactory> medtestSelector = new Selector<MedicalTestFactory>(
				controller.getMedicalTestFactories(), medTestDisplayer);
		MedicalTestFactory selected = medtestSelector.get();
		selected.setPatientFile(selectedPatientFile);

		return fromFact(selected);
	}

	private MedTestUsecase fromFact(MedicalTestFactory factory) {
		if (factory instanceof BloodAnalysisFactory)
			return new BloodAnalysisUsecase(data, (BloodAnalysisFactory) factory);
		if (factory instanceof UltraSoundScanFactory)
			return new UltraSoundScanUsecase(data, (UltraSoundScanFactory) factory);
		if (factory instanceof XRayScanFactory)
			return new XRayScanUsecase(data, (XRayScanFactory) factory);
		return null;

	}

	private abstract class MedTestUsecase extends UseCase
	{

		public MedTestUsecase(UIData data) {
			super(data, 0);
		}

		public abstract String display();
	}

	private class BloodAnalysisUsecase extends MedTestUsecase
	{

		private BloodAnalysisFactory fact;

		public BloodAnalysisUsecase(UIData data, BloodAnalysisFactory fact) {
			super(data);
			this.fact = fact;
		}

		@Override
		public UseCase execute() {
			printLn("Create a blood analysis.");
			print("Set focus(string):");
			String focus = read();
			fact.setFocus(focus);
			print("Set number of analysis(integer):");
			try {
				int number = new Integer(read());
				fact.setNumberOfAnalysis(number);
			} catch (Exception e) {
				print("Error not a valid number was entered, Q to quit");
				if (read().equalsIgnoreCase("Q"))
					return mm();
				else
					return this;
			}
			return new FactorySufficientLyInstantiated(data, fact);

		}

		@Override
		public String display() {
			return "Blood analysis";
		}

	}

	private class UltraSoundScanUsecase extends MedTestUsecase
	{

		private UltraSoundScanFactory fact;

		public UltraSoundScanUsecase(UIData data, UltraSoundScanFactory fact) {
			super(data);
			this.fact = fact;
		}

		@Override
		public UseCase execute() {
			printLn("Create Ultrasoundscan.");
			print("Focus:");
			fact.setFocus(read());
			printLn("Record images?");
			boolean bool = Selector.yesNoSelector.get();
			fact.setRecordImages(bool);
			printLn("Record vid?");
			fact.setRecordVid(Selector.yesNoSelector.get());

			return new FactorySufficientLyInstantiated(data, fact);
		}

		@Override
		public String display() {
			return "Ultra sound scan";
		}

	}

	private class XRayScanUsecase extends MedTestUsecase
	{

		private XRayScanFactory fact;

		public XRayScanUsecase(UIData data, XRayScanFactory fact) {
			super(data);
			this.fact = fact;
		}

		@Override
		public UseCase execute() {
			printLn("Set the details for the X-ray scan");
			print("Bodypart :");
			fact.setBodyPart(read());
			print("Number of required images (positive number):");
			try {
				int numberOfImages = new Integer(read());
				fact.setNumberOfNeededImages(numberOfImages);
			} catch (Exception e) {
				printLn("Wrong input, a number was not entered.");
				printLn("try again?");
				boolean again = Selector.yesNoSelector.get();
				if (again)
					return this;
				else
					return mm();
			}
			print("Zoomlevel (float between 0 and 4):");
			try {
				float zoomlevel = new Float(read());
				fact.setZoomLevel(zoomlevel);
			} catch (Exception e) {
				printLn("Wrong input, a number was not entered.");
				printLn("try again?");
				boolean again = Selector.yesNoSelector.get();
				if (again)
					return this;
				else
					return mm();
			}

			return new FactorySufficientLyInstantiated(data, fact);
		}

		@Override
		public String display() {
			return "X-ray scan.";
		}

	}

	private class FactorySufficientLyInstantiated extends UseCase
	{

		private MedicalTestFactory fact;

		public FactorySufficientLyInstantiated(UIData data, MedicalTestFactory fact) {
			super(data, 0);
			this.fact = fact;
		}

		@Override
		public UseCase execute() {
			HospitalDate startDate = null;
			try {
				startDate = controller.addMedicaltest(fact);
			} catch (FactoryInstantiationException e) {
				printLn("Factory was not sufficiently instantiated.");
				print(e.getMessage());
				return mm();
			} catch (CanNeverBeScheduledException e) {
				print(e.getMessage());
				return mm();
			}
			if (startDate == null) {
				printLn("Test is stored & not scheduled.");
				return mm();
			} else {
				print("Test is stored and scheduled at:");
				print(startDate.toString());
				return mm();
			}
		}

	}

	public String toString() {
		return "Order medical test";
	}

}
