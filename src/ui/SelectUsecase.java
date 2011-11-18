package ui;

import ui.loginchain.IsAllowedToLogin;
import ui.logoutchain.LogOut;
import ui.ordermedicaltestchain.OrderMedicalTest;
import ui.registerpatientchain.RegisterPatient;

/**
 * This class represents a menu to select what a user wants to do.
 * You can choose different options that are described in the enumeration below.
 */

public class SelectUsecase extends Usecase
{
	/**
	 * This enumeration is created to simplify the different use cases.
	 * 
	 * Login: a user logs in to the system.
	 * RegisterPatient: The nurse logs the new patient in to the system.
	 * Nothing: Returns immediatly to the select usecase.
	 * Logout: a user can log out from the system.
	 * Exit System: The system will be closed. 
	 *
	 *
	 *
	 */
	enum usecases
	{
		login("login"), RegisterPatient("register patient"), nothing(
				"do nothing"),logout("logout"),orderMedicalTest("order medical test"), exitSystem("exit system");
		String description;
		
		/**
		 * Gives a description of each different use case
		 */
		private usecases(String descr) {
			this.description = descr;
		}

		/**
		 * 
		 * @param i 
		 * 		Represents the number associated with the use case.
		 * @return The use case analogue to his number.
		 * 			If a wrong number is given the system does nothing
		 */
		public static usecases fromInt(int i) {
			for (usecases u : usecases.values())
				if (u.ordinal() == i)
					return u;
			return nothing;
		}
	}
/**
 * Initiates a new use case with data given.
 * @param data
 * 			Data is the information that the user interface must past, requires to create controllers and retains state information.
 */
	
	public SelectUsecase(UserinterfaceData data) {
		super(data);
	}

	/**
	 * In this method the initial chain is started. You can choose the different use cases.
	 */
	@Override
	public Usecase Execute() {
		System.out.println("Select what you would like to do: ");
		System.out.println("type the number of the new usecase");
		for (usecases u : usecases.values())
			System.out.println(u.ordinal() + " : " + u.description);
		String in = input.nextLine();
		int i;
		try {
			i = new Integer(in);
		} catch (NumberFormatException num) {
			System.out.println(in + " is not a valid number");
			return this;
		}
		usecases u = usecases.fromInt(i);
		switch (u) {
		case login:
			return new IsAllowedToLogin(data);
		case RegisterPatient:
			return new RegisterPatient(data);
		case logout:
			return new LogOut(data);
		case orderMedicalTest:
			return new OrderMedicalTest(data);
		case exitSystem:
			return new ExitSystem(data);
		default:
			break;
		}
		;

		return this;
	}

}
