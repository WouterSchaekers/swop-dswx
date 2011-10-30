package ui;

import controllers.DataPasser;

public class UserInterface
{
	static UCHandler usecaseController;
	DataPasser data;
	public UserInterface(DataPasser data) {
		DataBlob blob = new DataBlob(data);
		usecaseController = new UCHandler(blob);
		this.data=data;
	}

	public void start() {
			usecaseController.start();
	}
}
