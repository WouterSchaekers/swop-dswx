package machine;

import exceptions.InvalidLocationException;
import exceptions.InvalidSerialException;

public class BloodAnalyser extends Machine
{
	/**
	 * Creates an BloodAnalyser scanner, exceptions are thrown as in the super class: 
	 * {@link Machine#Machine(int, String)} 
	 * */
	public BloodAnalyser(int serial, String location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}

}
