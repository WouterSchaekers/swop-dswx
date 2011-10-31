package ui;

import controllers.DataPasser;

public class UserInterface
{
	static UCHandler usecaseController;
	DataPasser data;
	public UserInterface(DataPasser data) {
		UserinterfaceData blob = new UserinterfaceData(data);
		usecaseController = new UCHandler(blob);
		this.data=data;
	}

	public void start() {
			usecaseController.start();
	}
}
