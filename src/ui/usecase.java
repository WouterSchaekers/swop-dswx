package ui;

import java.util.Scanner;

abstract class usecase
{
	protected static Scanner input;
	public static void setScanner(Scanner s)
	{
		input=s;
	}
	final protected DataBlob data;
	public usecase(DataBlob data){
		this.data=data;
	}
	public abstract usecase Execute();
	
};