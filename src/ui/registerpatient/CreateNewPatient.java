package ui.registerpatient;

import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;
import exceptions.InvalidNameException;

public class CreateNewPatient extends Usecase
{
	RegisterPatientController rpc;

	public CreateNewPatient(UserinterfaceData data,
			RegisterPatientController rpc) {
		super(data);
		this.rpc = rpc;
	}

	@Override
	public Usecase Execute() {
		System.out.println("Create a new Patient in the database");
		System.out.println("enter name:");
		String name = input.nextLine();

		try {
			rpc.registerNewPatient(data.getDataPasser(), name);
		} catch (InvalidNameException e) {
			System.out.println("name provided was invalid");
			return this;
		}
		return new DisplayAllPatients(data, rpc);
	}

}
