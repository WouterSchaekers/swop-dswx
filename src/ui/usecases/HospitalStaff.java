package ui.usecases;

import java.util.Collection;
import ui.MainMenu;
import ui.UIData;
import ui.UseCase;
import ui.usecases.Selector.Displayer;
import users.UserFactory;
import controllers.AddHospitalStaffController;
import controllers.interfaces.CampusIN;
import exceptions.InvalidHospitalException;
import exceptions.InvalidLocationException;
import exceptions.InvalidLoginControllerException;
import exceptions.InvalidNameException;
import exceptions.UserAlreadyExistsException;

public class HospitalStaff extends UseCase
{
	private AddHospitalStaffController controller;

	public HospitalStaff(UIData data) throws InvalidLoginControllerException, InvalidHospitalException {
		super(data, 3);
		controller = new AddHospitalStaffController(data.getLoginController());
	}

	@Override
	public UseCase execute() {
		printLn("Add hospital staff");
		Collection<UserFactory> factories = controller.getFactories();
		if (factories.size() == 0) {
			printLn("No users can be added.");
			return new MainMenu(data);
		}
		Selector<UserFactory> userfactories = new Selector<UserFactory>(factories, new Displayer<UserFactory>()
		{

			@Override
			public void display(UserFactory t) {
				System.out.println("Usertype:" + t.toTitle());
			}
		});
		UserFactory slected = userfactories.get();
		printLn("Enter the name for the user:");
		String name = read();
		slected.setName(name);
		Selector<CampusIN> campusSelector = new Selector<CampusIN>(controller.getLocations(), Login.campusDisplayer);
		controller.getLocations();
		CampusIN campus = campusSelector.get();
		slected.setLocation(campus);
		System.out.print("Campus:");
		Login.campusDisplayer.display(campus);
		printLn("was chosen.");
		try {
			controller.addStaff(slected);
		} catch (UserAlreadyExistsException e) {
			printLn("Username is already taken");
			return new MainMenu(data);
		} catch (InvalidNameException e) {
			printLn("Name is not valid.");
			return new MainMenu(data);
		} catch (InvalidLocationException e) {
			printLn("Something went wrong in setting campus");
			return new MainMenu(data);
		}
		System.out.println("User succesfully created!");
		return new MainMenu(data);
	}

	public String toString() {
		return "Add hospital staff";
	}
}
