package ui;

import system.ExtendedStandardHospitalBuilder;
import system.Hospital;

public class SystemStarter
{
	public static void main(String[] args) {
		Hospital hospital = new ExtendedStandardHospitalBuilder().build();
		new UIRunner(new MainMenu(new UIData(hospital))).run();
	}
}
