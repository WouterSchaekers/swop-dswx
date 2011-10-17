package ui;

import help.FifoQueue;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.Spring;
import controllers.LoginController;
import controllers.MasterController;

public class UCcontroller
{
	MasterController t; // connec
	private Spring currentUseCase;
	private Map<String, Integer> map;
	private boolean inUseCase = false;

	public Iterable<String> handleInput(String input) {
		int choice = 0;
		if (!inUseCase) {
			Integer a = map.get(input);
			if (a == null)
				choice = Integer.MIN_VALUE;
			else
				choice = a;
		}else
		{
			choice = map.get(currentUseCase);
		}
		switch (choice) {
		case 0:// login
			((LoginController)this.t.getController("LoginController")).getUsers();
			return ((LoginController)this.t.getController("LoginController")).response();
		case 1:
			break;
		case Integer.MIN_VALUE:
			break;
		}
		return null;
	}

	public UCcontroller(MasterController m) {
		this.t=m;
		// every usecase has an activation key, once activated you stay in the usecase till you screw up
		map = new HashMap<String, Integer>();
		map.put("login", 0);
		map.put("Login", 0);
		map.put("", 1);

	}

}
