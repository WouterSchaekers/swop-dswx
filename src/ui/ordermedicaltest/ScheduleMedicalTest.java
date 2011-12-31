package ui.ordermedicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.InvalidPatientFileException;
import exceptions.InvalidTimeSlotException;
import ui.Usecase;
import ui.UserinterfaceData;

public class ScheduleMedicalTest extends OrderMedicalTestSuperClass
{

	public ScheduleMedicalTest(UserinterfaceData uiData, MedicalTestData medData) {
		super(uiData, medData);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		try {
			chaindata.getMedTestController().addMedicaltest(data.getLoginController(),data.getPatientFileOpenController(),chaindata.getFactory().create());
		} catch (InvalidLoginControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPatientFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTimeSlotException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryInstantiation e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
