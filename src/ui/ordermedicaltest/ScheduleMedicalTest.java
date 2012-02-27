package ui.ordermedicaltest;


import medicaltest.MedicalTest;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import exceptions.InvalidAmountException;
import exceptions.InvalidDurationException;
import exceptions.InvalidHospitalDateArgument;
import exceptions.InvalidHospitalDateException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidOccurencesException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidPatientFileOpenController;
import exceptions.InvalidResourceException;
import exceptions.InvalidTimeSlotException;
import exceptions.InvalidTreatmentException;

public class ScheduleMedicalTest extends OrderMedicalTestSuperClass
{

	public ScheduleMedicalTest(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
	}

	@Override
	public Usecase Execute() {
		MedicalTest t = null;
		try {
			t = chaindata.getFactory().create();
		} catch (Exception e1) {
			System.out.println("Medical test failed to create ");
			return new SelectUsecase(data);
		}
		try {
			chaindata.getMedTestController().addMedicaltest(data.getLoginController(),data.getPatientFileOpenController(),t, data.getDataPasser());
		} catch (InvalidLoginControllerException e) {
			System.out.println("invalid user");
			return new SelectUsecase(data);
		} catch (InvalidPatientFileException e) {
			System.out.println("bad logincontroller");
			return new SelectUsecase(data);
			
		} catch (InvalidDurationException e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
		
		} catch (InvalidTimeSlotException e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
		
		} catch (InvalidResourceException e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
			
		} catch (InvalidOccurencesException e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
			
		} catch (InvalidAmountException e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
			
		} catch (InvalidHospitalDateException e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
			
		} catch (InvalidTreatmentException e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
			
		} catch (InvalidHospitalDateArgument e) {

			System.out.println("internal system error");
			return new SelectUsecase(data);
		
		} catch (InvalidPatientFileOpenController e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Your medicaltest has now been scheduled !");
		System.out.println(t.appointMentInfo());
		return new SelectUsecase(data);
	}

}
