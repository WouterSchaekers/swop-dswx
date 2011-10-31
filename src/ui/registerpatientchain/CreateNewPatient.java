package ui.registerpatientchain;

import controllers.RegisterPatientController;
import ui.UserinterfaceData;
import ui.usecase;

public class CreateNewPatient extends usecase
{
	RegisterPatientController rpc;
	public CreateNewPatient(UserinterfaceData data, RegisterPatientController rpc) {
		super(data);
		this.rpc=rpc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
		System.out.println("Create a new Patient in the database");
		System.out.println("enter name:");
		String name = input.nextLine();
		rpc.createNewPatient(data.getDataPasser(),name);
		return new displayAllPatients(data, rpc);
	}

}
