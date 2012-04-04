package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import ui.usecases.Selector;

public class OrderedSelector<T extends Comparable<T>> extends Selector<T>
{

	public OrderedSelector(Collection<T> coll, ui.usecases.Selector.Displayer<T> displayer) {
		super(coll, displayer);
	}
	public interface Displayer<T>
	{
		public void display(T t);
	}
	private interface SelectionRunner
	{
		public SelectionRunner run();
	}
	private class StartRunner implements SelectionRunner
	{
		private Selector<T> selector;
		 StartRunner(Selector<T> s) {
			this.selector=s;
		}
		@Override
		public SelectionRunner run() {
			if(selector.coll.isEmpty())
			{
				//System.out.println("No menu options");
				return null;
			}
			Map<Integer, T> m = new HashMap<Integer, T>();
			int menuOption =0;
			List<T> list = new ArrayList<T>(coll);
			Collections.sort(list);
			for(T t:list)
				m.put(menuOption++, t);
			for(int i:m.keySet())
			{
				System.out.print(i+":");
				selector.displayer.display(m.get(i));
				System.out.println();
			}
			String in = new Scanner(System.in).nextLine();
			int entered;
			try{
			entered = new Integer(in);
			}catch(Exception e)
			{
				System.out.println(in+" is not a number!");
				return new StartRunner(selector);
			}
			if(!m.containsKey(entered))
			{
				System.out.println(entered+" is not a valid menu option!");
				return new StartRunner(selector);
			}
			selected = m.get(entered);
			return null;
		}
		
	}
	private T selected = null;
	
	private void run()
	{
		SelectionRunner current = new StartRunner(this) ;
		while(current != null)
			current = current.run();//run forrest run !
	}
	
	public T get()
	{
		run();
		return selected;
	}

}
