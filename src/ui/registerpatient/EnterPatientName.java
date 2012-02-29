package ui.registerpatient;

import java.util.Map;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;
import controllers.interfaces.PatientFileIN;

public class EnterPatientName extends Usecase
{
	private Map<String, PatientFileIN> namePatientMap;
	private RegisterPatientController rpc;

	public EnterPatientName(UserinterfaceData data,
			Map<String, PatientFileIN> namePatientMap,
			RegisterPatientController rpc) {
		super(data);
		this.namePatientMap = namePatientMap;
		this.rpc = rpc;
	}

	@Override
	public Usecase Execute() {
		System.out.println("Choose a menu option");
		System.out.println("0: Enter existing patient name:");
		System.out.println("1: Create new patient in the database");
		int choice;
		try {
			choice = new Integer(input.nextLine());
		} catch (NumberFormatException Num) {
			System.out.println("Not a valid number");
			return this;
		}
		switch (choice) {
		case 0:
			System.out.println("Enter patients name:");
			String in = input.nextLine();
			return new ValidatePatientName(data, rpc, namePatientMap, in);
		case 1:
			return new CreateNewPatient(data, rpc);
		default:

		}
		return this;
	}
}
