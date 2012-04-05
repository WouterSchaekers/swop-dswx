package ui;

import java.util.Scanner;

public abstract class UseCase implements Comparable<UseCase>
{
	protected final UIData data;
	public final Integer priority;
	public UseCase(UIData data, Integer prior)
	{
		this.data=data;
		this.priority=prior;
	}
	public abstract UseCase execute();
	protected final void printLn(String string)
	{
		System.out.println(string);
	}
	protected final void print(String string)
	{
		System.out.print(string);
	}
	protected final String read()
	{
		return new Scanner(System.in).nextLine();
	}
	@Override
	public int compareTo(UseCase usecase)
	{
		return priority.compareTo(usecase.priority);
	}
	protected final UseCase mm()
	{
		return new MainMenu(data);
	}
}
