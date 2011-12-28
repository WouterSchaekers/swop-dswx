package medicaltest;

import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;


public abstract class MedicalTestFactory
{
	/**
	 * Creates a medicaltest given that the factory is sufficiently initialized.
	 * @return
	 * @throws InvalidNameException
	 * @throws InvalidDurationException
	 * @throws InvalidTimeSlotException
	 * @throws FactoryInstantiation
	 */
	public abstract MedicalTest create() throws InvalidNameException, InvalidDurationException, InvalidTimeSlotException, FactoryInstantiation;
}
