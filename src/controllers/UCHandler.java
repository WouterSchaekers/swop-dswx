package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Spring;

/**
 * This class handles the usecases.
 * 
 * @author Dieter
 * 
 */
public class UCHandler
{
	private Metamanager t; // connec
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
		} else {
			choice = map.get(currentUseCase);
		}
		switch (choice) {
		case 0:// login
			LoginController lc = new LoginController(t);
			
			return lc.getUserNames();
			
		case 1:
			ArrayList<String>s= new ArrayList<String>();
			s.add("faal");
			return s;
		case Integer.MIN_VALUE:
			break;
		}
		return null;
	}

	public UCHandler(Metamanager m) {
		this.t = m;
		// every usecase has an activation key, once activated you stay in the
		// usecase till you screw up
		map = new HashMap<String, Integer>();
		map.put("login", 0);
		map.put("Login", 0);
		map.put("", 1);

	}

}
