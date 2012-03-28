package ui;

import system.Hospital;

public class UserInterface
{
	static UCHandler usecaseController;
	Hospital data;

	public UserInterface(Hospital data) {
		UserinterfaceData blob = new UserinterfaceData(data);
		usecaseController = new UCHandler(blob);
		this.data = data;
	}

	public void start() {
		usecaseController.start();
	}
}
