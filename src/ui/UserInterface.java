package ui;

import system.HospitalState;

public class UserInterface
{
	static UCHandler usecaseController;
	HospitalState data;

	public UserInterface(HospitalState data) {
		UserinterfaceData blob = new UserinterfaceData(data);
		usecaseController = new UCHandler(blob);
		this.data = data;
	}

	public void start() {
		usecaseController.start();
	}
}
