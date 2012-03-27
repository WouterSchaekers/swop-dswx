package machine;

import scheduler.HospitalDate;
import scheduler.TimeSlot;
import system.Location;
import exceptions.InvalidLocationException;
import exceptions.InvalidSchedulingRequestException;
import exceptions.InvalidSerialException;
import exceptions.InvalidTimeSlotException;

public class UltraSoundScanner extends Machine
{
	/**
	 * Creates an ultrasound scanner, exceptions are thrown as in the super
	 * class: {@link Machine#Machine(int, String)}
	 * 
	 * @throws InvalidTimeSlotException
	 * */
	UltraSoundScanner(int serial, Location location)
			throws InvalidLocationException, InvalidSerialException {
		super(serial, location);
	}



}
