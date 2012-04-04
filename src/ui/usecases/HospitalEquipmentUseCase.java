package ui.usecases;

import java.util.Collection;
import machine.MachineBuilder;
import system.Location;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import controllers.AddHospitalEquipmentController;
import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class HospitalEquipmentUseCase extends UseCase
{

	private AddHospitalEquipmentController addEquipment;
	private Displayer<MachineBuilder> machineBuilderDisplayer = new Displayer<MachineBuilder>()
	{

		@Override
		public void display(MachineBuilder t) {
			System.out.println(t.toString());
			
		}
	};

	public HospitalEquipmentUseCase(UIData data) throws Exception {
		super(data);
		addEquipment = new AddHospitalEquipmentController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		printLn("Add hospital equipment:");
		Collection<MachineBuilder> machinebuilders =addEquipment.getAllMachineBuilders();
		Selector<MachineBuilder>  machineBuilderSelector = new Selector<MachineBuilder>(machinebuilders, machineBuilderDisplayer );
		MachineBuilder builder = machineBuilderSelector.get();
		machineBuilderDisplayer.display(builder);
		printLn("Is going to be created.");
		builder.setLocation((Location)data.getLoginController().getLocation());
		int ID = selectID();
		builder.setSerial(ID);
		System.out.println("Set the equipments location (string):");
		String location = read();
		builder.setLocationWithinCampus(location);
		try {
			addEquipment.createMachine(builder);
		} catch (InvalidLocationException e) {
			System.out.println("Invalid location was set."+e.getMessage());
			System.out.println("Nothing was added");
			return new MainMenu(data);
		} catch (InvalidSerialException e) {
			System.out.println("Invalid locatin was set."+e.getMessage());
			System.out.println("Nothing was added");
			return new MainMenu(data);
		}
		printLn("Machine ");
		machineBuilderDisplayer.display(builder);
		printLn("was sucesfully created.");
		System.out.println("ID:"+ID);
		System.out.println("Location:"+location);
		return new MainMenu(data);
	}
	private int selectID() {
		boolean finished = false;
		int i=0;
		while(!finished)
		{
			printLn("select ID (type an int):");
			String in = read();
			try{
			i = new Integer(in);
			}catch(Exception e)
			{
				System.out.println(in+" is not an integer.");
				continue;
			}
			finished=true;
			}
		return i;
	}

	@Override
	public String toString()
	{
		return "Add hospital equipment";
	}

}
