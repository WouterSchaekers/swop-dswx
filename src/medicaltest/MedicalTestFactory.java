package medicaltest;

import exceptions.FactoryInstantiationException;
import exceptions.InvalidDurationException;
import exceptions.InvalidNameException;
import exceptions.InvalidTimeSlotException;

public abstract class MedicalTestFactory
{
	/**
	 * Creates a medicaltest given that the factory is sufficiently initialized.
	 * 
	 * @return
	 * @throws InvalidNameException
	 * @throws InvalidDurationException
	 * @throws InvalidTimeSlotException
	 * @throws FactoryInstantiationException
	 */
	public abstract MedicalTest create() throws InvalidNameException,
			InvalidDurationException, InvalidTimeSlotException,
			FactoryInstantiationException;
}
