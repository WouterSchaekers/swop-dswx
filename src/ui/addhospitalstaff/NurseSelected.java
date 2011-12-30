package ui.addhospitalstaff;

import controllers.CreateUserController;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;
import exceptions.UserAlreadyExistsException;
import ui.SelectUsecase;
import ui.Usecase;
import ui.UserinterfaceData;

public class NurseSelected extends Usecase
{
	private CreateUserController c;
	public NurseSelected(UserinterfaceData data, CreateUserController c) {
		super(data);
		this.c=c;
	}

	@Override
	public Usecase Execute() {
		System.out.println("Enter the name of the nurse!");
		String arg= input.nextLine();
		try {
			c.createNurse(arg,data.getLoginController(),data.getDataPasser());
		} catch (UserAlreadyExistsException e) {
			
			System.out.println("User already exists : error");
			return new SelectUsecase(data);
		} catch (InvalidNameException e) {
			System.out.println("name provided was invalid" + e);
			return new SelectUsecase(data);
			
		} catch (InvalidTimeSlotException e) {
		
		}
		System.out.println("Nurse "+arg+"succesfully created");
		return new SelectUsecase(data);
	}

}
