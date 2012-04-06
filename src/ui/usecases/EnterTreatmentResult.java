package ui.usecases;

import java.util.Collection;
import result.CastResultFactory;
import result.MedicationResultFactory;
import result.ResultFactory;
import result.SurgeryResultFactory;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.EnterTreatmentResultController;
import controllers.interfaces.TaskIN;

public class EnterTreatmentResult extends UseCase
{

	private EnterTreatmentResultController controller;
	private Displayer<TaskIN> taskINDisplayer = new Displayer<TaskIN>()
	{

		@Override
		public void display(TaskIN t) {
			print(fromTreatment(t.getResultFactory()).name());			
		}

		
	};
	private TaskIN selectedTask;

	public EnterTreatmentResult(UIData data) throws Exception {
		super(data,0);
		controller = new EnterTreatmentResultController(data.getLoginController());
		
	}

	@Override
	public UseCase execute() {
		Collection<TaskIN> treatments = controller.getTreatments();
		if(treatments.isEmpty())
		{
			printLn("No treatments!");
			return mm();
		}
		Selector<TaskIN> treatmentSelector = new Selector<TaskIN>(treatments,taskINDisplayer);
		selectedTask = treatmentSelector.get();
		ResultFactory chosen =selectedTask.getResultFactory();
		return fromTreatment(chosen);
	}
	private TreatmentUsecase fromTreatment(ResultFactory resultFactory) {
		if(resultFactory instanceof CastResultFactory)
			return new CastResultFact(data,resultFactory);
		if(resultFactory instanceof MedicationResultFactory)
			return new MedicationResultFact(data,resultFactory);
		if(resultFactory instanceof SurgeryResultFactory)
			return new SurgeryResultFact(data,resultFactory);
		return null;
	}
	private abstract class TreatmentUsecase extends UseCase
	{

		public TreatmentUsecase(UIData data ) {
			super(data, 0);
		}

		public abstract String name(); 
		
	}
	private class CastResultFact extends TreatmentUsecase
	{
		CastResultFactory fact;
		public CastResultFact(UIData data, ResultFactory resultFactory) {
			super(data);
			fact = (CastResultFactory) resultFactory;
		}

		@Override
		public String name() {
			return "cast";
		}

		@Override
		public UseCase execute() {
			printLn("Cast result:");
			print("Set report:");
			fact.setReport(read());
			try {
				controller.setResult(selectedTask, fact);
			} catch (Exception e) {
				print(e.getMessage());
				return mm();
			}
			printLn("Result succesfully entered.");
			return mm();
		}
		
	}
	private class MedicationResultFact extends TreatmentUsecase
	{
		MedicationResultFactory fact;
		public MedicationResultFact(UIData data, ResultFactory resultFactory) {
			super(data);
			fact = (MedicationResultFactory) resultFactory;
		}

		@Override
		public String name() {
			return "medication: " + fact.getType().name();
		}

		@Override
		public UseCase execute() {
			printLn(name());
			printLn("Was the patients reaction normal?");
			fact.setAbnormalReaction(Selector.yesNoSelector.get());
			print("Report:");
			String report =read();
			fact.setReport(report);
			try {
				controller.setResult(selectedTask, fact);
			} catch (Exception e) {
				print(e.getMessage());
				return mm();
			}
			printLn("Result succesfully entered.");// TODO Auto-generated method stub
			return mm();
		}
		
	}
	private class SurgeryResultFact extends TreatmentUsecase
	{
		SurgeryResultFactory fact;
		public SurgeryResultFact(UIData data, ResultFactory resultFactory) {
			super(data);
			fact = (SurgeryResultFactory) resultFactory;
		}

		@Override
		public String name() {
			return "surgery";
		}

		@Override
		public UseCase execute() {
			printLn(name());
			print("Aftercare:");
			fact.setAfterCare(read());
			print("Report:");
			fact.setReport(read());
			try {
				controller.setResult(selectedTask, fact);
			} catch (Exception e) {
				print(e.getMessage());
				return mm();
			}
			printLn("Result succesfully entered.");
			return mm();
		}
		
	}
	
	@Override
	public String toString()
	{
		return "enter treatment result";
	}

}
