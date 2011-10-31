package ui;

import java.util.Scanner;

public abstract class usecase
{
	protected static Scanner input;
	public static void setScanner(Scanner s)
	{
		input=s;
	}
	final protected UserinterfaceData data;
	public usecase(UserinterfaceData data){
		this.data=data;
	}
	public abstract usecase Execute();
	
};