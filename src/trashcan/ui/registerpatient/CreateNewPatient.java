package ui.registerpatient;

import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import controllers.RegisterPatientController;

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
		try {
			System.out.println("Create a new Patient in the database");
			System.out.println("enter name:");
			String name = input.nextLine();

			//TODO: location
			rpc.registerNewPatient(name, null);
			return new DisplayAllPatients(data, rpc);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SelectUsecase(data);
		}
	}

}
