package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;


public abstract class MedicalTestFactory
{
	public abstract MedicalTest create() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException, FactoryInstantiation;
}
