package ui.addhospitalequipment;

import machine.MachineBuilder;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidSerialException;

public class BuildMachine extends AddHospitalEquipmentSuperClass
{

	public BuildMachine(UserinterfaceData data,
			AddHospitalEquipmentData chaindata) {
		super(data, chaindata);
	}

	@Override
	public Usecase Execute() {
		System.out.println("Create " + chainData.getMachineBuilder());
		System.out.println("Enter serial number and location");
		System.out.println();
		System.out.println("Serial:");
		int serial;
		try {
			serial = new Integer(input.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Not a number error error!");
			System.out.println("please try again");
			return this;
		}
		System.out.println("Enter location :");
		String location = input.nextLine();
		MachineBuilder b = chainData.getMachineBuilder();
		{
			try {
				chainData.getController().createMachine(b, serial, location,
						data.getLoginController());
			} catch (InvalidLocationException e) {
				System.out.println("Invalid location ");
				return this;
			} catch (InvalidSerialException e) {
				System.out.println("Invalid serial ");
				return this;
			} catch (InvalidLoginControllerException e) {
				System.out.println("Not allowed to do that sorry");
				return new SelectUsecase(data);
			}
		}
		System.out.println("Machine was sucesfully build and added to system");
		return new SelectUsecase(data);
	}

}
