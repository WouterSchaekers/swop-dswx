package ui.usecases;

import java.util.Arrays;
import java.util.Collection;
import result.BloodAnalysisResultFactory;
import result.ResultFactory;
import result.UltraSoundScanResult;
import result.UltraSoundScanResult.ScannedMassNature;
import result.UltraSoundScanResultFactory;
import result.XRayScanResultFactory;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.EnterMedicaltestResultController;
import controllers.EnterTreatmentResultController;
import controllers.interfaces.PatientDescriptionHolderIN;
import controllers.interfaces.TaskIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;

public class EnterMedicalTestResult extends UseCase
{

	private EnterMedicaltestResultController controller;
	private ResultFactory factory;
	private TaskIN task;
	private Displayer<TaskIN> resultDisplayer = new Displayer<TaskIN>()
	{

		@Override
		public void display(TaskIN t) {
			print(fromFactory(t.getResultFactory()).name()+" for patient:"+patientName(t));
		}

		private String patientName(TaskIN t) {
			return ((PatientDescriptionHolderIN)t.getDescription()).getPatientFile().getPatientIN().getName();
		}
	};
	public EnterMedicalTestResult(UIData data) throws Exception {
		super(data, 33);
		controller = new EnterMedicaltestResultController(data.getLoginController());
//		if(controller.getMedicalTests().isEmpty())
//			throw new Exception();
	}

	@Override
	public UseCase execute() {
		printLn("Add treatment result.");
		Collection<TaskIN>medTests= controller.getMedicalTests();
		if(medTests.isEmpty())
		{
			printLn("No medical tests.");
			return mm();
		}
		Selector<TaskIN> resultFactorySelector = new Selector<TaskIN>(medTests, resultDisplayer );
		TaskIN task =resultFactorySelector.get();
		factory=task.getResultFactory();
		return fromFactory(factory);
	}
	private TreatmentResultUsecase fromFactory(ResultFactory resultFactory)
	{
		if(resultFactory instanceof BloodAnalysisResultFactory)
			return new BloodAnalysis(data,(BloodAnalysisResultFactory)factory);
		if(resultFactory instanceof UltraSoundScanResultFactory)
			return new UltraSound(data,(UltraSoundScanResultFactory)factory);
		if(resultFactory instanceof XRayScanResultFactory)
			return new XrayScan(data,(XRayScanResultFactory)factory);
		return new Unknown(data);
						
	}
	private abstract class TreatmentResultUsecase extends UseCase
	{

		public TreatmentResultUsecase(UIData data) {
			super(data, 0);
		}

		public abstract String name();
		
	}
	private class BloodAnalysis extends TreatmentResultUsecase
	{
		
		private BloodAnalysisResultFactory fact;

		public BloodAnalysis(UIData data, BloodAnalysisResultFactory factory) {
			super(data);
			fact = factory;
		}

		@Override
		public UseCase execute() {
			print("Set the amount of blood(positive integer):");
			int amountOfBlood;
			try{
				amountOfBlood = new Integer(read());
			}catch(Exception e)
			{
				printLn("Enter an integer.");
				return this;
			}
			fact.setAmountOfBlood(amountOfBlood);
			print("Set the plateletcount(positive integer):");
			int plateletcount;
			try{
				plateletcount = new Integer(read());
			}catch(Exception e)
			{
				printLn("Enter an integer.");
				return this;
			}
			fact.setPlateletCount(plateletcount);
			print("Set the red cell count(positive integer):");
			int redCellCount;
			try{
				redCellCount = new Integer(read());
			}catch(Exception e)
			{
				printLn("Enter an integer.");
				return this;
			}
			fact.setRedCellCount(redCellCount);
			print("Set the white cell count(positive integer):");
			int whiteCellCount;
			try{
				whiteCellCount = new Integer(read());
			}catch(Exception e)
			{
				printLn("Enter an integer.");
				return this;
			}
			fact.setWhiteCellCount(whiteCellCount);
			try {
				controller.setResult(task, fact);
			} catch (Exception e) {
				printLn(e.getMessage());
				return mm();
			}
			printLn("Result succesfully entered!");
			return mm();
		}

		@Override
		public String name() {
			return "blood analysis";
		}
		
	}
	private class UltraSound extends TreatmentResultUsecase
	{

		private UltraSoundScanResultFactory fact;

		public UltraSound(UIData data, UltraSoundScanResultFactory factory) {
			super(data);
			fact = factory;
		}

		@Override
		public UseCase execute() {
			printLn("Ultra sound result: ");
			printLn("Select the nature of the scanned mass");
			Selector<UltraSoundScanResult.ScannedMassNature> natureSelector = new Selector<UltraSoundScanResult.ScannedMassNature>(Arrays.asList(UltraSoundScanResult.ScannedMassNature.values()), new Displayer<UltraSoundScanResult.ScannedMassNature>()
			{
				@Override
				public void display(UltraSoundScanResult.ScannedMassNature nature)
				{
					print(nature.name());
				}
			});
			ScannedMassNature nature = natureSelector.get();
			fact.setScannedMassNature(nature);
			print("Set scan info:");
			fact.setScanInfo(read());
			try {
				controller.setResult(task, fact);
			} catch (Exception e) {
				printLn(e.getMessage());
				return mm();
			}
			printLn("Result succesfully added.");
			return mm();
		}

		@Override
		public String name() {
			return "ultra sound scan";
		}
		
	}
	private class XrayScan extends TreatmentResultUsecase
	{

		private XRayScanResultFactory fact;

		public XrayScan(UIData data, XRayScanResultFactory factory) {
			super(data);
			fact = factory;
		}

		@Override
		public UseCase execute() {
			printLn("X ray scan result:");
			print("Set abnormalities:");
			fact.setAbnormalities(read());
			print("Number of images(pos int):");
			int numberOfImages;
			try
			{
				numberOfImages = new Integer(read());
			}catch(Exception e)
			{
				printLn("Enter a number");
				return this;
			}
			fact.setNumberOfImages(numberOfImages);
			try {
				controller.setResult(task, fact);
			} catch (Exception e) {
				printLn(e.getMessage());
				return mm();
			}
			printLn("Result succesfully added.");
			return mm();
		}

		@Override
		public String name() {
			return "xray ";
		}
		
	}
	private class Unknown extends TreatmentResultUsecase
	{

		public Unknown(UIData data) {
			super(data);
		}

		@Override
		public UseCase execute() {
			printLn("Unknown");
			return mm();
		}

		@Override
		public String name() {
			return "noname";
		}
		
	}
	public String toString()
	{
		return "Add medTest result";
	}
}
