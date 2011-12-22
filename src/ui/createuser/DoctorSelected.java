package ui.createuser;

import controllers.CreateUserController;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class DoctorSelected extends Usecase
{

	private CreateUserController c;

	public DoctorSelected(UserinterfaceData data, CreateUserController c) {
		super(data);
		this.c=c;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usecase Execute() {
		System.out.println("Enter the doctors name:");
		String arg = input.nextLine();
		try {
			c.createDoctor(arg, data.getLoginController(), data.getDataPasser());
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			System.out.println("User already exists : error");
			return new SelectUsecase(data);
		} catch (InvalidNameException e) {
			System.out.println("name provided was invalid" + e);
		} catch (InvalidTimeSlotException e) {
			//wut?
		}
		System.out.println("Doctor "+ arg +" was succesfully created" );
		return new SelectUsecase(data);
	}

}
