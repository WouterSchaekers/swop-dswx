package ui.usecases;

import ui.UIData;
import ui.UseCase;

public class Quit extends UseCase
{

	public Quit(UIData data) {
		super(data, Integer.MAX_VALUE);
	}

	@Override
	public UseCase execute() {
		System.out.println("Goodby, it was fun.");
		return null;
	} 
	@Override
	public String toString()
	{
		return"quit.";
	}
}
