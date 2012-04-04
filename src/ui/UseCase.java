package ui;

public abstract class UseCase
{
	protected final UIData data;
	public UseCase(UIData data)
	{
		this.data=data;
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
	
}
