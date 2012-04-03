package ui;

import system.Hospital;
import system.StandardHospitalBuilder;

public class SystemStarter
{
	public static void main(String[] args) {
		Hospital hospital = new StandardHospitalBuilder().build();
		new UIRunner(new MainMenu(hospital)).run();
	}
}
