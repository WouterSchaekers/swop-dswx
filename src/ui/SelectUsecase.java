package ui;

import ui.loginchain.isAllowedToLogin;
import ui.logoutchain.logOut;
import ui.registerpatientchain.registerPatient;

public class SelectUsecase extends usecase
{
	enum usecases
	{
		login("login"), RegisterPatient("register patient"), nothing(
				"do nothing"),logout("logout"),exitSystem("exit system");
		String description;

		private usecases(String descr) {
			this.description = descr;
		}

		public static usecases fromInt(int i) {
			for (usecases u : usecases.values())
				if (u.ordinal() == i)
					return u;
			return nothing;
		}
	}

	
	public SelectUsecase(DataBlob data) {
		super(data);

		// TODO Auto-generated constructor stub
	}

	@Override
	public usecase Execute() {
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
			return new isAllowedToLogin(data);
		case RegisterPatient:
			return new registerPatient(data);
		case logout:
			return new logOut(data);
		case exitSystem:
			return new exitSystem(data);
		default:
			break;
		}
		;

		return this;
	}

}
