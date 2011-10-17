package ui;

import controllers.MasterController;

public class uistarter
{
	public static void main(String[] args) {
		MasterController m = new MasterController();
		m.intializeSystem();
		UserInterface t = new UserInterface(m);
		t.start();
		
	}
}
