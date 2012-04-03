package ui;

import java.util.Collection;
import java.util.LinkedList;
import system.Hospital;
import ui.UIRunner.UseCase;
import ui.usecases.Selector;

public class MainMenu implements UseCase
{
	public MainMenu(Hospital hospital)
	{
		
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
