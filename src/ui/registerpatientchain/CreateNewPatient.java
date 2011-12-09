package ui.registerpatientchain;

import controllers.RegisterPatientController;
import exceptions.InvalidNameException;
import ui.UserinterfaceData;
import ui.Usecase;

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
		//TODO; fix
		try {
			rpc.createNewPatient(data.getDataPasser(), name);
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DisplayAllPatients(data, rpc);
	}

}
