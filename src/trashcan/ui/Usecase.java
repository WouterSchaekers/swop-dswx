package ui;

import java.util.Scanner;
import exceptions.InvalidHospitalDateArgument;

/**
 * An abstract class that represends a usecase command.
 * 
 */
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
	 * @throws InvalidHospitalDateArgument
	 */
	public abstract Usecase Execute();
};