package ui.usecases;

import java.util.Collection;
import controllers.PrescribeTreatmentController;
import controllers.interfaces.DiagnoseIN;
import exceptions.CanNeverBeScheduledException;
import exceptions.FactoryInstantiationException;
import exceptions.InvalidConsultPatientFileController;
import exceptions.InvalidDiagnoseException;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidPatientFileException;
import treatment.CastFactory;
import treatment.MedicationFactory;
import treatment.SurgeryFactory;
import treatment.TreatmentFactory;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;

public class PrescribeTreatment extends UseCase
{

	private PrescribeTreatmentController controller;

	public PrescribeTreatment(UIData data) throws Exception {
		super(data, 123);
		controller = new PrescribeTreatmentController(data.getLoginController(),
				data.getConsultPatientFileopenController());
		throw new Exception();
	}

	@Override
	public UseCase execute() {
		
		print("Prescribe treatment for patient"
				+ data.getConsultPatientFileopenController().getPatientFile().getPatientIN().getName());
		DiagnoseIN selectedDiagnose;
		Collection<DiagnoseIN> diags = controller.getAllPossibleDiagnosis();
		Selector<DiagnoseIN> diagnoseSelector = new Selector<DiagnoseIN>(diags, Selector.diagnose);
		selectedDiagnose = diagnoseSelector.get();
		print("Diagnose ");
		Selector.diagnose.display(selectedDiagnose);
		printLn(" was selected, select the treatment for this diagnose.");
		Collection<TreatmentFactory> treatmentfacts = controller.getTreatmentFactories();
		Selector<TreatmentFactory> treatmentSelector = new Selector<TreatmentFactory>(treatmentfacts,
				new Displayer<TreatmentFactory>()
				{
					@Override
					public void display(TreatmentFactory treat) {
						new TreatUsecase(data).fromFact(treat).display();
					}
				});
		TreatmentFactory factory =treatmentSelector.get();
		factory.setDiagnose(selectedDiagnose);
		return new TreatUsecase(data).fromFact(factory);
	}

	@Override
	public String toString() {
		return "Prescribe treatment.";
	}

	private class TreatUsecase
	{
		private UIData data_;

		public TreatUsecase(UIData data) {
			data_ = data;
		}

		public FactUseCase fromFact(TreatmentFactory factory) {
			if (factory instanceof CastFactory)
				return new CastFactUsecase(data_, factory);
			if (factory instanceof MedicationFactory)
				return new MedicationFactUsecase(data_, factory);
			if (factory instanceof SurgeryFactory)
				return new SurgeryFactUsecase(data_, factory);
			return new UnknownFactory(data);
		}

	}

	private abstract class FactUseCase extends UseCase
	{
		public FactUseCase(UIData data, Integer prior) {
			super(data, prior);
		}

		public abstract void display();
	}

	private class MedicationFactUsecase extends FactUseCase
	{

		private MedicationFactory factory;

		public MedicationFactUsecase(UIData data, TreatmentFactory factory) {
			super(data, 0);
			this.factory = (MedicationFactory) factory;
		}

		@Override
		public UseCase execute() {
			printLn("Creating medication:");
			throw new Error("Not yet imlemented");
		}

		@Override
		public void display() {
			print("Medication.");

		}

	}

	private class CastFactUsecase extends FactUseCase
	{
		private CastFactory factory;

		public CastFactUsecase(UIData data, TreatmentFactory factory) {
			super(data, 0);
			this.factory = (CastFactory) factory;
		}

		@Override
		public UseCase execute() {
			printLn("Creating a new cast ");
			print("Give the bodypart for the cast:");
			String bodyPart= read();
			print("Give the time the patient has to wear the cast: ( a number in days)");
			Integer duration;
			try{
			duration = new Integer(read());
			}catch(Exception e)
			{
				printLn("Not a number, please try again.");
				return this;
			}
			factory.setBodyPart(bodyPart);
			factory.setDuration(duration);
			try {
				controller.addTreatment(null, factory);
			} catch (InvalidDiagnoseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FactoryInstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CanNeverBeScheduledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new Error("Not yet imlemented");
		}

		@Override
		public void display() {
			print("Cast.");

		}

	}

	private class UnknownFactory extends FactUseCase
	{

		public UnknownFactory(UIData data) {
			super(data, 0);
		}

		@Override
		public UseCase execute() {
			printLn("UNKOWN FACTORY");
			return mm();
		}

		@Override
		public void display() {
			print("Unkown");

		}

	}

	private class SurgeryFactUsecase extends FactUseCase
	{
		private SurgeryFactory factory;

		public SurgeryFactUsecase(UIData data, TreatmentFactory factory) {
			super(data, 0);
			this.factory = (SurgeryFactory) factory;
		}

		@Override
		public void display() {
			print("Surgery");
		}

		@Override
		public UseCase execute() {
			throw new Error("Not yet imlemented");
		}

	}

}
