package ui.registerpatientchain;

import controllers.RegisterPatientController;
import ui.UserinterfaceData;
import ui.Usecase;

public class CreateNewPatient extends Usecase
{
	RegisterPatientController rpc;
	public CreateNewPatient(UserinterfaceData data, RegisterPatientController rpc) {
		super(data);
		this.rpc=rpc;
	}

	@Override
	public Usecase Execute() {
		System.out.println("Create a new Patient in the database");
		System.out.println("enter name:");
		String name = input.nextLine();
		rpc.createNewPatient(data.getDataPasser(),name);
		return new DisplayAllPatients(data, rpc);
	}

}
