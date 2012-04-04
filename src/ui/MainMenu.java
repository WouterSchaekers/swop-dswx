package ui;

import java.util.Collection;
import java.util.Stack;
import ui.usecases.HospitalEquipmentUseCase;
import ui.usecases.Login;
import ui.usecases.Selector;

public class MainMenu extends UseCase
{
	public MainMenu(UIData data) {
		super(data);
	}

	private Collection<UseCase> getAvailable() {
		Collection<UseCase> rv = new Stack<UseCase>();
		addLogin(rv);
		addHospitalEquipment(rv);
		return rv;
	}

	private void addHospitalEquipment(Collection<UseCase> rv) {
	try {
		rv.add(new HospitalEquipmentUseCase(data));
	} catch (Exception e) {
		;
	}
		
	}

	private void addLogin(Collection<UseCase> rv) {
		try {
			rv.add(new Login(data));
		} catch (Exception e) {
			;
		}
	}
	@Override
	public UseCase execute() {

		Selector<UseCase> selector = new Selector<UseCase>(getAvailable(), new Selector.Displayer<UseCase>()
		{
			@Override
			public void display(UseCase s) {
				System.out.print(s);
			}
		});

		UseCase usecase = selector.get();
		if (usecase == null) {
			printLn("No usecase was selected.");
			return new MainMenu(data);
		} else
			return usecase;
	}

}
