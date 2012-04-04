package ui;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;
import exceptions.InvalidHospitalException;
import ui.usecases.Login;
import ui.usecases.Selector;

public class MainMenu extends UseCase
{
	public MainMenu(UIData data)
	{
	super(data)	;
	}
	private Collection<UseCase> getAvailable()
	{
		Collection<UseCase> rv = new Stack<UseCase>();
		addLogin(rv);
		
		return rv;
	}
	
	private void addLogin(Collection<UseCase> rv) {
		try {
			rv.add(new Login(data));
		} catch (InvalidHospitalException e) {
		}
	}
	@Override
	public UseCase execute() {
		Collection<String> strings = new LinkedList<String>();
		strings.add("abra");
		strings.add("cadabra");
		strings.add("alakazam");
		
		Selector<String> s = new Selector<String>(strings, new Selector.Displayer<String>()
		{
			@Override
			public void display(String s)
			{
				System.out.print(s);
			}
		});
		
		String string = s.get();
		System.out.println(string+" was selected.");
		return null;
	}

}
