package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SelectionMenu<T>
{
	private Map<String, T> map;
	private Scanner scanner = new Scanner(System.in);
	public SelectionMenu(Map<String, T> map) {
		this.map = map;
	}

	public T execute() {
		
		int i = 0;
		Map<Integer, T> m = new HashMap<Integer, T>();
		for (String s : map.keySet()) {
			System.out.println(i++ + ":" + s);
			m.put(i, map.get(s));
		}
		String input = scanner.nextLine();
		i = new Integer(input);
		if(m.containsKey(i))
			return m.get(i);
		else
		{
			System.out.println("invalid menu option");
			return null;
		}

	}
}
