package ui;

import java.util.Scanner;

/**
 * An abstract class that represends a usecase command.
 * 
 * @author Dieter
 * 
 */
// TODO: change the name of this class because it represents a command and not a
// usecase
public abstract class Usecase
{
	protected static Scanner input;

	public static void setScanner(Scanner s) {
		input = s;
	}

	final protected UserinterfaceData data;

	public Usecase(UserinterfaceData data) {
		this.data = data;
	}

	/**
	 * 
	 * @return
	 */
	public abstract Usecase Execute();

};