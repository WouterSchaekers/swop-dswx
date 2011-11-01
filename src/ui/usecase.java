package ui;

import java.util.Scanner;
/**
 * An abstract class that represends a usecase command.
 * @author Dieter
 *
 */
//TODO: change the name of this class because it represents a command and not a usecase
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
	/**
	 * 
	 * @return
	 */
	public abstract usecase Execute();
	
};